package com.example.testactivity.ui.newdictionary;

import static com.example.testactivity.activities.HomeActivity.dictionariesDatabase;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.testactivity.activities.HomeActivity;
import com.example.testactivity.R;
import com.example.testactivity.dao.DictionaryDao;
import com.example.testactivity.database.DictionariesDatabase;
import com.example.testactivity.databinding.FragmentNewDictionaryBinding;
import com.example.testactivity.entities.Dictionary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewDictionaryFragment extends Fragment {

    private FragmentNewDictionaryBinding binding;

    NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_home);

        NewDictionaryViewModel newDictionaryViewModel = new ViewModelProvider(this).get(NewDictionaryViewModel.class);

        binding = FragmentNewDictionaryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        EditText editText = binding.textNewDictionary;

        newDictionaryViewModel.getText().observe(getViewLifecycleOwner(), editText::setHint);
        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initListeners() {
        binding.fabNew.setOnClickListener(v -> {
            if (!binding.textNewDictionary.getText().toString().isEmpty()){
                final Dictionary dictionary = new Dictionary();
                dictionary.setDictionaryName(binding.textNewDictionary.getText().toString());
                dictionariesDatabase.dictionaryDao().insertDictionary(dictionary);

            Toast.makeText(requireContext(),
                    R.string.created,
                    Toast.LENGTH_SHORT).show();
            ((HomeActivity) requireActivity()).addDictionary(dictionary);
            navController.navigate(R.id.nav_home);
        }
            else {
                Toast.makeText(requireContext(), "Title can't be empty", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}