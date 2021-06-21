package com.example.test.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.test.outil.MySQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class AccessLocal {
    String nameBase = "ProfilDb";
    static int versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase db;
    private static AccessLocal accessLocal;

    private AccessLocal(Context contexte) {
        accesBD = new MySQLiteOpenHelper(contexte, nameBase, null, versionBase);
    }

    public static AccessLocal getinstance(Context context) {
        if (accessLocal == null) {
            accessLocal = new AccessLocal(context);
        }
        return accessLocal;
    }

    // ajouter une nouvelle compte
    public void ajoutCompte(Compte cmpt) {
        try {
            db = accesBD.getWritableDatabase();
            if (loginValide(cmpt.getMail(), cmpt.getPsw()) != 1) {

                String req = "insert into compte (nom,tel,mail,psw) values(" + "\"" + cmpt.getNom() + "\"," + "\"" + cmpt.getTel() + "\"," + "\"" + cmpt.getMail() + "\"," + "\"" + cmpt.getPsw() + "\")";
                db.execSQL(req);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public int loginValide(String mail, String psw) {
        String req = "select count(*) from compte where mail='" + mail + "' and psw='" + psw + "' ";
        int i = -1;
        try {
            SQLiteDatabase db = accesBD.getReadableDatabase();

            Cursor c = db.rawQuery(req, null);
            c.moveToFirst();
            i = c.getInt(0);
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public void ajoutProfil(Profil profil,String mail) {
        try {
            db = accesBD.getWritableDatabase();
            String req = "insert into creationProfil (taille,poids,age,sexe,mail) values(" + profil.getTaille() + "," + profil.getPoids() + "," + profil.getAge() + "," + profil.getSexe() + ",\"" +mail+"\")";
            db.execSQL(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Profil recupDernier(String mailText) {
        db = accesBD.getReadableDatabase();
        Profil profil = null;
        String req = "select * from creationProfil where mail= '"+mailText+"'";
        Cursor curseur = db.rawQuery(req,null);
        int i=curseur.getCount();
        curseur.moveToLast();
        if (i!=0) {
            Integer taille = curseur.getInt(0);
            Integer poids = curseur.getInt(1);
            Integer age = curseur.getInt(2);
            Integer sexe = curseur.getInt(3);
            String mail = curseur.getString(4);
            profil = new Profil( taille, poids, age, sexe,mail);
        }
        curseur.close();
        return profil;
    }


}