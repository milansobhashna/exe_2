package com.example.exe_2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class RecyclerViewAdapterf2 extends RecyclerView.Adapter<RecyclerViewAdapterf2.ViewHolder> {

    ArrayList<Usr> usrArrayList;

    public RecyclerViewAdapterf2(ArrayList<Usr> usrArrayList) {
        this.usrArrayList = usrArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterf2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listitem = layoutInflater.inflate(R.layout.rv_item_user, parent, false);
        ViewHolder viewHolder = new ViewHolder(listitem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterf2.ViewHolder holder, int position) {
        holder.username.setText(usrArrayList.get(position).getUsername());
        holder.email.setText(usrArrayList.get(position).getEmail());
//        usrArrayList.get(position).toString();
    }

    @Override
    public int getItemCount() {
        return usrArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView username;
        TextView email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.rv_item_username);
            email = (TextView) itemView.findViewById(R.id.rv_item_email);

        }
    }
}
