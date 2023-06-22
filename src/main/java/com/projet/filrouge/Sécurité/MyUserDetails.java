package com.projet.filrouge.Sécurité;

import com.projet.filrouge.Modèles.Personne;
import com.projet.filrouge.Modèles.Rôle;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

    private Personne personne;

    public MyUserDetails(Personne personne) {
        this.personne = personne;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Rôle> rôles = personne.getListeRoles();

        for (Rôle rôle : rôles) {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + rôle.getNom().toUpperCase());
            authorities.add(authority);
        }
        return authorities;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        List<Rôle> rôles = personne.getListeRoles(); // Obtenez la liste des rôles de la personne à partir de la table de liaison
//
//        GrantedAuthority authority = null;
//        for (Rôle rôle : rôles) {
//            authority = new SimpleGrantedAuthority("ROLE_" + rôle.getNom().toUpperCase());
//            authorities.add(authority);
//        }
//        return List.of(authority);
//    }

    @Override
    public String getPassword() {
        return personne.getMotDePasse();
    }

    @Override
    public String getUsername() {
        return personne.getEmailMNS();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
