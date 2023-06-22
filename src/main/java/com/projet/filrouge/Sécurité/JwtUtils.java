package com.projet.filrouge.Sécurité;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtUtils {
    public String generateJwt(UserDetails userDetails){

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .signWith(SignatureAlgorithm.HS256, "lepetitbidoubiwap")
                .compact();
    }

    public Claims getData(String jwt){

        return Jwts.parser()
                .setSigningKey("lepetitbidoubiwap")
                .parseClaimsJws(jwt)
                .getBody();
    }

    public boolean isTokenValid(String jwt){
        Claims données = getData(jwt);
        return true;
    }
}