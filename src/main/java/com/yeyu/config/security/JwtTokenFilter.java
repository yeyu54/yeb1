package com.yeyu.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 13474
 * @Package com.yeyu.config.security
 * @date 2021/11/1621:51
 */
public class JwtTokenFilter extends OncePerRequestFilter {

   @Value("${jwt.tokenHeader}")
    private  String tokenHeader;
   @Value("${jwt.tokenHead}")
   private String tokenHead;

   @Autowired
   private JwTokenUtils jwTokenUtils;
   @Autowired
   private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request , HttpServletResponse response , FilterChain filterChain) throws ServletException, IOException {
        String authHeader =  request.getHeader(tokenHeader);
        //存在token
        if (null != authHeader && authHeader.startsWith(tokenHead)){
            String authtoken =  authHeader.substring(tokenHead.length());
            String username = jwTokenUtils.getUserNameFromToken(authtoken);
            //token存在 但是未登录
            if (null !=  username && null == SecurityContextHolder.getContext().getAuthentication()){
                //登录
                UserDetails userDetails =  userDetailsService.loadUserByUsername(username);
                //验证token是否有效，重新设置对象
                if (jwTokenUtils.validdateToken(authtoken,userDetails))
                {
                    UsernamePasswordAuthenticationToken authenticationToken =     new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
filterChain.doFilter(request,response);
    }
}
