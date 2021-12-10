package com.yeyu.config.security.component;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author 13474
 * @Package com.yeyu.config.security.component
 * @date 2021/12/1018:51
 */
@Component
public class CusetUrl implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
       for (ConfigAttribute configAttribute : collection){
           String neerole = configAttribute.getAttribute();
           if("Role_login".equals(neerole)){
               if (authentication instanceof AnonymousAuthenticationToken){
                   throw new AccessDeniedException("未登录，请登录");
               }else {
                   return;
               }
           }
           Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
           for (GrantedAuthority grantedAuthority : authorities){
               if(grantedAuthority.getAuthority().equals(neerole)){
                   return;
               }
           }
       }
        throw new AccessDeniedException("权限不足，请联系管理员");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
