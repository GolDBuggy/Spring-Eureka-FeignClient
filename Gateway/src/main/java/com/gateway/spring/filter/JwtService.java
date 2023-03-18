package com.gateway.spring.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY="214125442A472D4B6150645367566B58703273357638792F423F4528482B4D62";



    public boolean validateToken(String token){
        if(getEmailByToken(token)!=null && checkExpirationTime(token)){
            return true;
        }

        return false;
    }

    private boolean checkExpirationTime(String token){
        Claims claims=getClaims(token);
        return claims.getExpiration().after(new Date(System.currentTimeMillis()));
    }

    private String getEmailByToken(String token){
        Claims claims=getClaims(token);
        return claims.getSubject();
    }

    private Key key(){
        byte[] bytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }



    private Claims getClaims(String token){
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();
    }
}
