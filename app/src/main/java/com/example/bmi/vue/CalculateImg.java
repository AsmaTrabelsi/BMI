package com.example.test.vue;

import android.content.Intent;
import android.os.Bundle;

import com.example.test.controlleur.Controle;
import com.example.test.model.Profil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.test.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class CalculateImg extends AppCompatActivity {
    private TextInputLayout taille,poids,age;
    private RadioButton rhomme;
    private RadioButton rfemme;
    Button btnCalculer ;
    Controle controle;
    static boolean test = false;
    private Button bSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_img);
        this.controle = Controle.getInstance(this);
        init();
        recupProfi();
        ecouteCalcul();

    }

    public void init() {
        taille = (TextInputLayout) findViewById(R.id.taille);
        poids = (TextInputLayout) findViewById(R.id.poids);
        age = (TextInputLayout) findViewById(R.id.age);
        rhomme = (RadioButton) findViewById(R.id.rh);
        rfemme = (RadioButton) findViewById(R.id.rf);
        btnCalculer=(Button)findViewById(R.id.btnCalculer) ;
    }

    private void ecouteCalcul() {
        btnCalculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean testChamps = true;
                int sexe = 0;
                int tailleInt = Integer.parseInt((taille.getEditText()).getText().toString());
                int poidsInt = Integer.parseInt((poids.getEditText()).getText().toString());
                int ageInt = Integer.parseInt((age.getEditText()).getText().toString());
                if (rhomme.isChecked())
                    sexe = 1;
                if (tailleInt < 30 || tailleInt > 220) {
                    taille.setHelperText("invalid height");
                    testChamps = false;
                }
                if (poidsInt < 10 || poidsInt > 300) {
                    poids.setHelperText("invalid weight");
                    testChamps = false;
                }
                if (ageInt < 1 || ageInt > 120) {
                    age.setHelperText("invalid age");
                    testChamps = false;
                }

                if (testChamps) {
                    Intent i = getIntent();
                    String mailTxt=i.getStringExtra("mail");
                    Intent intent = new Intent(CalculateImg.this, Resultat.class);


                    intent.putExtra("taille", tailleInt);
                    intent.putExtra("poids", poidsInt);
                    intent.putExtra("age", ageInt);
                    intent.putExtra("sexe", sexe);
                    Log.i("maillCalcul",mailTxt);
                    intent.putExtra("mail",mailTxt);
                    startActivity(intent);
                }
            }
        });

    }

  private void recupProfi() {
      Intent i = getIntent();
          String mailText = i.getStringExtra("mail");
          Profil profil = controle.recupererProfil(mailText);
          try {
              if (profil.getTaille() != null) {
                  taille.getEditText().setText(String.valueOf(profil.getTaille()));
                  poids.getEditText().setText(String.valueOf(profil.getPoids()));
                  age.getEditText().setText(String.valueOf(profil.getAge()));
                  if (profil.getSexe() == 1)
                      rhomme.setChecked(true);
              }
          }catch (Exception e){e.printStackTrace();}
  }

    public void onClickAnnuler(View view) {
    taille.getEditText().setText("");
    poids.getEditText().setText("");
    age.getEditText().setText("");
    rfemme.setChecked(true);

    }
}