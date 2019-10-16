package com.example.exe_2;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikhaellopez.circularimageview.CircularImageView;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private MyListData[] myListData;

    public RecyclerViewAdapter(MyListData[] myListData) {
        this.myListData = myListData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listitem = layoutInflater.inflate(R.layout.rv_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listitem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        MyListData myListData1=myListData[position];
        holder.imageView1.setImageResource(myListData[position].getImg());
        //AlertDialog.Builder builder=new AlertDialog.Builder();
        holder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView=new ImageView(v.getContext());
                imageView.setPadding(10,10,10,10);
                imageView.setImageResource(myListData[position].getImg());
                new AlertDialog.Builder(v.getContext()).setView(imageView).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return myListData.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        CircularImageView imageView1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView1 = (CircularImageView) itemView.findViewById(R.id.rv_item_img);
        }
    }
}
