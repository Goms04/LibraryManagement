package com.goms.model;

public class Bnd {
    private Integer id;
    private String titre;
    private String dessinateur;

    public Bnd(Integer id, String titre, String dessinateur) {
        this.id = id;
        this.titre = titre;
        this.dessinateur = dessinateur;
    }

    public Integer getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDessinateur() {
        return dessinateur;
    }
}
