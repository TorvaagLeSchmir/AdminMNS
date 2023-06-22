package com.projet.filrouge.Sécurité;

import com.projet.filrouge.DAO.PersonneDAOImpl;
import com.projet.filrouge.Modèles.Personne;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

private PersonneDAOImpl personneDAO;

    public MyUserDetailsService(PersonneDAOImpl personneDAO) {
        this.personneDAO = personneDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String emailMNS) throws UsernameNotFoundException {

        Optional<Personne> optional = personneDAO.findByEmail(emailMNS);

        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("L'utilisateur n'existe pas");
        }


        return new MyUserDetails(optional.get());
    }
}