package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);

        String utilisateur = getIntent().getStringExtra("Utilisateur");
        HashMap<String, HashMap<String, String>> offres =
                (HashMap<String, HashMap<String, String>>) getIntent().getSerializableExtra("offres");

        TextView textView = findViewById(R.id.text);
        textView.setText(utilisateur);

        LinearLayout container = findViewById(R.id.container);


            for (String key : offres.get(utilisateur).keySet()) {
                LinearLayout horizontalLayout = new LinearLayout(this);
                horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
                horizontalLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                LinearLayout.LayoutParams childLayoutParams = new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                );
                TextView voiture = new TextView(this);
                Integer idvoiture = View.generateViewId();
                voiture.setId(idvoiture);
                voiture.setText(key);
                voiture.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                voiture.setLayoutParams(childLayoutParams);

                TextView date = new TextView(this);
                Integer idDate = View.generateViewId();
                date.setId(idDate);
                date.setText(offres.get(utilisateur).get(key));
                date.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                date.setLayoutParams(childLayoutParams);

                // Create the Button
                Button button = new Button(this);
                Integer idButton = View.generateViewId();
                button.setId(idButton);
                button.setText("Enlever");
                button.setOnClickListener(v -> {
                    offres.get(utilisateur).remove(key);
                    horizontalLayout.removeView(findViewById(idButton));
                    horizontalLayout.removeView(findViewById(idvoiture));
                    horizontalLayout.removeView(findViewById(idDate));
                });
                button.setLayoutParams(childLayoutParams);

                // Add the TextViews and Button to the horizontal LinearLayout
                horizontalLayout.addView(voiture);
                horizontalLayout.addView(date);
                horizontalLayout.addView(button);

                // Add the horizontal LinearLayout to the parent LinearLayout
                container.addView(horizontalLayout);

            }

        Button buttonacc = findViewById(R.id.button6);
        buttonacc.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity4.this, MainActivity.class);
            intent.putExtra("Utilisateur", utilisateur);
            intent.putExtra("offres", offres);
            startActivity(intent);
        });
        Button buttonoffre = findViewById(R.id.button7);
        buttonoffre.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity4.this, MainActivity3.class);
            intent.putExtra("Utilisateur", utilisateur);
            intent.putExtra("offres", offres);
            startActivity(intent);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
