package com.example.myproject;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.HashMap;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main5);

        String utilisateur = getIntent().getStringExtra("Utilisateur");
        HashMap<String, HashMap<String, String>> offres =
                (HashMap<String, HashMap<String, String>>) getIntent().getSerializableExtra("offres");

        TextView textView = findViewById(R.id.textView7);

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                textView.setText(i + "/" + i1 + "/" + i2);
            }
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT, dateSetListener, year, month, day);

        Button btn = findViewById(R.id.button2);
        btn.setOnClickListener(v -> {
            datePickerDialog.show();
        });

        Button btnRetour = findViewById(R.id.button3);
        btnRetour.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity5.this, MainActivity.class);
            intent.putExtra("Utilisateur", utilisateur);
            intent.putExtra("offres", offres);
            startActivity(intent);
        });

        Button btnSave = findViewById(R.id.button4);
        btnSave.setOnClickListener(v -> {
            LinearLayout checkboxesContainer = findViewById(R.id.checkboxes_container);
            for (int i = 0; i < checkboxesContainer.getChildCount(); i++) {
                View child = checkboxesContainer.getChildAt(i);
                if (child instanceof CheckBox) {
                    if (((CheckBox) child).isChecked()) {
                        offres.get(utilisateur).put(((CheckBox) child).getText().toString(), textView.getText().toString());
                        ((CheckBox) child).setChecked(false);
                        Toast.makeText(MainActivity5.this, "voitures ajoute : " + ((CheckBox) child).getText().toString() + " dans le jour du " + textView.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        Button demandebtn = findViewById(R.id.button5);
        demandebtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity5.this, MainActivity4.class);
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
