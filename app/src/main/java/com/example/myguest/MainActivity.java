package com.example.myguest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private Button btnLogout;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Inisialisasi komponen UI
        tvWelcome = findViewById(R.id.tv_welcome);
        btnLogout = findViewById(R.id.btn_logout);

        // Cek apakah pengguna sudah masuk
        boolean isLoggedIn = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
        if (isLoggedIn) {
            // Tampilkan pesan selamat datang
            String email = sharedPreferences.getString(KEY_EMAIL, "Pengguna");
            String welcomeMessage = "Selamat datang, " + email + "!";
            tvWelcome.setText(welcomeMessage);
        } else {
            // Jika tidak ada pengguna yang masuk, kembali ke layar login
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }

        // Tambahkan event listener untuk tombol logout
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set status pengguna menjadi logout
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(KEY_IS_LOGGED_IN, false);
                editor.putString(KEY_EMAIL, null);
                editor.apply();

                Toast.makeText(MainActivity.this, "Logout berhasil", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}

