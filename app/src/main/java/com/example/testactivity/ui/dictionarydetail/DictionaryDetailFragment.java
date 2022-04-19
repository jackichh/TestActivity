package com.example.testactivity.ui.dictionarydetail;

import static com.example.testactivity.activities.HomeActivity.dictionariesDatabase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testactivity.R;
import com.example.testactivity.adapters.DictionaryContentAdapter;
import com.example.testactivity.adapters.DrawerAdapter;
import com.example.testactivity.databinding.FragmentDictionaryDetailBinding;
import com.example.testactivity.models.WordTranslationModel;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

public class DictionaryDetailFragment extends Fragment {

    FragmentDictionaryDetailBinding binding;
    private ArrayList<WordTranslationModel> dictionaryContentList;
    WordTranslationModel wordTranslationModel;
    int size = 10;
    NavController navController;
    EditText inputWord, inputTranslation;

    private DictionaryContentAdapter contentAdapter;

    public static DictionaryDetailFragment newInstance() {
        return new DictionaryDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentDictionaryDetailBinding.inflate(inflater, container, false);



        contentAdapter = new DictionaryContentAdapter(size, dictionaryContentList, requireActivity());
        binding.dictionaryRecyclerView.setAdapter(contentAdapter);


        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_home);

        DictionaryDetailViewModel dictionaryDetailViewModel = new ViewModelProvider(this).get(DictionaryDetailViewModel.class);

        View root = binding.getRoot();

        addingWord();

        return root;
    }

    public void addingWord() {
        //Add word
        binding.addWord.setOnClickListener(view -> {
            //contentAdapter.addDictItem(dictionaryContentList, wordTranslationModel);
            contentAdapter.addWordSpace();
        });
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater = requireActivity().getMenuInflater();
        inflater.inflate(R.menu.dictionary_content_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.save_option:
                saveButton();
                break;
            case R.id.clear_option:
                return true;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveButton() {
        Toast.makeText(requireContext(), "saved", Toast.LENGTH_SHORT).show();

        onDestroyView();
        navController.navigate(R.id.nav_home);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}