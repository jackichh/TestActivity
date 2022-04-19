package com.example.testactivity.activities;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
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
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;
    NavController navController;

    public static DictionariesDatabase dictionariesDatabase;

    List <Dictionary> dictionaryList;

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

        drawerAdapter = new DrawerAdapter(dictionaryList, HomeActivity.this);
        binding.navMenu.dictionariesList.setAdapter(drawerAdapter);

        navController = findNavController(this, R.id.nav_host_fragment_content_home);

        initListeners();

    }


//      Back press      //
//    boolean doubleBackToExitPressedOnce = false;
//
//    @Override
//    public void onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            return;
//        }
//
//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Please click again to exit", Toast.LENGTH_SHORT).show();
//
//        new Handler(Looper.getMainLooper()).postDelayed(() -> doubleBackToExitPressedOnce=false, 2000);
//    }




    public void onItemClicked(int position) {
        String currentDictionaryName = drawerAdapter.getItemText(position);
        Toast.makeText(HomeActivity.this, currentDictionaryName, Toast.LENGTH_SHORT).show();
        navController.navigate(R.id.nav_dictionary_detail);
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar !=null)
            actionBar.setTitle(currentDictionaryName);
    }

    public void onItemLongClicked(int pos) {
        Toast.makeText(HomeActivity.this, drawerAdapter.getItemText(pos) + " deleted", Toast.LENGTH_SHORT).show();
        deleteDictionary(pos);
        binding.drawerLayout.closeDrawer(GravityCompat.START);
    }



    public void addDictionary(Dictionary item) {
        drawerAdapter.addDrawerItem(dictionaryList, item);
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