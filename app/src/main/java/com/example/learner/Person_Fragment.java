package com.example.learner;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Person_Fragment extends Fragment {

    private TextView fullname;
    private EditText email, college, department, year;
    private Button addbadge, logout;
    private TextView badge;

    private FirebaseFirestore mFireStore;
    private FirebaseAuth mAuth;
    private String userId;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_person_, container, false);

        email = (EditText)view.findViewById(R.id.show_user_email);
        college = (EditText)view.findViewById(R.id.show_user_college);
        fullname = (TextView) view.findViewById(R.id.show_fullname);
        department = (EditText)view.findViewById(R.id.show_user_department);
        year = (EditText)view.findViewById(R.id.show_user_year);
        badge = (TextView)view.findViewById(R.id.person_actual_badges);
        addbadge = (Button)view.findViewById(R.id.add_badge);
        logout = (Button)view.findViewById(R.id.logout_button);


        mAuth = FirebaseAuth.getInstance();
        mFireStore = FirebaseFirestore.getInstance();

        if(mAuth != null){
            userId = mAuth.getCurrentUser().getUid();
            DocumentReference documentReference = mFireStore.collection("users").document(userId);
            documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    if(value != null && value.exists()){
                        User user = value.toObject(User.class);
                        email.setText(user.getEmail());
                        fullname.setText(user.getFullname());
                        college.setText(user.getCollege());
                        department.setText(user.getDepartment());
                        year.setText(user.getYear());
                    }
                }
            });
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).logout();
            }
        });

        addbadge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AddBadge.class);
                startActivity(i);
            }
        });
        return view;
    }
}