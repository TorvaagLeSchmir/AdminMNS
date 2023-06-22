package com.projet.filrouge.Contrôleurs;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class AdminController {


    @GetMapping("/userinfo")
    public ResponseEntity<Map<String, String>> getUserInfo(HttpServletRequest request) {
        Map<String, String> response = new HashMap<>();
        HttpSession session = request.getSession(false);

        if (session != null && SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMINISTRATEUR"))) {
            response.put("role", "ADMINISTRATEUR");
        }
        if (session != null && SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_UTILISATEUR"))) {

            response.put("role", "UTILISATEUR");
        }
        if (session != null && SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_CANDIDAT"))) {

                    response.put("role", "CANDIDAT");
                }

        return ResponseEntity.ok(response);
    }

}