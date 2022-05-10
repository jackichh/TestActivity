package com.example.testactivity.ui.home;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testactivity.Listeners.OnFetchDataListener;
import com.example.testactivity.RequestManagers.RequestManager;
import com.example.testactivity.adapters.MeaningAdapter;
import com.example.testactivity.adapters.PhoneticsAdapter;
import com.example.testactivity.databinding.FragmentHomeBinding;
import com.example.testactivity.models.APIResponse;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    SearchView searchView;
    TextView textView_word;
    RecyclerView recyclerView_phonetics, recyclerView_meanings;
    ProgressDialog progressDialog;
    PhoneticsAdapter phoneticsAdapter;
    MeaningAdapter meaningAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchView = binding.searchView;
        textView_word = binding.textWord;
        recyclerView_meanings = binding.recyclerMeanings;
        recyclerView_phonetics = binding.recyclerPhonetics;
        progressDialog = new ProgressDialog(getContext());

        progressDialog.setTitle("Loading..");
        progressDialog.show();
        RequestManager manager = new RequestManager(getContext());
        manager.getWordMeaning(listener, "hello");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Searching response for " + query);
                progressDialog.show();
                RequestManager manager = new RequestManager(getContext());
                manager.getWordMeaning(listener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private final OnFetchDataListener listener = new OnFetchDataListener(){

        @Override
        public void onFetchData(APIResponse apiResponse, String message) {
            progressDialog.dismiss();
            if (apiResponse==null){
                Toast.makeText(getContext(), "No data found!", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiResponse);
        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(getContext(), "Error!", Toast.LENGTH_SHORT).show();
        }
    };

    @SuppressLint("SetTextI18n")
    private void showData(APIResponse apiResponse) {
        textView_word.setText("Word: "+ apiResponse.getWord());

        recyclerView_phonetics.setHasFixedSize(true);
        recyclerView_phonetics.setLayoutManager(new GridLayoutManager(getContext(), 1));
        phoneticsAdapter = new PhoneticsAdapter(getContext(), apiResponse.getPhonetics());
        recyclerView_phonetics.setAdapter(phoneticsAdapter);

        recyclerView_meanings.setHasFixedSize(true);
        recyclerView_meanings.setLayoutManager(new GridLayoutManager(getContext(),1));
        meaningAdapter = new MeaningAdapter(getContext(), apiResponse.getMeanings());
        recyclerView_meanings.setAdapter(meaningAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}