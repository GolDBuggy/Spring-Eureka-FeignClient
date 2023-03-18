package com.user.spring.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

import java.util.Date;

@Service
public class JwtService {

    private final static String SECRET_KEY="214125442A472D4B6150645367566B58703273357638792F423F4528482B4D62";

    public String generateToken(String email){
        return Jwts.builder().setSubject(email)
                .setIssuer("ADMIN").setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+100*60*1000))
                .signWith(key(), SignatureAlgorithm.HS256).compact();
    }


    private Key key(){
        byte[] bytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }





    private Claims getClaims(String token){
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();
    }
}
