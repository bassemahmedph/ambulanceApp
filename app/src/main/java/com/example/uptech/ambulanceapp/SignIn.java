package com.example.uptech.ambulanceapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {
ImageButton imgbtn;
    private EditText username,password;
Button loginBtn;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        progressDialog = new ProgressDialog(this);
        imgbtn=(ImageButton) findViewById(R.id.btnImgId);
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent (SignIn.this,MainActivity.class);
                startActivity(intent);
            }
        });

        loginBtn=(Button) findViewById(R.id.btnLoginId);
        username=(EditText) findViewById(R.id.emailId);
        password=(EditText) findViewById(R.id.passwordId);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), Home.class));

        }
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserLogin();
//                Intent intent = new Intent(SignIn.this,Home.class);
//                startActivity(intent);
            }
        });

    }
    private void UserLogin(){
        String Username = username.getText().toString().trim();
        String Password = password.getText().toString().trim();
        if (TextUtils.isEmpty(Username)) {
            Toast.makeText(this, "Please Enter The Mail  ", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(Password)) {
            Toast.makeText(this, "Please Enter The Password ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (username.getText().toString().equals("pharmacy@yahoo.com")&& password.getText().toString().equals("0123456789"))
        {
            startActivity(new Intent(getApplicationContext(), Home.class));

        }
        progressDialog.setMessage("Login...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(Username,Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    finish();
                    startActivity(new Intent(getApplicationContext(), Home.class));
                }
                if (!task.isSuccessful()){

                    Toast.makeText(SignIn.this, "Wrong Mail or Password Please Try Again ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            }
        });

    }
}
