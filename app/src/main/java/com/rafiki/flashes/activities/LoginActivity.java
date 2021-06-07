package com.rafiki.flashes.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rafiki.flashes.R;
import com.rafiki.flashes.model.User;
import com.rafiki.flashes.service.Service;
import com.rafiki.flashes.service.ServiceApi;
import com.rafiki.flashes.sharedResources.Shared;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextPassword;
    private EditText editTextUsername;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpView();
    }

    private void setUpView(){
        editTextUsername = findViewById(R.id.inputUsername);
        editTextPassword = findViewById(R.id.inputPassword);
        Button buttonLogin = findViewById(R.id.btnLogin);
        TextView textViewSignUp = findViewById(R.id.gotoRegister);
        buttonLogin.setOnClickListener(v -> userLogin());
        textViewSignUp.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SignUpActivity.class)));
    }

    private void userLogin(){
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(username.isEmpty()){
            editTextUsername.setError(getResources().getString(R.string.login_email_empty));
            editTextUsername.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError(getResources().getString(R.string.login_password_empty));
            editTextPassword.requestFocus();
            return;
        }

        if(password.length()<4){
            editTextPassword.setError(getResources().getString(R.string.password_error_less_than));
            editTextPassword.requestFocus();
            return;
        }

        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        login();
    }

    private void login(){
        Call<User> call = Service
                .getInstance()
                .createService(ServiceApi.class)
                .login(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if(response.code() == 200){
                    Log.d("FLASHES", "User loged in " + " id " + response.body().getId()
                            + " username: " + response.body().getUsername());
                    Shared.getInstance(getApplicationContext())
                            .saveUser(response.body());
                    startActivity(new Intent(getApplicationContext(), FilmsActivity.class));
                }else if (response.code()==404){
                    Toast.makeText(LoginActivity.this, "User username or password is incorrect", Toast.LENGTH_LONG).show();
                    Log.d("FLASHES", "User does not exist");
                }else{
                    Log.d("FLASHES", "Unknown Error, please try again");
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {

            }
        });
    }





}