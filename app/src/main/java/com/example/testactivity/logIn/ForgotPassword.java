package com.example.testactivity.logIn;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    AppCompatButton recoverPasswordBtn;
    EditText forgotPasswordEditText;
    TextView forgotPasswordText;
    ImageView forgotPasswordIcon, forgotPassBackBtn;
    LinearLayout forgotPasswordLayout;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        recoverPasswordBtn = findViewById(R.id.recoverPasswordBtn);
        forgotPasswordEditText = findViewById(R.id.forgotPasswordEditText);
        forgotPasswordLayout = findViewById(R.id.forgotPasswordLayout);
        forgotPasswordText = findViewById(R.id.forgotPasswordText);
        forgotPasswordIcon = findViewById(R.id.forgotPasswordIcon);
        forgotPassBackBtn = findViewById(R.id.forgotPassBackBtn);

        firebaseAuth = FirebaseAuth.getInstance();

        forgotPassBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPassword.this, LogInActivity.class));
                finish();
            }
        });

        recoverPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = forgotPasswordEditText.getText().toString().trim();
                if(email.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter your email first", Toast.LENGTH_SHORT).show();
                } else {
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Mail sent, you can recover your password", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(ForgotPassword.this, LogInActivity.class));
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Email is wrong or account does not exist!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(getApplicationContext(), "Instructions were sent on your email", Toast.LENGTH_SHORT).show();
                }
            }
        });

        forgotPasswordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.showSoftInput(forgotPasswordEditText, InputMethodManager.SHOW_IMPLICIT);

                    selectView("forgot");
                }
            }
        });
    }

    private void selectView(String type) {
        resetFields();

        TextView selectedTxt = null;
        if (type.equals("forgot")) {
            selectedTxt = forgotPasswordText;
            forgotPasswordEditText.setHint("");
            forgotPasswordEditText.setTypeface(null, Typeface.BOLD);
            forgotPasswordText.setVisibility(View.VISIBLE);
            forgotPasswordEditText.setTextSize(14);
            forgotPasswordIcon.setImageResource(R.drawable.ic_selected_email);
            forgotPasswordLayout.setBackgroundResource(R.drawable.round_back_white10_20);
        }
    }

    private void resetFields() {

        forgotPasswordEditText.setHint("Password");
        forgotPasswordEditText.setTextSize(12);

        if (forgotPasswordEditText.getText().toString().isEmpty()) {
            forgotPasswordText.setVisibility(View.GONE);
            forgotPasswordEditText.setTypeface(null, Typeface.NORMAL);
        }

        forgotPasswordIcon.setImageResource(R.drawable.ic_selected_email);
        forgotPasswordLayout.setBackgroundResource(android.R.color.transparent);
    }

}