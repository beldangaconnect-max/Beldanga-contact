package com.example.serviceapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class AddProviderActivity extends AppCompatActivity {

    EditText etName, etPhone, etService, etArea;
    Button btnSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_provider);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etService = findViewById(R.id.etService);
        etArea = findViewById(R.id.etArea);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> saveProvider());
    }

    private void saveProvider() {

        String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String service = etService.getText().toString().trim();
        String area = etArea.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Name and Phone required", Toast.LENGTH_SHORT).show();
            return;
        }

        ServiceProvider provider =
                new ServiceProvider(name, phone, service, area, true);

        FirebaseFirestore.getInstance()
                .collection("providers")
                .add(provider)
                .addOnSuccessListener(doc -> {
                    Toast.makeText(this, "Provider Added", Toast.LENGTH_SHORT).show();
                    finish(); // back to main screen
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
    }
}
