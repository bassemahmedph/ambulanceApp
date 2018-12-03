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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    ImageButton imgbtn;
    Button signup;
    private EditText name,mail,phone,password,repassword;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("s");


        progressDialog = new ProgressDialog(this);

        imgbtn=(ImageButton) findViewById(R.id.btnImgId);
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent (SignUp.this,MainActivity.class);
                startActivity(intent);
            }
        });

        signup= (Button) findViewById(R.id.btnSignupId);
        name = (EditText) findViewById(R.id.NameId);
        mail = (EditText) findViewById(R.id.mailId);
        phone = (EditText) findViewById(R.id.phoneId);
        password = (EditText) findViewById(R.id.passId);
        repassword = (EditText) findViewById(R.id.repassId);


        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), Home.class));
        }
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {

        String FirstName = name.getText().toString().trim();
        String Mail = mail.getText().toString().trim();
        String Phone = phone.getText().toString().trim();
        String Password = password.getText().toString().trim();
        String Repassword = repassword.getText().toString().trim();


        if (TextUtils.isEmpty(FirstName)) {
            Toast.makeText(this, "please enter first name ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Mail)) {
            Toast.makeText(this, "please enter mail ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Phone)) {
            Toast.makeText(this, "please phone ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Password)) {
            Toast.makeText(this, "please enter password ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Repassword)) {
            Toast.makeText(this, "please re-type password ", Toast.LENGTH_SHORT).show();
            return;
        }



        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(Mail, Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    saveUserInformation();
                    finish();
//                    Toast.makeText(sign_up.this, "done ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Home.class));

                } else {
                    Toast.makeText(SignUp.this, "something wronge ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void saveUserInformation() {
        String Name = name.getText().toString().trim();
        // String lastname = LastName.getText().toString().trim();
        String Email = mail.getText().toString().trim();
        String Password = password.getText().toString().trim();
        String Repassword = repassword.getText().toString().trim();
        String Phone = phone.getText().toString().trim();
        String id = firebaseAuth.getCurrentUser().getUid();
        ModelSignUp modelSignUp = new ModelSignUp(Name, Email, Phone, Password,Repassword);
        databaseReference.child(id).setValue(modelSignUp);


    }
}
