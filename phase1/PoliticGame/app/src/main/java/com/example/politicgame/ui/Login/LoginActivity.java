package com.example.politicgame.ui.Login;

import android.app.Activity;

import androidx.lifecycle.Observer;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.politicgame.BabyGame.BabyActivity;
import com.example.politicgame.MainActivity;
import com.example.politicgame.PoliticGameApp;
import com.example.politicgame.R;
import com.example.politicgame.RegistrationActivity;

public class LoginActivity extends AppCompatActivity {
    private PoliticGameApp app;
    private LoginViewModel loginViewModel;
    private void register(){
        Intent registerIntent = new Intent(this, RegistrationActivity.class);
        startActivity(registerIntent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button registration = findViewById(R.id.signup);
        registration.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        register();
                    }
                });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        app = (PoliticGameApp) getApplication();

        System.out.println("The current theme is blue: " + app.isThemeBlue());

        if (app.isThemeBlue()){
            setTheme(R.style.BlueTheme);
        } else {
            setTheme(R.style.RedTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");

        this.loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory(this))
                .get(LoginViewModel.class);
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        final Button backButton = findViewById(R.id.goBack);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });

        backButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        BackToMenu();
                    }
                });
    }
    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        Intent startIntent = new Intent(this, BabyActivity.class);
        startActivity(startIntent);
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    public void BackToMenu() {
        /**
         * Returns to main menu
         */
        Intent switchBabyIntent = new Intent(this, MainActivity.class);
        startActivity(switchBabyIntent);
        finish();
    }
}
