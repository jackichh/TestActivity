package com.example.testactivity.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testactivity.R;

public class DefinitionViewHolder extends RecyclerView.ViewHolder{
     public TextView textDefinition, textExample, textSynonyms, textAntonyms;

    public DefinitionViewHolder(@NonNull View itemView) {
        super(itemView);
        textDefinition = itemView.findViewById(R.id.text_definition);
        textExample = itemView.findViewById(R.id.text_example);
        textSynonyms = itemView.findViewById(R.id.text_synonyms);
        textAntonyms = itemView.findViewById(R.id.text_antonyms);
    }
}
