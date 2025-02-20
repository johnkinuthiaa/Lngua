package com.slippery.lingua.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenService {
    private final String SECRET_STRING="3d64d7a7e855c818059e98bff18322c90ec1213ff2a6ea452562f904d0fcdd64f24faccaf1746a0d2037c0a9012bed4e214963a3c0884cfaed55f1d6f7fed6b4554d19f8a4f6c06699df04f0273a4187e70bae42dabe97996eb570922ac3378875e5706d552501e429132b9af86ca6cdb19e9c27ff51bc763fc7e4015074bde7bae5d3e385275b46d8a33acec30fab25c34ea687472ae965602629cd04f1e1a285ffd78d836249c557d25ca9571d9dab9b900ba77ffc1da87e1190a4f7eeceef424e599d0580f8a7870b6f390b0228e59fb0c0085f154b00ada5ee21b30bae5b52ff8f22ad98fcc075e93a51f8b6684f31dde4e56c153a3b3340bb2aafb95351";
    private final Long EXPIRATION_TIME=8640000L;

    private SecretKey generateToken(){
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_STRING);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateJwtToken(String username){
        Map<String,Object> claims =new HashMap<>();
        return Jwts
                .builder()
                .claims()
                .add(claims)
                .subject(username)
                .and()
                .signWith(generateToken())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .compact();
    }
}
