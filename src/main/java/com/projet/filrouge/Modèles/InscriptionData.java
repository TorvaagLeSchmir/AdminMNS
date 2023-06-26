package com.projet.filrouge.Modèles;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.sql.Date;

public class InscriptionData implements Serializable {

    private String prenom;
    private String nom;
    private Date date_de_naissance;
    private String adresse;
    private String code_postal;
    private String ville;
    private String telephone;
    private String email;
    private String nationalite;
    private String diplome;
    private String formation_type;
    private String amenagements;
    private String amenagementsInfo;
    private String tiers_temps;
    private String langages;
    private String projets_url;
    private String formation_interesse;
    private String choix_mns;
    private String choix_formation;
    private String projet_pro;
    private String secteur_stage;
    private String qualites;
    private String defauts;
    private String passions;
    private String niveau_anglais;
    private String autre_langue;
    private String autre_langueInfo;
    private String permis_b;
    private String vehicule;
    private String mobilite;
    private String expatriation_stage;
    private String connu_mns;
    private MultipartFile photo;
    private MultipartFile copie_id;
    private MultipartFile cv;
    private MultipartFile copie_diplome;
    private MultipartFile bulletins_notes;
    private MultipartFile lettre_motivation;
    private boolean acceptation_politique;
    private String ancien_stagiaire_prenom;
    private String ancien_stagiaire_nom;
    private String ancien_stagiaire_formation;

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate_de_naissance() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(Date date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getFormation_type() {
        return formation_type;
    }

    public void setFormation_type(String formation_type) {
        this.formation_type = formation_type;
    }

    public String getAmenagements() {
        return amenagements;
    }

    public void setAmenagements(String amenagements) {
        this.amenagements = amenagements;
    }

    public String getAmenagementsInfo() {
        return amenagementsInfo;
    }

    public void setAmenagementsInfo(String amenagementsInfo) {
        this.amenagementsInfo = amenagementsInfo;
    }


    public String getLangages() {
        return langages;
    }

    public void setLangages(String langages) {
        this.langages = langages;
    }

    public String getProjets_url() {
        return projets_url;
    }

    public void setProjets_url(String projets_url) {
        this.projets_url = projets_url;
    }

    public String getFormation_interesse() {
        return formation_interesse;
    }

    public void setFormation_interesse(String formation_interesse) {
        this.formation_interesse = formation_interesse;
    }

    public String getChoix_mns() {
        return choix_mns;
    }

    public void setChoix_mns(String choix_mns) {
        this.choix_mns = choix_mns;
    }

    public String getChoix_formation() {
        return choix_formation;
    }

    public void setChoix_formation(String choix_formation) {
        this.choix_formation = choix_formation;
    }

    public String getProjet_pro() {
        return projet_pro;
    }

    public void setProjet_pro(String projet_pro) {
        this.projet_pro = projet_pro;
    }

    public String getSecteur_stage() {
        return secteur_stage;
    }

    public void setSecteur_stage(String secteur_stage) {
        this.secteur_stage = secteur_stage;
    }

    public String getQualites() {
        return qualites;
    }

    public void setQualites(String qualites) {
        this.qualites = qualites;
    }

    public String getDefauts() {
        return defauts;
    }

    public void setDefauts(String defauts) {
        this.defauts = defauts;
    }

    public String getPassions() {
        return passions;
    }

    public void setPassions(String passions) {
        this.passions = passions;
    }

    public String getNiveau_anglais() {
        return niveau_anglais;
    }

    public void setNiveau_anglais(String niveau_anglais) {
        this.niveau_anglais = niveau_anglais;
    }

    public String getAutre_langue() {
        return autre_langue;
    }

    public void setAutre_langue(String autre_langue) {
        this.autre_langue = autre_langue;
    }

    public String getAutre_langueInfo() {
        return autre_langueInfo;
    }

    public void setAutre_langueInfo(String autre_langueInfo) {
        this.autre_langueInfo = autre_langueInfo;
    }


    public String getMobilite() {
        return mobilite;
    }

    public void setMobilite(String mobilite) {
        this.mobilite = mobilite;
    }

    public String getConnu_mns() {
        return connu_mns;
    }

    public void setConnu_mns(String connu_mns) {
        this.connu_mns = connu_mns;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }

    public MultipartFile getCopie_id() {
        return copie_id;
    }

    public void setCopie_id(MultipartFile copie_id) {
        this.copie_id = copie_id;
    }

    public MultipartFile getCv() {
        return cv;
    }

    public void setCv(MultipartFile cv) {
        this.cv = cv;
    }

    public MultipartFile getCopie_diplome() {
        return copie_diplome;
    }

    public void setCopie_diplome(MultipartFile copie_diplome) {
        this.copie_diplome = copie_diplome;
    }

    public MultipartFile getBulletins_notes() {
        return bulletins_notes;
    }

    public void setBulletins_notes(MultipartFile bulletins_notes) {
        this.bulletins_notes = bulletins_notes;
    }

    public MultipartFile getLettre_motivation() {
        return lettre_motivation;
    }

    public void setLettre_motivation(MultipartFile lettre_motivation) {
        this.lettre_motivation = lettre_motivation;
    }

    public boolean isAcceptation_politique() {
        return acceptation_politique;
    }

    public void setAcceptation_politique(boolean acceptation_politique) {
        this.acceptation_politique = acceptation_politique;
    }

    public String getAncien_stagiaire_prenom() {
        return ancien_stagiaire_prenom;
    }

    public void setAncien_stagiaire_prenom(String ancien_stagiaire_prenom) {
        this.ancien_stagiaire_prenom = ancien_stagiaire_prenom;
    }

    public String getAncien_stagiaire_nom() {
        return ancien_stagiaire_nom;
    }

    public void setAncien_stagiaire_nom(String ancien_stagiaire_nom) {
        this.ancien_stagiaire_nom = ancien_stagiaire_nom;
    }

    public String getAncien_stagiaire_formation() {
        return ancien_stagiaire_formation;
    }

    public void setAncien_stagiaire_formation(String ancien_stagiaire_formation) {
        this.ancien_stagiaire_formation = ancien_stagiaire_formation;
    }

    public String getTiers_temps() {
        return tiers_temps;
    }

    public void setTiers_temps(String tiers_temps) {
        this.tiers_temps = tiers_temps;
    }

    public String getPermis_b() {
        return permis_b;
    }

    public void setPermis_b(String permis_b) {
        this.permis_b = permis_b;
    }

    public String getVehicule() {
        return vehicule;
    }

    public void setVehicule(String vehicule) {
        this.vehicule = vehicule;
    }

    public String getExpatriation_stage() {
        return expatriation_stage;
    }

    public void setExpatriation_stage(String expatriation_stage) {
        this.expatriation_stage = expatriation_stage;
    }

    @Override
    public String toString() {
        return "InscriptionData{" +
                "prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", date_de_naissance=" + date_de_naissance +
                ", adresse='" + adresse + '\'' +
                ", code_postal='" + code_postal + '\'' +
                ", ville='" + ville + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", nationalite='" + nationalite + '\'' +
                ", diplome='" + diplome + '\'' +
                ", formation_type='" + formation_type + '\'' +
                ", amenagements='" + amenagements + '\'' +
                ", amenagementsInfo='" + amenagementsInfo + '\'' +
                ", tiers_temps='" + tiers_temps + '\'' +
                ", langages='" + langages + '\'' +
                ", projets_url='" + projets_url + '\'' +
                ", formation_interesse='" + formation_interesse + '\'' +
                ", choix_mns='" + choix_mns + '\'' +
                ", choix_formation='" + choix_formation + '\'' +
                ", projet_pro='" + projet_pro + '\'' +
                ", secteur_stage='" + secteur_stage + '\'' +
                ", qualites='" + qualites + '\'' +
                ", defauts='" + defauts + '\'' +
                ", passions='" + passions + '\'' +
                ", niveau_anglais='" + niveau_anglais + '\'' +
                ", autre_langue='" + autre_langue + '\'' +
                ", autre_langueInfo='" + autre_langueInfo + '\'' +
                ", permis_b='" + permis_b + '\'' +
                ", vehicule='" + vehicule + '\'' +
                ", mobilite='" + mobilite + '\'' +
                ", expatriation_stage='" + expatriation_stage + '\'' +
                ", connu_mns='" + connu_mns + '\'' +
                ", photo=" + photo +
                ", copie_id=" + copie_id +
                ", cv=" + cv +
                ", copie_diplome=" + copie_diplome +
                ", bulletins_notes=" + bulletins_notes +
                ", lettre_motivation=" + lettre_motivation +
                ", acceptation_politique=" + acceptation_politique +
                ", ancien_stagiaire_prenom='" + ancien_stagiaire_prenom + '\'' +
                ", ancien_stagiaire_nom='" + ancien_stagiaire_nom + '\'' +
                ", ancien_stagiaire_formation='" + ancien_stagiaire_formation + '\'' +
                '}';
    }
}
