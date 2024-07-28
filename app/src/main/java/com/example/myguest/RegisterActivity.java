package com.example.myguest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.content.SharedPreferences;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword;
    private Button btnRegister;
    private ProgressBar progressBar;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        etName = findViewById(R.id.edit_name);
        etEmail = findViewById(R.id.edit_email);
        etPassword = findViewById(R.id.edit_password);
        btnRegister = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progress_bar);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    etName.setError("Nama diperlukan");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    etEmail.setError("Email diperlukan");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    etPassword.setError("Password diperlukan");
                    return;
                }

                if (password.length() < 6) {
                    etPassword.setError("Password harus memiliki setidaknya 6 karakter");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // Simpan data pengguna di SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_EMAIL, email);
                editor.putString(KEY_PASSWORD, password);
                editor.apply();

                progressBar.setVisibility(View.GONE);
                Toast.makeText(RegisterActivity.this, "Registrasi berhasil", Toast.LENGTH_SHORT).show();

                // Intent ke halaman utama atau verifikasi email
                // Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                // startActivity(mainIntent);
                // finish();
            }
        });
    }
}


