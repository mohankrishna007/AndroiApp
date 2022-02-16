package com.example.learner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class AddBadge extends AppCompatActivity {

    RadioGroup rg;
    EditText input;
    Button submit;

    FirebaseAuth mAuth;
    FirebaseFirestore mFireStore;

    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_badge);

         rg = (RadioGroup) findViewById(R.id.radioGroup);
         submit = (Button)findViewById(R.id.submit_badge);
         input = (EditText)findViewById(R.id.user_input_text);

         mAuth = FirebaseAuth.getInstance();
         mFireStore = FirebaseFirestore.getInstance();
         userId = mAuth.getCurrentUser().getUid();

         submit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent i = new Intent(AddBadge.this, MainActivity.class);
                 startActivity(i);
                 finish();
             }
         });

    }
}