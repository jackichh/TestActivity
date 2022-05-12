package com.example.testactivity.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testactivity.R;
import com.example.testactivity.ViewHolders.PhoneticViewHolder;
import com.example.testactivity.Models.Phonetics;

import java.util.List;

public class PhoneticsAdapter extends RecyclerView.Adapter<PhoneticViewHolder> {
    private Context context;
    private List<Phonetics> phoneticsList;

    public PhoneticsAdapter(Context context, List<Phonetics> phoneticsList) {
        this.context = context;
        this.phoneticsList = phoneticsList;
    }

    @NonNull
    @Override
    public PhoneticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhoneticViewHolder(LayoutInflater.from(context).inflate(R.layout.item_phonetics, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneticViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.phoneticText.setText(phoneticsList.get(position).getText());
        holder.audioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer player = new MediaPlayer();
                try {
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    player.setDataSource("https:" + phoneticsList.get(position).getAudio());
                    player.prepare();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Couldn't play audio!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return phoneticsList.size();
    }

}