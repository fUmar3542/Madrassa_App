package com.example.madrassah_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class myRecyclerViewAdapter extends RecyclerView.Adapter<myRecyclerViewAdapter.MyVH> {

    List<Student> StudentsList;
    public myRecyclerViewAdapter(List<Student> StudentsList) {
        this.StudentsList = StudentsList;
    }

    @NonNull
    @Override
    public myRecyclerViewAdapter.MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_single_item, parent, false);
        return new MyVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull myRecyclerViewAdapter.MyVH holder, int position) {
        holder.data=StudentsList.get(position);
        holder.textViewName.setText(holder.data.getName());
        holder.textViewRollNo.setText(holder.data.getRollNo());
        holder.textViewSabaq.setText(holder.data.getSabaq());
        holder.textViewSabqi.setText(holder.data.getSabqi());
        holder.textViewManzil.setText(holder.data.getManzil());
    }

    @Override
    public int getItemCount() {
        return StudentsList.size();
    }


    public class MyVH extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewRollNo;
        TextView textViewSabaq;
        TextView textViewSabqi;
        TextView textViewManzil;
        Student data;
        public MyVH(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewRollNo = itemView.findViewById(R.id.textViewRollNo);
            textViewSabaq = itemView.findViewById(R.id.textViewSabaq);
            textViewSabqi = itemView.findViewById(R.id.textViewSabqi);
            textViewManzil = itemView.findViewById(R.id.textViewManzil);
        }
    }
}