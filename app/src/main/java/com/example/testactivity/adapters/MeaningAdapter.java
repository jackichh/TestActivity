package com.example.testactivity.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testactivity.R;
import com.example.testactivity.ViewHolders.MeaningsViewHolder;
import com.example.testactivity.models.Meanings;

import java.util.List;

public class MeaningAdapter extends RecyclerView.Adapter<MeaningsViewHolder> {
    private Context context;
    private List<Meanings> meaningsList;

    public MeaningAdapter(Context context, List<Meanings> meaningsList) {
        this.context = context;
        this.meaningsList = meaningsList;
    }

    @NonNull
    @Override
    public MeaningsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MeaningsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_meanings, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MeaningsViewHolder holder, int position) {
        holder.partsOfSpeech.setText("Parts of speech: " + meaningsList.get(position).getPartOfSpeech());
        holder.recyclerDefinitions.setHasFixedSize(true);
        holder.recyclerDefinitions.setLayoutManager(new GridLayoutManager(context, 1));

        DefinitionAdapter definitionAdapter = new DefinitionAdapter(context, meaningsList.get(position).getDefinitions());
        holder.recyclerDefinitions.setAdapter(definitionAdapter);
    }

    @Override
    public int getItemCount() {
        return meaningsList.size();
    }
}
