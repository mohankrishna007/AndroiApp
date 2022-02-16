
package com.example.learner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register_Activity extends AppCompatActivity {

    private EditText email, pass, fullname, college, year, department;
    private Button sign_up;

    private  String userId;

    private FirebaseAuth mAuth;
    private FirebaseFirestore mFireStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        email  = (EditText)findViewById(R.id.register_user_email);
        pass = (EditText)findViewById(R.id.register_user_password);
        college = (EditText) findViewById(R.id.register_user_college);
        fullname = (EditText) findViewById(R.id.register_user_name);
        year = (EditText)findViewById(R.id.register_user_year);
        department  = (EditText)findViewById(R.id.register_user_department);
        sign_up = (Button)findViewById(R.id.signup_submit);

        mAuth = FirebaseAuth.getInstance();
        mFireStore = FirebaseFirestore.getInstance();

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailText = email.getText().toString().trim();
                String passwordText = pass.getText().toString().trim();
                String fullnameText = fullname.getText().toString().trim();
                String collegeText = college.getText().toString().trim();
                String departmentText = department.getText().toString().trim();
                String yearText = year.getText().toString().trim();


                mAuth.createUserWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent i = new Intent(Register_Activity.this, MainActivity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    userId = mAuth.getCurrentUser().getUid();
                                    DocumentReference documentReference = mFireStore.collection("users").document(userId);
                                    User user  = new User(fullnameText, emailText, collegeText,departmentText, yearText);
                                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            startActivity(i);
                                            finish();
                                        }
                                    });
                                } else{
                                    Toast.makeText(Register_Activity.this,
                                            "Error :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                );
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null){
            Intent i = new Intent(Register_Activity.this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }
    }
}