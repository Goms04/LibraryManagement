package com.goms.model;

import java.time.LocalDate;
import java.util.Date;

public class Jour {
    private Integer id;
    private String titre;
    private Date date;

    public Integer getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public Date getDate() {
        return date;
    }



    public Jour(Integer id, String titre, Date date) {
        this.id = id;
        this.titre = titre;
        this.date = date;
    }

}
