package com.progetto.projectmanagement.security;

import io.jsonwebtoken.ExpiredJwtException;
import com.progetto.projectmanagement.domain.ResponseModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class AuthenticationTokenFilter extends OncePerRequestFilter {

    private final Log logger = LogFactory.getLog(this.getClass());

    private UserDetailServiceImpl userDetailsService;

    @Autowired
    private TokenUtil jwtTokenUtil;

    @Value("Authorization")
    private String tokenHeader;

    @Autowired
    ResponseModel responseModel;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String requestHeader = request.getHeader(this.tokenHeader);

        String username = null;
        String authToken = null;

        if (requestHeader != null ) {
          try {
              authToken = requestHeader.substring(7);
              username = jwtTokenUtil.getUsernameFromToken(authToken);
              logger.info(" username in Auth filter "+ username);
            } catch (IllegalArgumentException e) {
                logger.error("an error occured during getting username from token", e);
            } catch (ExpiredJwtException e) {
                logger.error("the token is expired and not valid anymore", e);
            } catch (Exception e){
                logger.error("the token AuthenticationException ");
            }
        } else {
            logger.warn("no token value set to header ");
        }

        logger.info("checking authentication for user " + username);

        
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // It is not compelling necessary to load the use details from the database. You could also store the information
            // in the token and read it from it. It's up to you ;)
            userDetailsService = new UserDetailServiceImpl();
             UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // For simple validation it is completely sufficient to just check the token integrity. You don't have to call
            // the database compellingly. Again it's up to you ;)
            if (jwtTokenUtil.validateToken(authToken, userDetails.getUsername(),userDetails.getPassword())) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                logger.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        try {
            chain.doFilter(request, response);
         } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in last chain.doFilter "+e);
        }
    }
}