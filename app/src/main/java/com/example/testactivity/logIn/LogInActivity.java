package com.example.testactivity.logIn;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.testactivity.R;
import com.example.testactivity.activities.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {

    EditText emailEditText, passwordEditText;
    ImageView emailIcon, passwordIcon;
    LinearLayout emailLayout, passwordLayout;
    TextView emailText, passwordText;
    AppCompatButton signInBtn;
    TextView signUp, forgotPassword;

    ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(LogInActivity.this);

        signInBtn = findViewById(R.id.loginSignInBtn);
        signUp = findViewById(R.id.loginSignUpBtn);
        forgotPassword = findViewById(R.id.forgotPasswordBtn);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        emailIcon = findViewById(R.id.emailIcon);
        passwordIcon = findViewById(R.id.passwordIcon);

        emailLayout = findViewById(R.id.emailLayout);
        passwordLayout = findViewById(R.id.passwordLayout);

        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);

        //Forgot password
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInActivity.this, ForgotPassword.class));
                finish();
            }
        });

        //Sign In
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "All fields Are Required!", Toast.LENGTH_SHORT).show();

                } else {


                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            progressDialog.setTitle("Loading..");
                            progressDialog.show();

                            if (task.isSuccessful()) {
                                checkEmailVerification();
                                progressDialog.dismiss();
                            } else {
                                Toast.makeText(getApplicationContext(), "Account Doesn't Exist", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    });
                }
            }
        });


        //Sign Up
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInActivity.this, RegisterActivity.class));
                finish();
            }
        });

        emailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.showSoftInput(emailEditText, InputMethodManager.SHOW_IMPLICIT);

                    selectView("email");
                }
            }
        });

        passwordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.showSoftInput(passwordEditText, InputMethodManager.SHOW_IMPLICIT);

                    selectView("password");
                }
            }
        });
    }

    private void checkEmailVerification() {
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            if(firebaseUser.isEmailVerified())
            {
                Toast.makeText(getApplicationContext(),"Signed In",Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(LogInActivity.this, HomeActivity.class));
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Verify your mail first",Toast.LENGTH_SHORT).show();
                firebaseAuth.signOut();
            }
        }
    }

    private void selectView(String type) {
        resetFields();

        TextView selectedTxt = null;
        if (type.equals("email")) {
            selectedTxt = emailText;
            emailEditText.setHint("");
            emailEditText.setTypeface(null, Typeface.BOLD);
            emailText.setVisibility(View.VISIBLE);
            emailEditText.setTextSize(14);
            emailIcon.setImageResource(R.drawable.ic_selected_email);
            emailLayout.setBackgroundResource(R.drawable.round_back_white10_20);
        } else if (type.equals("password")) {
            selectedTxt = passwordText;
            passwordEditText.setHint("");
            passwordEditText.setTypeface(null, Typeface.BOLD);
            passwordText.setVisibility(View.VISIBLE);
            passwordEditText.setTextSize(14);
            passwordIcon.setImageResource(R.drawable.ic_selected_password);
            passwordLayout.setBackgroundResource(R.drawable.round_back_white10_20);
        }

        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 50, 0);
        translateAnimation.setDuration(200);
        assert selectedTxt != null;
        selectedTxt.startAnimation(translateAnimation);
    }

    private void resetFields() {

        passwordEditText.setHint("Password");
        passwordEditText.setTextSize(12);

        if (passwordEditText.getText().toString().isEmpty()) {
            passwordText.setVisibility(View.GONE);
            passwordEditText.setTypeface(null, Typeface.NORMAL);
        }

        passwordIcon.setImageResource(R.drawable.ic_not_selected_password);
        passwordLayout.setBackgroundResource(android.R.color.transparent);

        emailEditText.setHint("Email");
        emailEditText.setTextSize(12);

        if (emailEditText.getText().toString().isEmpty()) {
            emailText.setVisibility(View.GONE);
            emailEditText.setTypeface(null, Typeface.NORMAL);
        }

        emailIcon.setImageResource(R.drawable.ic_not_selected_email);
        emailLayout.setBackgroundResource(android.R.color.transparent);
    }
}