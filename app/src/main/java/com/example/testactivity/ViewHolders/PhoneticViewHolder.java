package com.example.testactivity.ViewHolders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testactivity.R;

public class PhoneticViewHolder extends RecyclerView.ViewHolder{
    public TextView phoneticText;
    public ImageButton audioButton;

    public PhoneticViewHolder(@NonNull View itemView) {
        super(itemView);
        phoneticText = itemView.findViewById(R.id.text_phonetic);
        audioButton = itemView.findViewById(R.id.imageButton_audio);
    }
}
