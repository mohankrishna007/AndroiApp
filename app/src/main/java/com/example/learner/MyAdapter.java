package com.example.learner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<User> userArrayList;

    public MyAdapter(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()
        ).inflate(R.layout.item, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = userArrayList.get(position);
        holder.badge.setText(user.getFullname());
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView badge, skill, award, job;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            badge = (TextView)itemView.findViewById(R.id.user_badge);
            skill = (TextView)itemView.findViewById(R.id.skill_count);
            award = (TextView)itemView.findViewById(R.id.award_count);
            job = (TextView)itemView.findViewById(R.id.jobs_count);
        }
    }
}
