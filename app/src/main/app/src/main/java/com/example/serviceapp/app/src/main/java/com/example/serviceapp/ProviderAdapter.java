package com.example.serviceapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProviderAdapter extends RecyclerView.Adapter<ProviderAdapter.Holder> {

    Context context;
    List<ServiceProvider> list;

    public ProviderAdapter(Context context, List<ServiceProvider> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_provider, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        ServiceProvider sp = list.get(position);

        holder.name.setText(sp.name);
        holder.phone.setText(sp.phone);
        holder.service.setText(sp.service);

        holder.callBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + sp.phone));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class Holder extends RecyclerView.ViewHolder {

        TextView name, phone, service;
        Button callBtn;

        Holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtName);
            phone = itemView.findViewById(R.id.txtPhone);
            service = itemView.findViewById(R.id.txtService);
            callBtn = itemView.findViewById(R.id.btnCall);
        }
    }
          }
