package com.example.test.controlleur;

import android.content.Context;

import com.example.test.model.AccessLocal;
import com.example.test.model.Compte;
import com.example.test.model.Profil;


public class Controle {
    private static  Controle instance = null;
    private static Profil profil;
    Compte compte;
    private static AccessLocal accessLocal;

    private Controle() {
        super();
    }

    public static final Controle getInstance(Context context) {
        if (Controle.instance == null) {
            Controle.instance = new Controle();
            accessLocal= AccessLocal.getinstance(context);
           // profil=accessLocal.recupDernier();
        }
        return Controle.instance;
    }
   public String getNom(){return  compte.getNom();}
   public String getMail(){return  compte.getMail();}
   public String  getPsw(){return compte.getPsw();}

    public Integer  getTaille(){
        if (profil ==null) {
            return null;
        }
        else {
            return profil.getTaille();
        }
    }
    public Integer  getPoids(){
        if (profil ==null) {
            return null;
        }
        else {
            return profil.getPoids();
        }
    }
    public Integer  getAge() {
        if (profil == null) {
            return null;
        } else {
            return profil.getAge();
        }
    }
    public Integer  getSexe(){
        if (profil ==null) {
            return null;
        }
        else {
            return profil.getSexe();
        }
    }
    // methode pour creer une nouvelle profil
    public void creeCompte(String nom,String tel,String mail, String psw ,Context context) {
        compte = new Compte(nom,tel,mail,psw);
        accessLocal.ajoutCompte(compte);

    }
    public void creeProfil(Integer taille, Integer poids, Integer age, Integer sexe,String mail, Context context){
        profil=new Profil(taille, poids, age, sexe,mail);
        profil = new Profil(taille,poids,age,sexe,mail);
        accessLocal.ajoutProfil(profil,mail);
    }
    public  int validation(String mail, String psw){
        return accessLocal.loginValide(mail,psw);
    }

    public String getMessage() {
        return profil.getMsg();
    }


    public float getImg() {
        return profil.getImg();
    }

    public Profil recupererProfil(String mail){
        return accessLocal.recupDernier(mail);
    }
}
