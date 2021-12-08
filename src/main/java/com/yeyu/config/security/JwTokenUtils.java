package com.yeyu.config.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 13474
 * @Package com.yeyu.config.security
 * @date 2021/11/14 11:09
 */

@Component
public class JwTokenUtils {
    private static final String CLATM_KEY_USERNAME="sub";
    private static final String CLATM_KEY_CREATED="created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    /*
     *
     * 根据用户信息生成JWT TOKEN
     * @param userDateils
     * @return
     * */
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<String, Object>();
        claims.put(CLATM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLATM_KEY_CREATED,new Date());
        return generateToken(claims);
    }
/*
*
* 根据载荷生成 JWT Token
*
* @param claims
* @return
* */
    private String generateToken(Map<String,Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    /*
     * 生成token失效时间
     * @return
     * */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }
   /*
   * 从token中获取用户名
   *
   * @param token
   * @return
   * */
    public String getUserNameFromToken(String token){
        String username;

       try {
           Claims claims = getClainsFormToken(token);
           username = claims.getSubject();
       }catch (Exception e){
           username = null;
       }
        return username;
    }
    /*
    * 验证token是否有效
    *
    * @param token
    * @return
    * */
    public boolean validdateToken(String token , UserDetails userDetails){
      String username =   getUserNameFromToken(token);
      return username.equals(userDetails.getUsername()) && !isTokenExpiored(token);
    }
    /*
    * 判断token是否失效
    *
    * @param token
    * @return
    * */
    private boolean isTokenExpiored(String token) {
        Date expired = getEXpiredDateFromToken(token);
        return expired.before(new Date());
    }
    /*
    * 判断token是否可以被刷新
    *
    * @param token
    * @return
    * */
    public boolean canRefresh(String token){
        return !isTokenExpiored(token);
    }

    /*
    * 刷新token
    * @param token
    * @return
    * */
    public String refreshToken(String token){
        Claims claims = getClainsFormToken(token);
        claims.put(CLATM_KEY_CREATED , new Date());
        return generateToken(claims);
    }
    /*
    * 获取token失效时间
    *
    * @param token
    *
    * @return
    * */
    private Date getEXpiredDateFromToken(String token) {
        Claims claims = getClainsFormToken(token);
        return claims.getExpiration();

    }
    /*
* 获取荷载
* @param token
*
* @retrun
* */
    private Claims getClainsFormToken(String token) {
            Claims claims = null;
            try {
                claims = Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(token)
                        .getBody();
            }catch (Exception e){
                e.printStackTrace();
            }
            return claims;
    }

}
