package com.warsaw.hospital.auth.config;

import com.warsaw.hospital.auth.utils.JwtUtil;
import com.warsaw.hospital.exception.ApiException;
import com.warsaw.hospital.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter implements Filter {
  private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthFilter.class);

  private final JwtUtil jwtUtil;
  private final UserService userService;

  public JwtAuthFilter(JwtUtil jwtUtil, UserService userService) {
    this.jwtUtil = jwtUtil;
    this.userService = userService;
  }

  @Override
  public void init(FilterConfig filterConfig) {}

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String token = jwtUtil.parseJwt((HttpServletRequest) request);
    if (token != null && jwtUtil.isValid(token)) {
      Authentication profile = buildProfile(token);
      SecurityContextHolder.getContext().setAuthentication(profile);
    }
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {}

  /**
   * This method creates an authenticated profile from JWT token. If the token does not have USER
   * authority, then an api exception is thrown.
   *
   * @param token A valid JWT token.
   * @return authenticated profile.
   * @throws ApiException when token does not have USER authority.
   */
  private AuthenticatedProfile buildProfile(String token) throws ApiException {
    List<String> authorities = jwtUtil.getAuthorities(token);
    if (authorities != null && authorities.contains(JwtUtil.USER)) {
      AuthenticatedProfile profile = new AuthenticatedProfile();
      Long userId = Long.valueOf(jwtUtil.getUserId(token));
      profile
          .setId(userId)
          .setAccessToken(jwtUtil.getAccessToken(token))
          .setRefreshToken(jwtUtil.getRefreshToken(token))
          .setAuthorityList(jwtUtil.getAuthorities(token))
          .setAuthenticated(true);
      return profile;
    } else {
      LOGGER.error("Error in authorization. Token does not have USER authority.");
      throw ApiException.bad("err.auth.invalidAuthorities");
    }
  }
}
