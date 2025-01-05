package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        HashMap<String, HashMap<String, String>> offres;
        EditText utilisateurname = findViewById(R.id.editTextText2);

        if (getIntent().hasExtra("offres")) {
            offres = (HashMap<String, HashMap<String, String>>) getIntent().getSerializableExtra("offres");
        } else {
            offres = new HashMap<>();
        }

        Button voir_les_offres= findViewById(R.id.voir_les_offres);
        voir_les_offres.setOnClickListener(v -> {
            String utilisateur = utilisateurname.getText().toString().trim();


            if (utilisateur.isEmpty()) {
                Toast.makeText(MainActivity.this, "Veuillez entrer un nom d'utilisateur.", Toast.LENGTH_SHORT).show();
                return;
            }


            if (!offres.containsKey(utilisateur)) {
                offres.put(utilisateur, new HashMap<>());
            }
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("Utilisateur", utilisateur);
            intent.putExtra("offres", offres);
            startActivity(intent);
        });
    }
}
