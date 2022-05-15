package com.example.testactivity.activities;

import static androidx.navigation.Navigation.findNavController;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import com.example.testactivity.R;
import com.example.testactivity.adapters.DrawerAdapter;
import com.example.testactivity.database.DictionariesDatabase;
import com.example.testactivity.databinding.ActivityHomeBinding;
import com.example.testactivity.entities.Dictionary;
import com.example.testactivity.logIn.LogInActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;
    NavController navController;

    public static DictionariesDatabase dictionariesDatabase;

    List <Dictionary> dictionaryList, newList = new ArrayList<>();
    List<String> dictionaryNames = new ArrayList<>(), newDictionaryNames = new ArrayList<>();

    private DrawerAdapter drawerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHome.toolbar);

        dictionariesDatabase = Room.databaseBuilder(getApplicationContext(),
                DictionariesDatabase.class,
                "dict").allowMainThreadQueries().build();

        DrawerLayout drawer = binding.drawerLayout;

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, binding.appBarHome.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        dictionaryList=dictionariesDatabase.dictionaryDao().getAllDictionaries();

        for (int i = 0; i < dictionaryList.size(); i++) {
            dictionaryNames.add(dictionaryList.get(i).getDictionaryName());
        }
        newList.clear();
        newDictionaryNames.clear();
        for (int i = 0; i < dictionaryList.size(); i++) {
            if(!newDictionaryNames.contains(dictionaryList.get(i).getDictionaryName())){
                newDictionaryNames.add(dictionaryList.get(i).getDictionaryName());
                newList.add(dictionaryList.get(i));
            }
        }

        drawerAdapter = new DrawerAdapter(newList, HomeActivity.this);
        binding.navMenu.dictionariesList.setAdapter(drawerAdapter);

        navController = findNavController(this, R.id.nav_host_fragment_content_home);

        initListeners();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser == null){
            startActivity(new Intent(HomeActivity.this, LogInActivity.class));
        }
    }

    public void onItemClicked(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", position);
        bundle.putString("name", newList.get(position).getDictionaryName());
        navController.navigate(R.id.nav_dictionary_detail, bundle);
        binding.drawerLayout.closeDrawer(GravityCompat.START);
    }

    public void onItemLongClicked(int pos) {
        Toast.makeText(HomeActivity.this, drawerAdapter.getItemText(pos) + " deleted", Toast.LENGTH_SHORT).show();
        deleteDictionary(pos);
        navController.navigate(R.id.nav_home);
        binding.drawerLayout.closeDrawer(GravityCompat.START);
    }



    public void addDictionary(Dictionary item) {
        drawerAdapter.addDrawerItem(newList, item);
    }

    public void deleteDictionary(int position){
        drawerAdapter.deleteDrawerItem(position);

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