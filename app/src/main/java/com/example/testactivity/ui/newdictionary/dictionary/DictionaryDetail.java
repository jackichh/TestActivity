package com.example.testactivity.ui.newdictionary.dictionary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.testactivity.R;
import com.example.testactivity.databinding.DictionaryDetailFragmentBinding;

public class DictionaryDetail extends Fragment {

    private DictionaryDetailViewModel mViewModel;
    DictionaryDetailFragmentBinding binding;
    NavController navController;

    public static DictionaryDetail newInstance() {
        return new DictionaryDetail();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_home);
        mViewModel = new ViewModelProvider(this).get(DictionaryDetailViewModel.class);
        binding = DictionaryDetailFragmentBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

}