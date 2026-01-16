package com.example.serviceapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProviderAdapter adapter;
    List<ServiceProvider> providerList = new ArrayList<>();
    FirebaseFirestore db;
    EditText searchBox;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        searchBox = findViewById(R.id.searchBox);
        FloatingActionButton fab = findViewById(R.id.fabAdd);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProviderAdapter(this, providerList);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        loadProviders();

        fab.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, AddProviderActivity.class))
        );

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterProviders(s.toString());
            }
        });
    }

    private void loadProviders() {
        db.collection("providers")
                .addSnapshotListener((value, error) -> {
                    providerList.clear();
                    if (value != null) {
                        for (DocumentSnapshot doc : value.getDocuments()) {
                            ServiceProvider p = doc.toObject(ServiceProvider.class);
                            if (p != null) {
                                providerList.add(p);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    private void filterProviders(String text) {
        List<ServiceProvider> filtered = new ArrayList<>();
        for (ServiceProvider p : providerList) {
            if (p.name != null &&
                p.name.toLowerCase().contains(text.toLowerCase())) {
                filtered.add(p);
            }
        }
        recyclerView.setAdapter(new ProviderAdapter(this, filtered));
    }
                             }
