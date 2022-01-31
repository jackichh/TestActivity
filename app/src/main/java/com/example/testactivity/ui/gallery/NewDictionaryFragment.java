package com.example.testactivity.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.testactivity.R;
import com.example.testactivity.databinding.ActivityHomeBinding;
import com.example.testactivity.databinding.FragmentNewDictionaryBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class NewDictionaryFragment extends Fragment {

    private NewDictionaryViewModel newDictionaryViewModel;
    private FragmentNewDictionaryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {


        newDictionaryViewModel = new ViewModelProvider(this).get(NewDictionaryViewModel.class);

        binding = FragmentNewDictionaryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final EditText editText = binding.textNewDictionary;
        newDictionaryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                editText.setHint(s);
            }
        });
        return root;


    binding.fabNew.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(requireContext(), "ok", Toast.LENGTH_SHORT).show();
        }
    });

    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}