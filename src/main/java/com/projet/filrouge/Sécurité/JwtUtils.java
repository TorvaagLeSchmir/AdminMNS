package com.projet.filrouge.Sécurité;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtils {
    public String generateJwt(UserDetails userDetails){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, "lepetitbidoubiwap")
                .compact();
    }


    public Claims getData(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey("lepetitbidoubiwap")
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            // Une erreur s'est produite pendant le parsing du JWT, le token est donc invalide
            return null;
        }
    }

    public boolean isTokenValid(String jwt){
        Claims données = getData(jwt);
        return données != null;  // Si les données sont null, le token n'est pas valide
    }

}