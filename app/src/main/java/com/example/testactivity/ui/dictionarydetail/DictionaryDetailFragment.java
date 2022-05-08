package com.example.testactivity.ui.dictionarydetail;

import static com.example.testactivity.activities.HomeActivity.dictionariesDatabase;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testactivity.R;
import com.example.testactivity.adapters.DictionaryContentAdapter;
import com.example.testactivity.databinding.FragmentDictionaryDetailBinding;
import com.example.testactivity.entities.Dictionary;
import com.example.testactivity.interfaces.DetailFragmentOnBackPressed;
import com.example.testactivity.models.WordTranslationModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class DictionaryDetailFragment extends Fragment implements DetailFragmentOnBackPressed {

    FragmentDictionaryDetailBinding binding;
    ArrayList<WordTranslationModel> dictionaryContentList = new ArrayList<>();
    List<Dictionary> list;
    int id, var = 0;
    boolean flag = false;
    String name;
    NavController navController;
    private DictionaryContentAdapter contentAdapter;
    private int delCount = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentDictionaryDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            id = getArguments().getInt("id");
            name = getArguments().getString("name");
            Toast.makeText(requireContext(), name, Toast.LENGTH_SHORT).show();
        }

        list = dictionariesDatabase.dictionaryDao().getAllDictionaryWithNameLike(name);
        for (int i = 0; i < list.size(); i++) {
            WordTranslationModel temp = new WordTranslationModel();
            temp.setWord(list.get(i).getWord());
            temp.setTranslation(list.get(i).getTranslation());
            dictionaryContentList.add(temp);
        }

        dictionaryContentList.remove(0);

        contentAdapter = new DictionaryContentAdapter(dictionaryContentList, requireContext());
        binding.dictionaryRecyclerView.setAdapter(contentAdapter);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_home);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.dictionaryRecyclerView);
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            if (position <= list.size() - 2) {
                Dictionary deleteDict = new Dictionary();
                deleteDict.setId(list.get(position).getId());
                deleteDict.setWord(dictionaryContentList.get(position).getWord());
                deleteDict.setTranslation(dictionaryContentList.get(position).getTranslation());
                deleteDict.setDictionaryName(name);

                if (deleteDict.getWord() != null && deleteDict.getTranslation() != null) {
                    dictionariesDatabase.dictionaryDao().deleteDeleteDictionary(deleteDict);
                    Snackbar.make(binding.dictionaryRecyclerView, "Deleted", Snackbar.LENGTH_SHORT).setAction("Undo", v -> {
                        dictionariesDatabase.dictionaryDao().insertDictionary(deleteDict);
                        dictionaryContentList.add(new WordTranslationModel(deleteDict.getWord(), deleteDict.getTranslation()));
                        contentAdapter.notifyItemInserted(position);
                    }).show();
                }
            }
            dictionaryContentList.remove(position);
            contentAdapter.notifyItemRemoved(position);

        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater = requireActivity().getMenuInflater();
        inflater.inflate(R.menu.dictionary_content_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_option);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type word or translation");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                contentAdapter.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.save_option:
                saveOption();
                break;

            case R.id.clear_option:
                clearOption();
                break;

            case R.id.new_option:
                newOption();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void clearOption() {
        if (!dictionaryContentList.isEmpty()) {
            AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
            ad.setCancelable(false);
            ad.setTitle("Clear");
            ad.setMessage("Do you want to empty your dictionary?");
            ad.setPositiveButton("Yes", (dialog, which) -> {
                dictionariesDatabase.dictionaryDao().deleteDictionariesWithNameLike(name);
                Dictionary dict = new Dictionary();
                dict.setDictionaryName(name);
                dictionaryContentList.clear();
                dictionariesDatabase.dictionaryDao().insertDictionary(dict);
                contentAdapter.notifyDataSetChanged();
            });
            ad.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
            ad.show();
        }
    }

    private void newOption() {
        if (!flag) flag = true;
        var++;
        delCount++;
        dictionaryContentList.add(new WordTranslationModel());
        contentAdapter.notifyItemChanged(contentAdapter.getItemCount() - 1);
    }

    private void saveOption() {
        if (flag) flag = false;
        ArrayList<WordTranslationModel> wtList;
        wtList = dictionaryContentList;

        if (var != 0) {
            for (int i = 0; i < var; i++) {
                Dictionary tempDictionary = new Dictionary();
                if (wtList.get(wtList.size() - var + i).getWord() != null && wtList.get(wtList.size() - var + i).getTranslation() != null) {
                    tempDictionary.setDictionaryName(name);
                    tempDictionary.setWord(wtList.get(wtList.size() - var + i).getWord());
                    tempDictionary.setTranslation(wtList.get(wtList.size() - var + i).getTranslation());
                    dictionariesDatabase.dictionaryDao().insertDictionary(tempDictionary);
                }
            }
        }
        Toast.makeText(requireContext(), "saved", Toast.LENGTH_SHORT).show();
        //onDestroyView();
        //navController.navigate(R.id.nav_home);
    }

    @Override
    public void onDestroyView() {
        if (flag) {
            AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
            ad.setCancelable(false);
            ad.setTitle("Save");
            ad.setMessage("Your changes won't be saved!");
            ad.setPositiveButton("Save", (dialog, which) -> {
                saveOption();
            });
            ad.setNegativeButton("Ok", (dialog, which) -> {
                dialog.dismiss();
            });
            ad.show();
        }
        super.onDestroyView();
        binding = null;
    }

    @Override
    public boolean onBackPressed() {
        return true;
    }
}