package com.example.learner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    ImageButton person, people;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        checkUser();
        person = (ImageButton) findViewById(R.id.person_button);
        people = (ImageButton) findViewById(R.id.people_button);

        if(savedInstanceState == null){
            replaceFragment(new Person_Fragment());
        }

        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new Person_Fragment());
            }
        });

        people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new People_Fragment());
            }
        });

    }

     void logout(){
        mAuth.signOut();
        checkUser();
    }
    private  void replaceFragment(Fragment frag){
        FragmentManager fgm = getSupportFragmentManager();
        FragmentTransaction fgt = fgm.beginTransaction();
        fgt.replace(R.id.view_layout, frag);
        fgt.commit();
    }

    private void checkUser(){
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null){
            Intent i = new Intent(MainActivity.this,  User_Activity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }
    }
}