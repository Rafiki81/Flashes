package com.rafiki.flashes.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rafiki.flashes.R;
import com.rafiki.flashes.model.User;
import com.rafiki.flashes.service.Service;
import com.rafiki.flashes.service.ServiceApi;
import com.rafiki.flashes.sharedResources.Shared;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setUpView();
    }

    private void setUpView(){
        editTextUsername = findViewById(R.id.inputUsername);
        editTextPassword = findViewById(R.id.inputPassword);
        editTextEmail = findViewById(R.id.inputEmail);
        Button btSignUp = findViewById(R.id.btnSignUp);
        TextView textViewLogin = findViewById(R.id.gotoLogin);

        btSignUp.setOnClickListener(v -> userSignUp());

        textViewLogin.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), LoginActivity.class)));
    }

    private void userSignUp(){
        String email = editTextEmail.getText().toString().trim();
        String name = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(name.isEmpty()){
            editTextUsername.setError(getResources().getString(R.string.signUp_username_empty));
            editTextUsername.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmail.setError(getResources().getString(R.string.signUp_email_empty));
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError(getResources().getString(R.string.signUp_not_an_email));
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError(getResources().getString(R.string.signUp_password_empty));
            editTextPassword.requestFocus();
            return;
        }

        if(password.length()<4){
            editTextPassword.setError(getResources().getString(R.string.password_error_less_than));
            editTextPassword.requestFocus();
            return;
        }

        user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(password);
        createUser();
    }

    private void createUser(){
        Call<Void> call = Service
                .getInstance()
                .createService(ServiceApi.class)
                .signUp(user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if(response.code() == 201){
                    Log.d("FLASHES", "Used correctly stored");
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }else if(response.code()==409){
                    Log.d("FLASHES", "The user already exists");
                }else{
                    Log.d("FLASHES", "Unknown error, please try again");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.d("FLASHES Error: ", t.getMessage());
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        if(Shared.getInstance(this).isLoggedIn()){
            Log.d("FLASHES", "User already has login");
            startActivity(new Intent(getApplicationContext(), FilmsActivity.class));
        }
    }
}