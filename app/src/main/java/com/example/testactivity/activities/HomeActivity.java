package com.example.testactivity.activities;

import static androidx.navigation.Navigation.findNavController;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testactivity.R;
import com.example.testactivity.adapters.DictionaryAdapter;
import com.example.testactivity.database.DictionariesDatabase;
import com.example.testactivity.databinding.ActivityHomeBinding;
import com.example.testactivity.adapters.DrawerAdapter;
import com.example.testactivity.entities.Dictionary;
import com.example.testactivity.ui.dictionarydetail.DictionaryDetailViewModel;
import com.google.android.material.navigation.NavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;
    NavController navController;

    private List<Dictionary> dictionaryList;

    DictionaryDetailViewModel dictionaryDetailViewModel;

    ArrayList<String> list = new ArrayList<>();
    int count = 0;

    DrawerAdapter mDrawerAdapter;
    private DictionaryAdapter dictionaryAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDictionaries();

        dictionaryDetailViewModel = new ViewModelProvider(this).get(DictionaryDetailViewModel.class);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHome.toolbar);

        DrawerLayout drawer = binding.drawerLayout;

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, binding.appBarHome.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        
        mDrawerAdapter = new DrawerAdapter(list, HomeActivity.this);
        binding.navMenu.dictionaryList.setAdapter(mDrawerAdapter);


        mDrawerAdapter.addDrawerMenuList(list);

        NavigationView navigationView = binding.navView;

        navController = findNavController(this, R.id.nav_host_fragment_content_home);

//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);
        //getDictionaries();
        initListeners();

    }

    @SuppressLint("NotifyDataSetChanged")
    private void getDictionaries() {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            //Background work here

            List<Dictionary> dictionaries = DictionariesDatabase
                    .getDatabase(getApplicationContext())
                    .dictionaryDao().getAllDictionaries();


            handler.post(() -> {
                // pass to main thread
                if (dictionaryList.size() == 0) {
                    dictionaryList.addAll(dictionaries);

                } else {
                    dictionaryList.add(0, dictionaries.get(0));
                    mDrawerAdapter.notifyDataSetChanged();
                    //notesAdapter.notifyItemInserted(0);
                }
//                notesRecyclerView.setAdapter(new NotesAdapter(noteList));
//                notesAdapter.notifyDataSetChanged();
                //smoothScrollToPosition(0);
            });
        });
    }

    public void onItemSelected(int item) {
        Toast.makeText(HomeActivity.this, (CharSequence) mDrawerAdapter.getItemText(item), Toast.LENGTH_SHORT).show();
        dictionaryDetailViewModel.setText(mDrawerAdapter.getItemText(item));
        navController.navigate(R.id.nav_dictionary_detail);
        binding.drawerLayout.closeDrawer(GravityCompat.START);
    }


    public void addDictionary(String name) {
        mDrawerAdapter.addDrawerMenuItem(list, name);
    }



    private void initListeners() {
        //Home
        binding.navMenu.homeItem.setOnClickListener(view -> {
            Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
            navController.navigate(R.id.nav_home);
            binding.drawerLayout.closeDrawer(GravityCompat.START);

        });

        //New dictionary
        binding.navMenu.addItem.setOnClickListener(view -> {
            navController.navigate(R.id.nav_new_dictionary);
//            final EditText taskEditText = new EditText(this);
//            AlertDialog dialog = new AlertDialog.Builder(this)
//                    .setTitle("Set title of your dictionary")
//                    .setView(taskEditText)
//                    .setPositiveButton("Create", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(getApplicationContext(),
//                                    R.string.created,
//                                    Toast.LENGTH_SHORT).show();
//                            addDictionary(taskEditText.getText().toString());
//                            navController.navigate(R.id.nav_home);
//                        }
//                    })
//                    .setNegativeButton("Cancel", null)
//                    .create();
//            dialog.show();
            //mDrawerAdapter.addDrawerMenuItem(list, "Dictionary " + (count+1));
            //count++;
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        });


        //Settings
        binding.navMenu.settings.setOnClickListener(view -> {
            Toast.makeText(HomeActivity.this, "Settings", Toast.LENGTH_SHORT).show();
            navController.navigate(R.id.nav_settings);
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        });

        //Dictionaries


    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}