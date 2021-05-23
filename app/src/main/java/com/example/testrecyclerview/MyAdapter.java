package com.example.testrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final ArrayList <ItemClass> myList;

    private OnRecyclerViewClickListener listener;

    public interface OnRecyclerViewClickListener{
        void OnItemClick(int position);
    }

    public void OnRecyclerViewClickListener (OnRecyclerViewClickListener listener){
        this.listener = listener;
    }

    //--> Constructor
    public MyAdapter(ArrayList<ItemClass> myList) {
        this.myList = myList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_template,parent,false);
        return new MyViewHolder(view, listener);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ItemClass item = myList.get(position);
        holder.imageView_img.setImageResource(item.getImg());
        holder.textView_name.setText(item.getName());
        holder.textView_email.setText(item.getEmail());
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }


    //--> My ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView_img;
        public TextView textView_name;
        public TextView textView_email;

        //--> Constructor
        public MyViewHolder(@NonNull View itemView, OnRecyclerViewClickListener listener) {
            super(itemView);

            imageView_img = itemView.findViewById(R.id.id_img_CardView);
            textView_name = itemView.findViewById(R.id.id_name_CardView);
            textView_email = itemView.findViewById(R.id.id_email_CardView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null && getAbsoluteAdapterPosition()!=RecyclerView.NO_POSITION){
                        listener.OnItemClick(getAbsoluteAdapterPosition());
                    }
                }
            });
        }
    }

}
