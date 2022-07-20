package com.goms.model;

public class Liv {

    private Integer id;
    private String titre;
    private String auteur;
    private Integer annee;
    private String edition;
    private String disponible;

    public Liv(Integer id, String titre, String auteur, Integer annee, String edition, String disponible) {
        this.id=id;
        this.titre = titre;
        this.auteur = auteur;
        this.annee = annee;
        this.edition = edition;
        this.disponible = disponible;
    }

    public Integer getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public Integer getAnnee() {
        return annee;
    }

    public String getEdition() {
        return edition;
    }

    public String getDisponible() {
        return disponible;
    }
}
