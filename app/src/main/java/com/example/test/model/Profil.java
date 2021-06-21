package com.example.test.model;

public class Profil {
    private  String mail;
    private int taille, poids, age, sexe;
    private float img;
    private String message;

     // constructeur de class Profil
    public Profil(Integer taille, Integer poids, Integer age, Integer sexe,String mail) {
        this.taille = taille;
        this.poids = poids;
        this.age = age;
        this.sexe= sexe;
        this.mail=mail;
        this.img = calcul(poids,taille, age, sexe);
    }
    public Integer getTaille() {
        return taille;
    }

    public Integer getPoids() {
        return poids;
    }

    public Integer getAge() {
        return age;
    }

    public int getSexe() { return sexe; }
    public String getMail(){return mail;}
    public float getImg() { return calcul(taille,poids, age, sexe); }

    public String getMessage() {
        return message;
    }
    // methode pour calculer IMG
    public float calcul( Integer taille,Integer poids, Integer age, Integer sexe) {
        float tailleN = ((float) taille) / 100;
        this.img = (float) ((1.2 * poids / (tailleN * tailleN)) + (0.23 * age) - (10.83 * sexe) - 5.4);
        return img;
    }
    public String getMsg() {
        int min;
        int max;
        if(sexe==0){
            min=25;
            max=30;}
        else{
            min=15;
            max=20;

        }
        message="normal";
        if(img<min){
            message="very low";
        }
        else{
            message="You have too much body fat";
        }
        return message ;
    }
}

