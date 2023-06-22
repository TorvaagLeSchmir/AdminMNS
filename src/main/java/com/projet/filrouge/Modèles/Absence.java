package com.projet.filrouge.Modèles;

import java.sql.Date;
import java.time.LocalDateTime;

public class Absence {
    private int id;
    private LocalDateTime dateDébut;
    private LocalDateTime dateFin;
    private Date dateDéclaration;
    private String motif;
    private String justificatifTxt;
    private boolean isRetard;
    private boolean isChecked;
    private boolean isJustified;

    public Absence() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public LocalDateTime getDateDébut() {
        return dateDébut;
    }

    public void setDateDébut(LocalDateTime dateDébut) {
        this.dateDébut = dateDébut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public Date getDateDéclaration() {
        return dateDéclaration;
    }

    public void setDateDéclaration(Date dateDéclaration) {
        this.dateDéclaration = dateDéclaration;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getJustificatifTxt() {
        return justificatifTxt;
    }

    public void setJustificatifTxt(String justificatifTxt) {
        this.justificatifTxt = justificatifTxt;
    }

    public boolean isRetard() {
        return isRetard;
    }

    public void setRetard(boolean retard) {
        isRetard = retard;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isJustified() {
        return isJustified;
    }

    public void setJustified(boolean justified) {
        isJustified = justified;
    }
}
