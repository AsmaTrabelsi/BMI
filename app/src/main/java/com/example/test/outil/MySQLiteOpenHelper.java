package com.example.test.outil;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.sql.Statement;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    // creation de table compte
    private final String compte = "create table if not exists compte ("
            +"nom TEXT NOT NULL,"
            +"tel TEXT NOT NULL,"
            + "mail TEXT PRIMARY KEY,"
            + "psw  TEXT NOT NULL)";
    private final String creationProfil="create table creationProfil("
            +"taille INT NOT NULL,"
            +"poids INT NOT NULL, "
            +"age INT NOT NULL,"
            +"sexe INT NOT NULL,"
            +"mail TEXT PRIMARY KEY,"
            +"FOREIGN KEY(mail) REFERENCES compte(mail))";

    public MySQLiteOpenHelper(Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(compte);
        db.execSQL(creationProfil);
    }
    public void insertCompte(ContentValues contentValues,String name){
        getWritableDatabase().insert(name,"",contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
