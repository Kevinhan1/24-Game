package com.example.a24game;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Mendapatkan referensi ke EditText dan tombol Play
        nameEditText = findViewById(R.id.Name);
        Button playButton = findViewById(R.id.Playbutton);
        Button exitButton = findViewById(R.id.Exit);

        // Mengatur onClickListener untuk tombol Play
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog();
            }
        });

        // Mengatur onClickListener untuk tombol Exit
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Keluar dari aplikasi
                finish();
            }
        });

        // Mengatur padding untuk menangani insets jendela
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showConfirmationDialog() {
        String name = nameEditText.getText().toString();

        // Membuat dialog konfirmasi
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Name");
        builder.setMessage("Confirm name \"" + name + "\"?");

        // Tombol "Yes"
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Logika untuk melanjutkan ke permainan
                Intent intent = new Intent(MainActivity.this, PlayActivity.class);
                intent.putExtra("USER_NAME", name); // Mengirim nama ke aktivitas permainan
                startActivity(intent);
            }
        });

        // Tombol "No"
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Menutup dialog
            }
        });

        // Menampilkan dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}