package com.example.test.model;

public class Compte {
    private String mail,psw,nom,tel;

    public Compte(String nom, String tel, String mail, String psw) {
        this.mail = mail;
        this.psw = psw;
        this.nom = nom;
        this.tel = tel;
    }
    public String getNom() {
        return nom;
    }

    public String getTel() {
        return tel;
    }

    public String getMail() {
        return mail;
    }

    public String getPsw() {
        return psw;
    }


}
