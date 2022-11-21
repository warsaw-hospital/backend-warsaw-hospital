package com.warsaw.hospital.auth.utils;

import com.warsaw.hospital.utils.ListUtil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import io.micrometer.core.lang.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JwtUtil {
  private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

  public static final String USER = "USER";
  public static final String ADMIN = "ADMIN";
  public static final String JOB = "JOB";

  private static final String AUTHORITIES = "authorities";
  private static final String APPLICATION_ID = "applicationId";
  private static final String EMAIL = "email";
  private static final String ACCESS_TOKEN = "access_token";
  private static final String REFRESH_TOKEN = "refresh_token";
  private static final String USER_ID = "user_id";

  private final CookieUtil cookieUtil;
  private final Key secretKey;
  private final JwtParser jwtParser;
  private final int jwtExpirationMs;

  public JwtUtil(
      CookieUtil cookieUtil,
      @Value("${app.jwt.secret}") String jwtSecret,
      @Value("${app.jwt.expirationMs}") int jwtExpirationMs) {
    this.cookieUtil = cookieUtil;
    this.secretKey =
        Keys.hmacShaKeyFor(Base64Utils.encode(jwtSecret.getBytes(StandardCharsets.UTF_8)));
    this.jwtParser = Jwts.parserBuilder().setSigningKey(this.secretKey).build();
    this.jwtExpirationMs = jwtExpirationMs;
  }

  /**
   * This method creates a JWT token string, which will be used in authentication.
   *
   * @param userId authenticated user id;
   * @param accessToken access token given from 3rd party;
   * @param refreshToken refresh token given from 3rd party.
   * @param grantedAuthorities list of authorities given to the user.
   * @return String (not null)
   */
  public String generateJwtToken(
      Long userId,
      String accessToken,
      String refreshToken,
      List<GrantedAuthority> grantedAuthorities) {
    var now = new Date();
    List<String> authorities =
        grantedAuthorities.stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());
    return Jwts.builder()
        .setSubject("me")
        .claim(ACCESS_TOKEN, accessToken)
        .claim(REFRESH_TOKEN, refreshToken)
        .claim(USER_ID, userId)
        .claim(AUTHORITIES, authorities)
        .setIssuedAt(now)
        .setExpiration(new Date(now.getTime() + jwtExpirationMs))
        .signWith(secretKey)
        .compact();
  }

  /**
   * This method returns user email claim from JWT token.
   *
   * @param token JWT token string.
   * @return UUID value
   */
  public UUID getApplicationIdClaim(String token) {
    isValid(token);
    var rawUuid = (String) jwtParser.parseClaimsJws(token).getBody().get(APPLICATION_ID);
    return UUID.fromString(rawUuid);
  }

  /**
   * This method returns user email claim from JWT token.
   *
   * @param token JWT token string.
   * @return String or null.
   */
  @Nullable
  public String getEmailClaim(String token) {
    isValid(token);
    return (String) jwtParser.parseClaimsJws(token).getBody().get(EMAIL);
  }

  /**
   * This method returns user authority list from JWT token.
   *
   * @param token JWT token string.
   * @return Not empty list or null.
   */
  @Nullable
  public List<String> getAuthorities(String token) {
    isValid(token);
    Object maybeList = jwtParser.parseClaimsJws(token).getBody().get(AUTHORITIES);
    return ListUtil.objectToList(maybeList, String.class);
  }

  /**
   * This method returns user id from JWT token.
   *
   * @param token JWT token string.
   * @return Integer or null.
   */
  @Nullable
  public Integer getUserId(String token) {
    isValid(token);
    return (Integer) jwtParser.parseClaimsJws(token).getBody().get(USER_ID);
  }

  /**
   * This method returns access token value from JWT token.
   *
   * @param token JWT token string.
   * @return String or null.
   */
  @Nullable
  public String getAccessToken(String token) {
    isValid(token);
    return (String) jwtParser.parseClaimsJws(token).getBody().get(ACCESS_TOKEN);
  }

  /**
   * This method returns refresh token value from JWT token.
   *
   * @param token JWT token string.
   * @return String or null.
   */
  @Nullable
  public String getRefreshToken(String token) {
    isValid(token);
    return (String) jwtParser.parseClaimsJws(token).getBody().get(REFRESH_TOKEN);
  }

  /**
   * This method validates JWT claim. There are three possible cases: 1) Returns false if JWT claim
   * is empty or null; 2) Returns false if JWT claim is not parsable; 3) Returns true if JWT claim
   * is parsable.
   *
   * @param jwtClaim JWT claim string.
   * @return true or false according to JWT claim.
   */
  public boolean isValid(String jwtClaim) {
    if (jwtClaim == null || StringUtils.isBlank(jwtClaim)) {
      LOGGER.error("JWT token is empty");
      return false;
    }

    try {
      jwtParser.parseClaimsJws(jwtClaim);
      return true;
    } catch (SignatureException e) {
      LOGGER.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      LOGGER.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      LOGGER.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      LOGGER.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      LOGGER.error("JWT claims string is empty: {}", e.getMessage());
    }
    return false;
  }

  /**
   * This method parses http servlet request and returns a JWT token. It does that by retrieving the
   * value from authentication cookie.
   *
   * @param request incoming request.
   * @return JWT token string or null.
   */
  @Nullable
  public String parseJwt(HttpServletRequest request) {
    return cookieUtil.getAuthorizationCookieValue(request);
  }
}
