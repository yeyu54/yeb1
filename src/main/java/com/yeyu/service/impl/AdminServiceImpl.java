package com.yeyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.yeyu.config.security.JwTokenUtils;
import com.yeyu.pojo.Admin;
import com.yeyu.mapper.AdminMapper;
import com.yeyu.pojo.RespBean;
import com.yeyu.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yeyu
 * @since 2021-11-14
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService{
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwTokenUtils jwTokenUtils;
    @Value("${jwt.tokenHeader}")
    private String tokenHead;
    /**
    * @Description:登录之后返回token
    * @Param: [username, password, request]
    * @return
    * @date: 2021/11/14
    */
    @Override
  public RespBean login(String username, String password, HttpServletRequest request){
       //登录
        UserDetails userDetails =  userDetailsService.loadUserByUsername(username);

        if (null == userDetails || passwordEncoder.matches(password,userDetails.getPassword())){
            return RespBean.error("用户名密码不正确");

       }

       if (!userDetails.isEnabled()){
           return RespBean.error("账号被禁用");
       }

       //更新security登陆用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
       //生成token
       String token = jwTokenUtils.generateToken(userDetails);
        Map<String,Object> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);

        return RespBean.success("登陆成功",tokenMap);

    }
    @Override
    public Admin getAdminusername(String username){
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",username).eq("enabled",true));
    }

}
