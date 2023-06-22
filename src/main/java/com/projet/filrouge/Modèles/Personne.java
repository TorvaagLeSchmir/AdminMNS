package com.projet.filrouge.Modèles;

import java.sql.Date;
import java.util.List;

public class Personne {

    private int id;
    private String tel;
    private Date dateNaissance;
    private String nom;
    private String prénom;
    private String ville;
    private String rue;
    private String codePostal;
    private String nationalité;
    private String emailMNS;
    private String motDePasse;
    private String emailPerso;
    private List<Rôle> listeRoles;
    private String infos;

    public Personne() {
    }

    public Personne(String nom, String prénom) {
        this.nom = nom;
        this.prénom = prénom;
    }

    public Personne(String tel, Date dateNaissance, String ville, String rue, String codePostal, String nationalité, String emailPerso) {
        this.tel = tel;
        this.dateNaissance = dateNaissance;
        this.ville = ville;
        this.rue = rue;
        this.codePostal = codePostal;
        this.nationalité = nationalité;
        this.emailPerso = emailPerso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrénom() {
        return prénom;
    }

    public void setPrénom(String prénom) {
        this.prénom = prénom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getEmailMNS() {
        return emailMNS;
    }

    public void setEmailMNS(String emailMNS) {
        this.emailMNS = emailMNS;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEmailPerso() {
        return emailPerso;
    }

    public void setEmailPerso(String emailPerso) {
        this.emailPerso = emailPerso;
    }

    public List<Rôle> getListeRoles() {
        return listeRoles;
    }

    public void setListeRoles(List<Rôle> listeRoles) {
        this.listeRoles = listeRoles;
    }

    public String getNationalité() {
        return nationalité;
    }

    public void setNationalité(String nationalité) {
        this.nationalité = nationalité;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", tel='" + tel + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", nom='" + nom + '\'' +
                ", prénom='" + prénom + '\'' +
                ", ville='" + ville + '\'' +
                ", rue='" + rue + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", nationalité='" + nationalité + '\'' +
                ", emailMNS='" + emailMNS + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", emailPerso='" + emailPerso + '\'' +
                ", listeRoles=" + listeRoles +
                ", infos='" + infos + '\'' +
                '}';
    }
}
