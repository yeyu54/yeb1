package com.yeyu.config.security.component;

import com.yeyu.pojo.Menu;
import com.yeyu.pojo.Role;
import com.yeyu.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 权限控制
 * 根据请求url分析角色拦截器
 * @author 13474
 * @Package com.yeyu.config.security.component
 * @date 2021/12/921:24
 */
@Component
public class Interceptor implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private IMenuService menuService;
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
      String url =  ((FilterInvocation) o ).getRequestUrl();
        List<Menu> menus = menuService.getmenuwithrole();
        for (Menu menu : menus) {
            if (antPathMatcher.match(menu.getUrl(),url)){
              String[] str = menu.getRoles().stream().map(Role::getName).toArray(String[]::new);
                return SecurityConfig.createList(str);
            }
        }
        //没匹配的url默认登录可访问
        return SecurityConfig.createList("Role_login");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
