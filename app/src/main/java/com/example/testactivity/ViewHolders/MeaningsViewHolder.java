package com.example.testactivity.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testactivity.R;

public class MeaningsViewHolder extends RecyclerView.ViewHolder {
    public TextView partsOfSpeech;
    public RecyclerView recyclerDefinitions;

    public MeaningsViewHolder(@NonNull View itemView) {
        super(itemView);

        partsOfSpeech = itemView.findViewById(R.id.text_partsOfSpeech);
        recyclerDefinitions = itemView.findViewById(R.id.recycler_definitions);
    }
}
