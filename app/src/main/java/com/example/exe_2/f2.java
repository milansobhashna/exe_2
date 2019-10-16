package com.example.exe_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class f2 extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.f2, container, false);

        MyListData[] myListData = new MyListData[]{
                new MyListData(R.drawable.img1),
                new MyListData(R.drawable.img2),
                new MyListData(R.drawable.img3),
                new MyListData(R.drawable.img4),
                new MyListData(R.drawable.img5),
                new MyListData(R.drawable.img1),
                new MyListData(R.drawable.img2),
                new MyListData(R.drawable.img3),
                new MyListData(R.drawable.img4),
                new MyListData(R.drawable.img5),
                new MyListData(R.drawable.img1),
                new MyListData(R.drawable.img2),
                new MyListData(R.drawable.img3),
                new MyListData(R.drawable.img4),
                new MyListData(R.drawable.img5),
                new MyListData(R.drawable.img1),
                new MyListData(R.drawable.img2),
                new MyListData(R.drawable.img3),
                new MyListData(R.drawable.img4),
                new MyListData(R.drawable.img5),

        };
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.f2_rv1);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }
}
