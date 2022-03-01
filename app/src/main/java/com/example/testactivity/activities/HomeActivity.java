package com.example.testactivity.activities;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;
import com.example.testactivity.R;
import com.example.testactivity.adapters.DrawerAdapter;
import com.example.testactivity.database.DictionariesDatabase;
import com.example.testactivity.databinding.ActivityHomeBinding;
import com.example.testactivity.entities.Dictionary;
import com.example.testactivity.ui.dictionarydetail.DictionaryDetailViewModel;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;
    NavController navController;

    public static DictionariesDatabase dictionariesDatabase;

    List<Dictionary> dictionaryList;

    DictionaryDetailViewModel dictionaryDetailViewModel;


    private DrawerAdapter drawerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dictionaryDetailViewModel = new ViewModelProvider(this).get(DictionaryDetailViewModel.class);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dictionariesDatabase = Room.databaseBuilder(getApplicationContext(),
                DictionariesDatabase.class,
                "dict").allowMainThreadQueries().build();

        setSupportActionBar(binding.appBarHome.toolbar);

        DrawerLayout drawer = binding.drawerLayout;

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, binding.appBarHome.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        dictionaryList=dictionariesDatabase.dictionaryDao().getAllDictionaries();

        drawerAdapter = new DrawerAdapter(dictionaryList, getApplicationContext());
        binding.navMenu.dictionariesList.setAdapter(drawerAdapter);

        navController = findNavController(this, R.id.nav_host_fragment_content_home);

        initListeners();

    }


    public void onItemSelected(int item) {
        Toast.makeText(HomeActivity.this, drawerAdapter.getItemText(item), Toast.LENGTH_SHORT).show();
        dictionaryDetailViewModel.setText(drawerAdapter.getItemText(item));
        navController.navigate(R.id.nav_dictionary_detail);
        binding.drawerLayout.closeDrawer(GravityCompat.START);
    }

    public void onItemLongClick(int item) {
        drawerAdapter.deleteDrawerItem(item);
        binding.drawerLayout.closeDrawer(GravityCompat.START);
    }



//    public void addDictionary(Dictionary name) {
//        drawerAdapter.addDrawerMenuItem(dictionaryList, name);
//    }



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