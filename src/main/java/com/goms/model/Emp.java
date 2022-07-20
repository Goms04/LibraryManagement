package com.goms.model;

public class Emp {
 private Integer id;
 private String nom;
 private String prenom;
 private String titre;
 private int tel;
 private String auteur;
 private String dateE;
 private String dateR;



    public Emp(int id, String nom, String prenom, int tel, String titre, String auteur, String dateE, String dateR) {
        this.id = id;
        this.nom = nom;
        this.auteur=auteur;
        this.prenom = prenom;
        this.tel=tel;
        this.titre = titre;
        this.dateE = dateE;
        this.dateR = dateR;
    }

    public String getAuteur() {
        return auteur;
    }

    public int getTel() {
        return tel;
    }

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTitre() {
        return titre;
    }

    public String getDateE() {
        return dateE;
    }

    public String getDateR() {
        return dateR;
    }
}
