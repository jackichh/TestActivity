package com.example.testactivity.ui.Main.Profile;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatRatingBar;

import com.example.testactivity.R;

public class RateUsDialog extends Dialog {

    private float userRate = 0;
    AppCompatButton rateNowBtn, laterBtn;
    AppCompatRatingBar ratingBar;
    ImageView ratingImage;

    public RateUsDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate_us_dialog_layout);

        rateNowBtn=findViewById(R.id.rateNowBtn);
        laterBtn=findViewById(R.id.laterBtn);
        ratingBar=findViewById(R.id.ratingBar);
        ratingImage=findViewById(R.id.ratingImage);

        rateNowBtn.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Thank you!", Toast.LENGTH_SHORT).show();
            dismiss();
        });

        laterBtn.setOnClickListener(v -> {
            dismiss();
        });

        ratingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {

            if(rating <= 1){
                ratingImage.setImageResource(R.drawable.very_angry_emoji);
            } else if(rating <= 2){
                ratingImage.setImageResource(R.drawable.sad_emoji);
            } else if(rating <= 3){
                ratingImage.setImageResource(R.drawable.neutural_emoji);
            } else if(rating <= 4){
                ratingImage.setImageResource(R.drawable.happy_emoji);
            } else if(rating <= 5){
                ratingImage.setImageResource(R.drawable.heart_eyes_emoji);
            }

            animateImage(ratingImage);

            userRate = rating;
        });
    }

    private void animateImage(ImageView ratingImage){
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(200);
        ratingImage.startAnimation(scaleAnimation);
    }
}
