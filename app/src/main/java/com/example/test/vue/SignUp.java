package com.example.test.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.controlleur.Controle;
import com.example.test.model.AccessLocal;
import com.example.test.model.Compte;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import static java.lang.Character.isDigit;

public class SignUp extends AppCompatActivity {
    private TextInputLayout nom, mail, tel, pswSign, cpsw;
    private Button bSignup;
    TextView su;
    AccessLocal accessLocal ;
    Compte compte;
    Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
    }


    public void init() {
        nom= (TextInputLayout) findViewById(R.id.nom);
        tel = (TextInputLayout) findViewById(R.id.tel);
        mail = (TextInputLayout) findViewById(R.id.mail);
        pswSign = (TextInputLayout) findViewById(R.id.pswSign);
        cpsw = (TextInputLayout) findViewById(R.id.cpsw);
        su=(TextView)findViewById(R.id.su);
        bSignup = (Button) findViewById(R.id.bSignup);
        controle=Controle.getInstance(this);

    }


            public void onClickSignup(View view) {
                // recuperation des editText
                String nomText = Objects.requireNonNull(nom.getEditText()).getText().toString();
                String telText = Objects.requireNonNull(tel.getEditText()).getText().toString();
                String mailText = Objects.requireNonNull(mail.getEditText()).getText().toString();
                String pswText = Objects.requireNonNull(pswSign.getEditText()).getText().toString();
                String cpswText = Objects.requireNonNull(cpsw.getEditText()).getText().toString();
                boolean testChamps = true;
                if (nomText.isEmpty()) {
                    testChamps = false;
                    nom.setHelperText("invalid name ");
                }
                if (telText.length()!=8) {
                    tel.setHelperText("invalide phone number ");
                    testChamps = false;
                }
                if (mailText.equals("") || !mailText.contains("@") || !mailText.contains(".") || mailText.indexOf(".") < mailText.indexOf("@")) {
                    mail.setHelperText("invalid e-mail ");
                    testChamps = false;
                }
                if (pswText.length() < 4) {
                    pswSign.setHelperText("password must contain at least 4 characters");
                    testChamps = false;
                }
                if (!cpswText.equals(pswText)) {
                    cpsw.setHelperText("please make sure your passwords match");
                    testChamps = false;
                }
                if (testChamps) {
                    // si toutes les champs est valider on creer une nouvelle profil et deplacer vers le page de login
                    controle.creeCompte(nomText,telText,mailText,pswText,this);

                    Intent intent = new Intent(SignUp.this,CalculateImg.class);
                    try {
                        intent.putExtra("mail", mailText);
                    }catch(Exception e){e.printStackTrace();}
                    Log.i("mail",mailText);
                    startActivity(intent);
                }
            }

        // le click sur texte Sign up
    public   void clickText(View view) {
        Intent intent = new Intent(SignUp.this, LoginActivity.class);
        startActivity(intent);
    }

}