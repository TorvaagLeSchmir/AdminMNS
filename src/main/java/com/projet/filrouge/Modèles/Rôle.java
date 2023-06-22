package com.projet.filrouge.Modèles;

public class Rôle {

    private int id_statut;
    private String nom;

    public Rôle() {
    }

    public Rôle(int id_statut) {
        this.id_statut = id_statut;
    }

    public int getId_statut() {
        return id_statut;
    }

    public void setId_statut(int id_statut) {
        this.id_statut = id_statut;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
