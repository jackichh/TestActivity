package com.example.testactivity.ui.dictionarydetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testactivity.R;
import com.example.testactivity.adapters.DrawerAdapter;
import com.example.testactivity.databinding.FragmentDictionaryDetailBinding;

import java.util.Dictionary;
import java.util.List;

public class DictionaryDetailFragment extends Fragment {

    FragmentDictionaryDetailBinding binding;
    private RecyclerView dictionariesRecyclerView;
    private List<Dictionary> dictionaryList;
    private DrawerAdapter drawerAdapter;

    NavController navController;
    EditText inputWord, inputTranslation;

    public static DictionaryDetailFragment newInstance() {
        return new DictionaryDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_home);
        DictionaryDetailViewModel dictionaryDetailViewModel = new ViewModelProvider(this).get(DictionaryDetailViewModel.class);
        binding = FragmentDictionaryDetailBinding.inflate(inflater, container, false);



        View root = binding.getRoot();

        //final TextView textView = binding.textDictionaryDetail;
        //textView.setText(dictionaryDetailViewModel.getText());
//        dictionaryDetailViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        return root;

    }

}