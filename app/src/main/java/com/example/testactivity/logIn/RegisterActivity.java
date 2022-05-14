package com.example.testactivity.logIn;

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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.testactivity.R;
import com.example.testactivity.entities.FireBaseUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    EditText nickNameEditText, fullNameEditText, phoneEditText, emailEditText, passwordEditText;
    ImageView nickNameIcon, fullNameIcon, phoneIcon, emailIcon, passwordIcon;
    LinearLayout nickNameLayout, fullNameLayout, phoneLayout, emailLayout, passwordLayout;
    TextView nickNameText, fullNameText, phoneText, emailText, passwordText;
    AppCompatButton signUpBtn;
    TextView signIn;
    ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth=FirebaseAuth.getInstance();

        signUpBtn = findViewById(R.id.registerSignUpBtn);
        signIn = findViewById(R.id.registerSignInBtn);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        nickNameEditText = findViewById(R.id.nickNameEditText);
        fullNameEditText = findViewById(R.id.fullNameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);


        emailIcon = findViewById(R.id.emailIcon);
        passwordIcon = findViewById(R.id.passwordIcon);
        nickNameIcon = findViewById(R.id.nickNameIcon);
        fullNameIcon = findViewById(R.id.fullNameIcon);
        phoneIcon = findViewById(R.id.phoneIcon);

        emailLayout = findViewById(R.id.emailLayout);
        passwordLayout = findViewById(R.id.passwordLayout);
        nickNameLayout = findViewById(R.id.nickNameLayout);
        fullNameLayout = findViewById(R.id.fullNameLayout);
        phoneLayout = findViewById(R.id.phoneLayout);

        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        nickNameText = findViewById(R.id.nickNameText);
        fullNameText = findViewById(R.id.fullNameText);
        phoneText = findViewById(R.id.phoneText);

        signUpBtn.setOnClickListener(v -> {
            String nickname = nickNameEditText.getText().toString().trim();
            String fullName = fullNameEditText.getText().toString().trim();
            String phone = phoneEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if(email.isEmpty() || password.isEmpty() || fullName.isEmpty())
            {
                Toast.makeText(getApplicationContext(),"Only phone number and nickname are optional",Toast.LENGTH_SHORT).show();
            }
            else if(password.length()<6)
            {
                Toast.makeText(getApplicationContext(),"Password should contain at least 6 symbols",Toast.LENGTH_SHORT).show();
            }
            else{
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FireBaseUser fireBaseUser = new FireBaseUser(fullName, email, password, phone, nickname);
                            FirebaseFirestore db = FirebaseFirestore.getInstance();

                            db.collection("users").add(fireBaseUser);

                            sendEmailVerification();
                            Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        signIn.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LogInActivity.class));
            finish();
        });


        nickNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.showSoftInput(nickNameEditText, InputMethodManager.SHOW_IMPLICIT);
                    selectView("nickName");
                }
            }
        });

        fullNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.showSoftInput(fullNameEditText, InputMethodManager.SHOW_IMPLICIT);
                    selectView("fullName");
                }
            }
        });

        phoneEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.showSoftInput(phoneEditText, InputMethodManager.SHOW_IMPLICIT);
                    selectView("phone");
                }
            }
        });

        emailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.showSoftInput(emailEditText, InputMethodManager.SHOW_IMPLICIT);
                    selectView("email");
                }
            }
        });

        passwordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.showSoftInput(passwordEditText, InputMethodManager.SHOW_IMPLICIT);
                    selectView("password");
                }
            }
        });
    }

    private void sendEmailVerification()
    {
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null)
        {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(),"Verification Email is sent, verify and Log In again",Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(RegisterActivity.this, LogInActivity.class));
                }
            });
        }

        else
        {
            Toast.makeText(getApplicationContext(),"Failed to send verification Email",Toast.LENGTH_SHORT).show();
        }
    }

    private void selectView(String type) {
        resetFields();

        TextView selectedTxt = null;

        switch (type) {
            case "email":
                selectedTxt = emailText;
                emailEditText.setHint("");
                emailEditText.setTypeface(null, Typeface.BOLD);
                emailText.setVisibility(View.VISIBLE);
                emailEditText.setTextSize(14);
                emailIcon.setImageResource(R.drawable.ic_selected_email);
                emailLayout.setBackgroundResource(R.drawable.round_back_white10_20);
                break;
            case "password":
                selectedTxt = passwordText;
                passwordEditText.setHint("");
                passwordEditText.setTypeface(null, Typeface.BOLD);
                passwordText.setVisibility(View.VISIBLE);
                passwordEditText.setTextSize(14);
                passwordIcon.setImageResource(R.drawable.ic_selected_password);
                passwordLayout.setBackgroundResource(R.drawable.round_back_white10_20);
                break;
            case "nickName":
                selectedTxt = nickNameText;
                nickNameEditText.setHint("");
                nickNameEditText.setTypeface(null, Typeface.BOLD);
                nickNameText.setVisibility(View.VISIBLE);
                nickNameEditText.setTextSize(14);
                nickNameIcon.setImageResource(R.drawable.ic_person_outline_white);
                nickNameLayout.setBackgroundResource(R.drawable.round_back_white10_20);
                break;
            case "fullName":
                selectedTxt = fullNameText;
                fullNameEditText.setHint("");
                fullNameEditText.setTypeface(null, Typeface.BOLD);
                fullNameText.setVisibility(View.VISIBLE);
                fullNameEditText.setTextSize(14);
                fullNameIcon.setImageResource(R.drawable.ic_badge);
                fullNameLayout.setBackgroundResource(R.drawable.round_back_white10_20);
                break;
            case "phone":
                selectedTxt = phoneText;
                phoneEditText.setHint("");
                phoneEditText.setTypeface(null, Typeface.BOLD);
                phoneText.setVisibility(View.VISIBLE);
                phoneEditText.setTextSize(14);
                phoneIcon.setImageResource(R.drawable.ic_phone_white);
                phoneLayout.setBackgroundResource(R.drawable.round_back_white10_20);
                break;
        }

        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 50, 0);
        translateAnimation.setDuration(200);
        assert selectedTxt != null;
        selectedTxt.startAnimation(translateAnimation);
    }

    private void resetFields() {

        passwordEditText.setHint("Password");
        passwordEditText.setTextSize(12);

        if(passwordEditText.getText().toString().isEmpty()){
            passwordText.setVisibility(View.GONE);
            nickNameEditText.setTypeface(null, Typeface.NORMAL);
            fullNameEditText.setTypeface(null, Typeface.NORMAL);
            phoneEditText.setTypeface(null, Typeface.NORMAL);
            emailEditText.setTypeface(null, Typeface.NORMAL);
        }

        passwordIcon.setImageResource(R.drawable.ic_not_selected_password);
        passwordLayout.setBackgroundResource(android.R.color.transparent);

        emailEditText.setHint("Email");
        emailEditText.setTextSize(12);

        if(emailEditText.getText().toString().isEmpty()){
            emailText.setVisibility(View.GONE);
            passwordEditText.setTypeface(null, Typeface.NORMAL);
            fullNameEditText.setTypeface(null, Typeface.NORMAL);
            phoneEditText.setTypeface(null, Typeface.NORMAL);
            emailEditText.setTypeface(null, Typeface.NORMAL);
        }

        emailIcon.setImageResource(R.drawable.ic_not_selected_email);
        emailLayout.setBackgroundResource(android.R.color.transparent);

        nickNameEditText.setHint("Nickname");
        nickNameEditText.setTextSize(12);

        if(nickNameEditText.getText().toString().isEmpty()){
            nickNameText.setVisibility(View.GONE);
            passwordEditText.setTypeface(null, Typeface.NORMAL);
            fullNameEditText.setTypeface(null, Typeface.NORMAL);
            phoneEditText.setTypeface(null, Typeface.NORMAL);
            emailEditText.setTypeface(null, Typeface.NORMAL);
        }

        nickNameIcon.setImageResource(R.drawable.ic_person_outline_not_selected);
        nickNameLayout.setBackgroundResource(android.R.color.transparent);

        fullNameEditText.setHint("Full name");
        fullNameEditText.setTextSize(12);

        if(fullNameEditText.getText().toString().isEmpty()){
            fullNameText.setVisibility(View.GONE);
            passwordEditText.setTypeface(null, Typeface.NORMAL);
            nickNameEditText.setTypeface(null, Typeface.NORMAL);
            phoneEditText.setTypeface(null, Typeface.NORMAL);
            emailEditText.setTypeface(null, Typeface.NORMAL);
        }

        fullNameIcon.setImageResource(R.drawable.ic_badge_not_selected);
        fullNameLayout.setBackgroundResource(android.R.color.transparent);

        phoneEditText.setHint("Phone number");
        phoneEditText.setTextSize(12);

        if(phoneEditText.getText().toString().isEmpty()){
            phoneText.setVisibility(View.GONE);
            passwordEditText.setTypeface(null, Typeface.NORMAL);
            fullNameEditText.setTypeface(null, Typeface.NORMAL);
            nickNameEditText.setTypeface(null, Typeface.NORMAL);
            emailEditText.setTypeface(null, Typeface.NORMAL);
        }

        phoneIcon.setImageResource(R.drawable.ic_phone_not_selected);
        phoneLayout.setBackgroundResource(android.R.color.transparent);
    }
}