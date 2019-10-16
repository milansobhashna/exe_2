package com.example.exe_2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class f1 extends Fragment {

    SqlLiteDbHelper sqlLiteDbHelper = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.f1, container, false);
        sqlLiteDbHelper = new SqlLiteDbHelper(getContext());
        List<Usr> usrs = sqlLiteDbHelper.getAllContacts();
        ArrayList<Usr> usrArrayList= (ArrayList<Usr>) usrs;
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.f1_rv1);
        RecyclerViewAdapterf2 recyclerViewf2Adapter = new RecyclerViewAdapterf2(usrArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerViewf2Adapter);

        return v;
    }
}
