package com.example.test.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.controlleur.Controle;
import com.example.test.model.AccessLocal;
import com.example.test.model.Compte;
import com.example.test.model.Profil;
import com.example.test.outil.MySQLiteOpenHelper;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private Button bLogin;
    TextInputLayout mail, psw;
    TextView validation ;
    Controle controle;
    AccessLocal accessLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        init();
        ecouteCalcul();
    }
        private void ecouteCalcul() {
        bLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // recupere les EditText
                String mailText = mail.getEditText().getText().toString();
                String pswText = psw.getEditText().getText().toString();
                if(mailText.equals("")||pswText.equals("")){
                    validation.setText("please fill in all login details");
                    validation.setVisibility(View.VISIBLE);
                }
                // comparer mail et psw avec les enregistrement  existe
                else if(controle.validation(mailText,pswText)!=1){
                    validation.setText("invalid email or password");
                    validation.setVisibility(View.VISIBLE);
                }
                else{
                    validation.setText("");
                    Intent intent = new Intent(LoginActivity.this,CalculateImg.class);
                    try {
                        intent.putExtra("mail", mailText);
                    }catch(Exception e){e.printStackTrace();}
                    Log.i("mailLogin",mailText);
                    startActivity(intent);}
                }

            }
        );

    }

    public void init() {
        mail = (TextInputLayout) findViewById(R.id.mail);
        psw = (TextInputLayout) findViewById(R.id.psw);
        bLogin = (Button) findViewById(R.id.bLogin);
        validation=(TextView)findViewById(R.id.validation);
        controle=Controle.getInstance(this);
    }




    public void clickText(View view) {
        // le click sur texte Login si l'utilisateur ne possede pas une compte
        Intent intent = new Intent(LoginActivity.this,SignUp.class);
            startActivity(intent);
        }
}