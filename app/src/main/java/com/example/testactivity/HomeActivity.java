package com.example.testactivity;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.testactivity.databinding.ActivityHomeBinding;
import com.example.testactivity.adapter.DrawerAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;
    NavController navController;

    ArrayList<String> list = new ArrayList<>();
    int count = 0;

    DrawerAdapter mDrawerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHome.toolbar);

        DrawerLayout drawer = binding.drawerLayout;

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, binding.appBarHome.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        binding.navMenu.dictionaryList.setHasFixedSize(true);
        mDrawerAdapter = new DrawerAdapter(list, HomeActivity.this);
        binding.navMenu.dictionaryList.setAdapter(mDrawerAdapter);
        mDrawerAdapter.addDrawerMenuList(list);

        NavigationView navigationView = binding.navView;

        navController = findNavController(this, R.id.nav_host_fragment_content_home);

//        NavigationUI.setupWithNavController(navigationView, navController);

        initListeners();

    }

    public void onItemSelected(int item) {
        Toast.makeText(HomeActivity.this, "onItemSelected " + (item + 1), Toast.LENGTH_SHORT).show();
        binding.drawerLayout.closeDrawer(GravityCompat.START);
    }


    public void addDictionary(String name) {
        mDrawerAdapter.addDrawerMenuItem(list, name);
    }



    private void initListeners() {
        binding.navMenu.homeItem.setOnClickListener(view -> {
            Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
            navController.navigate(R.id.nav_home);
            binding.drawerLayout.closeDrawer(GravityCompat.START);

        });
        binding.navMenu.addItem.setOnClickListener(view -> {
            Toast.makeText(HomeActivity.this, "Add", Toast.LENGTH_SHORT).show();
            navController.navigate(R.id.nav_new_dictionary);
            mDrawerAdapter.addDrawerMenuItem(list, "Dictionary " + (count+1));
            count++;
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        });
        binding.navMenu.settings.setOnClickListener(view -> {
            Toast.makeText(HomeActivity.this, "Settings", Toast.LENGTH_SHORT).show();
            navController.navigate(R.id.nav_settings);
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        });
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}