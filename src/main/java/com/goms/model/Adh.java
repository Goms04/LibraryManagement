package com.goms.model;

public class Adh {
    private Integer id;
    private  String nom;
    private String prenom;
    private String profession;
    private Integer tel;
    private String email;

    public Adh(Integer id, String nom, String prenom, String profession, Integer telephone, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.profession = profession;
        this.tel = telephone;
        this.email = email;
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

    public String getProfession() {
        return profession;
    }

    public Integer getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }
}
