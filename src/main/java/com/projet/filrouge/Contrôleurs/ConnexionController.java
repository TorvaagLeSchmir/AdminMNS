package com.projet.filrouge.Contrôleurs;


import com.projet.filrouge.Modèles.Personne;
import com.projet.filrouge.Sécurité.JwtUtils;
import com.projet.filrouge.Sécurité.MyUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
public class ConnexionController {



    final
    AuthenticationManager authenticationManager;
    final
    JwtUtils jwtUtils;

    public ConnexionController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {

        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/connexion")
    public String connexion(@RequestBody Personne personne) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(personne.getEmailMNS(), personne.getMotDePasse())
            );

        }catch(BadCredentialsException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "L'utilisateur n'existe pas", e);
        }
        return jwtUtils.generateJwt(new MyUserDetails(personne));
    }
}