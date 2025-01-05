package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Récupération des données transmises par MainActivity
        Intent intent = getIntent();
        String utilisateur = intent.getStringExtra("Utilisateur");
        HashMap<String, HashMap<String, String>> offres =
                (HashMap<String, HashMap<String, String>>) intent.getSerializableExtra("offres");

        if (utilisateur == null || offres == null) {
            Toast.makeText(this, "Données manquantes.", Toast.LENGTH_SHORT).show();
            return;
        }

        TextView textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.voir_les_offres);
        button.setOnClickListener(v -> {
            Intent intentToMain3 = new Intent(MainActivity2.this, MainActivity3.class);
            intentToMain3.putExtra("Utilisateur", utilisateur);
            intentToMain3.putExtra("offres", offres);
            startActivity(intentToMain3);
        });
        Button buttonmrcedes = findViewById(R.id.button);
        buttonmrcedes.setOnClickListener(v -> {
            Intent intentToMain3 = new Intent(MainActivity2.this, MainActivity5.class);
            intentToMain3.putExtra("Utilisateur", utilisateur);
            intentToMain3.putExtra("offres", offres);
            startActivity(intentToMain3);
        });
        Button buttonaudi = findViewById(R.id.audibttn);
        buttonaudi.setOnClickListener(v -> {
            Intent intentToMain3 = new Intent(MainActivity2.this, MainActivity6.class);
            intentToMain3.putExtra("Utilisateur", utilisateur);
            intentToMain3.putExtra("offres", offres);
            startActivity(intentToMain3);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
