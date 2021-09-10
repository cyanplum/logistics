package cn.sevenlion.logistics.security.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import cn.sevenlion.logistics.security.exceptions.SevenlionException;
import cn.sevenlion.logistics.security.model.token.SevenlionAuthenticationToken;

import java.util.Date;
import java.util.Map;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/20 11:16 上午
 */
public class JwtAuthenticationTokenService implements AuthenticationTokenService {

     private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

     private String jwtKey = "sevenlion_security";

    @Override
    public String createToken(SevenlionAuthenticationToken authenticationToken) {
        long date = System.currentTimeMillis();
        long expireTime = date + authenticationToken.getExpireIn() * 1000;
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(new Date(date))
                .claim("token", authenticationToken)
                .setSubject("access_token " + authenticationToken.getAuthenticationUser().getPrincipal())
                .signWith(signatureAlgorithm, jwtKey)
                .setExpiration(new Date(expireTime));
        return builder.compact();
    }

    @Override
    public SevenlionAuthenticationToken readToken(String tokenStr) {
        Jws<Claims> claims = null;
        try{
            claims = Jwts.parser()
                    .setSigningKey(jwtKey)
                    .parseClaimsJws(tokenStr);
        }catch (Exception e) {
            throw new SevenlionException("token过期!");
        }
        if (claims != null) {
            try {
                Map<String,Object> tokenMap = claims.getBody().get("token", Map.class);
                if (tokenMap == null) {
                    return null;
                }
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                SevenlionAuthenticationToken authenticationToken = objectMapper.convertValue(tokenMap, SevenlionAuthenticationToken.class);
                return authenticationToken;
            }catch (Exception e) {
                e.printStackTrace();
                throw new SevenlionException("token解析错误!");
            }
        }else {
            throw new SevenlionException("token解析错误!");
        }
    }
}
