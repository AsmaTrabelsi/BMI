package com.example.test.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.controlleur.Controle;
import com.example.test.model.AccessLocal;

import java.util.Date;
import java.util.List;

public class Resultat extends AppCompatActivity {
    private static final String MY_PREFS_NAME = "";
    private TextView resultatIMG, textMessage;
    public Button bRetour;
    private ImageView imgRes;
    private Controle controle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        init();
        this.controle=Controle.getInstance(this);
        afficheResultat();
        ecouteRetour();
    }

    private void init() {
        resultatIMG = (TextView)findViewById(R.id.resultatIMG);
        textMessage = (TextView) findViewById(R.id.textMessage);
        bRetour=(Button) findViewById(R.id.bRetour) ;
        imgRes=(ImageView) findViewById(R.id.imgRes);
        this.controle= Controle.getInstance(this);
    }

    private void afficheResultat() {
        Intent intent = getIntent();
        Integer taille = intent.getIntExtra("taille",-1);
        Integer poids = intent.getIntExtra("poids", -1);
        Integer age = intent.getIntExtra("age", -1);
        Integer sexe = intent.getIntExtra("sexe",-1);

        // ...................................................
            String mail = intent.getStringExtra("mail");
            this.controle.creeProfil(taille, poids, age, sexe, mail, this);
            float img = this.controle.getImg();
            String message = this.controle.getMessage();
            Integer s = this.controle.getSexe();


        if (message.equals("normal")) {
            textMessage.setTextColor(Color.GREEN);
            if(s==1)
            imgRes.setImageResource(R.drawable.normal);
            else
                imgRes.setImageResource(R.drawable.fnormal);
        }
        else if (message.equals("very low")) {
                textMessage.setTextColor(getResources().getColor(R.color.yellow));
                if(s==1)
                    imgRes.setImageResource(R.drawable.small);
                else
                    imgRes.setImageResource(R.drawable.fsmall);
            }
        else {
                textMessage.setTextColor(Color.RED);
                if(s==1)
                    imgRes.setImageResource(R.drawable.big);
                else
                    imgRes.setImageResource(R.drawable.fbig);


            }

        resultatIMG.setText("Votre IMG Est: "+String.valueOf(img));
        textMessage.setText(message);

    }

    private void ecouteRetour(){
        bRetour.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Resultat.this,CalculateImg.class);
                startActivity(intent);
            }
        });
    }
    }