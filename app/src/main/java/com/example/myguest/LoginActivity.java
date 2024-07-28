package com.example.myguest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin, btnRegister; // Tambahkan deklarasi btnRegister
    private ProgressBar progressBar;

    // Dummy credentials for example purposes
    private static final String DUMMY_EMAIL = "user@example.com";
    private static final String DUMMY_PASSWORD = "password123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.edit_email);
        etPassword = findViewById(R.id.edit_password);
        btnLogin = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progress_bar);
        btnRegister = findViewById(R.id.btn_register); // Inisialisasi btnRegister

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    etEmail.setError("Email diperlukan");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    etPassword.setError("Password diperlukan");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // Simulate authentication (replace with real authentication logic)
                authenticateUser(email, password);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() { // Tambahkan event listener untuk btnRegister
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }

    private void authenticateUser(String email, String password) {
        progressBar.setVisibility(View.GONE);
        if (email.equals(DUMMY_EMAIL) && password.equals(DUMMY_PASSWORD)) {
            Toast.makeText(LoginActivity.this, "Login berhasil", Toast.LENGTH_SHORT).show();
            Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "Login gagal: Email atau password salah", Toast.LENGTH_SHORT).show();
        }
    }
}
