package com.study.encurtador.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity(name = "urls")
@Table(name = "TB_URLS")
public class URLModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)


    //Colunas da Tabela
    private UUID idUrl;
    private String originalUrl;


    public UUID getIdUrl() {
        return idUrl;
    }

    public void setIdUrl(UUID idUrl) {
        this.idUrl = idUrl;
    }

    public String getBigUrl() {
        return originalUrl;
    }

    public void setBigUrl(String bigUrl) {
        this.originalUrl = bigUrl;
    }

}
