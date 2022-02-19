package com.example.testactivity.ui.newdictionary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.testactivity.HomeActivity;
import com.example.testactivity.R;
import com.example.testactivity.databinding.FragmentNewDictionaryBinding;

public class NewDictionaryFragment extends Fragment {

    private NewDictionaryViewModel newDictionaryViewModel;
    private FragmentNewDictionaryBinding binding;

    NavController navController;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_home);


        newDictionaryViewModel = new ViewModelProvider(this).get(NewDictionaryViewModel.class);

        binding = FragmentNewDictionaryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        newDictionaryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                binding.textNewDictionary.setHint(s);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
    }

    private void initListeners() {
        binding.fabNew.setOnClickListener(v -> {
                    Toast.makeText(requireContext(), "ok", Toast.LENGTH_SHORT).show();
                    ((HomeActivity) requireActivity()).addDictionary(binding.textNewDictionary.getText().toString());
                    navController.navigate(R.id.nav_home);
                }
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}