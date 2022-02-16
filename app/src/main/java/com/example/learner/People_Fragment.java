package com.example.learner;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class People_Fragment extends Fragment {

    EditText college, department, year;
    Button filter;

    RecyclerView recyclerView;
    ArrayList<User> userArrayList;
    MyAdapter myAdapter;
    FirebaseFirestore mFireStore;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_people_, container, false);

        recyclerView = view.findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        userArrayList = new ArrayList<>();
        mFireStore =FirebaseFirestore.getInstance();
        myAdapter = new MyAdapter(userArrayList);
        recyclerView.setAdapter(myAdapter);
        college = (EditText)view.findViewById(R.id.filter_college);
        department = (EditText)view.findViewById(R.id.filter_department);
        year = (EditText)view.findViewById(R.id.filter_year);
        filter = (Button)view.findViewById(R.id.perfom_filter);


        CollectionReference con = mFireStore.collection("users");
        retrieveData(con);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query = filterData(con);
                userArrayList.clear();
                myAdapter.notifyDataSetChanged();
                if(query != null){
                    query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d : list){
                                User u = d.toObject(User.class);
                                userArrayList.add(u);
                            }
                            myAdapter.notifyDataSetChanged();
                        }
                    });
                } else{
                    retrieveData(con);
                }
            }
        });

        return view;
    }

    private void retrieveData(CollectionReference con) {
        con.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot d : list){
                    User u = d.toObject(User.class);
                    userArrayList.add(u);
                }
                myAdapter.notifyDataSetChanged();
            }
        });
    }

    private Query filterData(CollectionReference con){
        Query query = null;
        String clg = college.getText().toString().trim();
        String dept = department.getText().toString().trim();
        String yr = year.getText().toString().trim();
        System.out.println(clg+" "+dept+" "+yr);

        if(!TextUtils.isEmpty(clg)){
           query = con.whereEqualTo("college", clg);
        }
        if(!TextUtils.isEmpty(dept)){
            query = con.whereEqualTo("department", dept);
        }
        if(!TextUtils.isEmpty(yr)){
            query = con.whereEqualTo("year", yr);
        }

        college.setText("");
        department.setText("");
        year.setText("");

        return query;
    }
}