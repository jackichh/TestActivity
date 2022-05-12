package com.example.testactivity.ui.Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testactivity.R;
import com.example.testactivity.databinding.FragmentMainBinding;
import com.example.testactivity.ui.Main.Home.HomeFragment;
import com.example.testactivity.ui.Main.Profile.ProfileFragment;
import com.example.testactivity.ui.Main.Search.SearchFragment;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;

    LinearLayout homeLayout, searchLayout, profileLayout;
    ImageView homeImage, searchImage, profileImage;
    TextView homeText, searchText, profileText;

    private int selectedTab = 2;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getParentFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, HomeFragment.class, null)
                .commit();


        //setting id to items
        homeLayout = binding.homeLayout;
        searchLayout = binding.searchLayout;
        profileLayout = binding.profileLayout;

        homeImage = binding.homeImage;
        searchImage = binding.searchImage;
        profileImage = binding.profileImage;

        homeText = binding.homeText;
        searchText = binding.searchText;
        profileText = binding.profileText;


        homeLayout.setOnClickListener(v -> {
            if(selectedTab != 2){

                getParentFragmentManager()
                        .beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view, HomeFragment.class, null)
                        .commit();

                searchText.setVisibility(View.GONE);
                profileText.setVisibility(View.GONE);

                searchImage.setImageResource(R.drawable.ic_search_white);
                profileImage.setImageResource(R.drawable.ic_person_outline_white);

                searchLayout.setBackgroundColor(0);
                profileLayout.setBackgroundColor(0);

                homeText.setVisibility(View.VISIBLE);
                homeImage.setImageResource(R.drawable.ic_home);
                homeLayout.setBackgroundResource(R.drawable.round_back_home_100);

                ScaleAnimation scaleAnimation = new ScaleAnimation(
                        0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                scaleAnimation.setDuration(150);
                scaleAnimation.setFillAfter(true);
                homeLayout.startAnimation(scaleAnimation);

                selectedTab = 2;
            }
        });

        searchLayout.setOnClickListener(v -> {
            if(selectedTab != 1){

                getParentFragmentManager()
                        .beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view, SearchFragment.class, null)
                        .commit();

                homeText.setVisibility(View.GONE);
                profileText.setVisibility(View.GONE);

                homeImage.setImageResource(R.drawable.ic_home);
                profileImage.setImageResource(R.drawable.ic_person_outline_white);

                homeLayout.setBackgroundColor(0);
                profileLayout.setBackgroundColor(0);

                searchText.setVisibility(View.VISIBLE);
                searchImage.setImageResource(R.drawable.ic_search_white);
                searchLayout.setBackgroundResource(R.drawable.round_back_search_100);

                ScaleAnimation scaleAnimation = new ScaleAnimation(
                        0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                scaleAnimation.setDuration(150);
                scaleAnimation.setFillAfter(true);
                searchLayout.startAnimation(scaleAnimation);

                selectedTab = 1;
            }
        });

        profileLayout.setOnClickListener(v -> {
            if(selectedTab != 3){

                getParentFragmentManager()
                        .beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view, ProfileFragment.class, null)
                        .commit();

                searchText.setVisibility(View.GONE);
                homeText.setVisibility(View.GONE);

                searchImage.setImageResource(R.drawable.ic_search_white);
                homeImage.setImageResource(R.drawable.ic_home);

                searchLayout.setBackgroundColor(0);
                homeLayout.setBackgroundColor(0);

                profileText.setVisibility(View.VISIBLE);
                profileImage.setImageResource(R.drawable.ic_person_outline_white);
                profileLayout.setBackgroundResource(R.drawable.round_back_profile_100);

                ScaleAnimation scaleAnimation = new ScaleAnimation(
                        0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                scaleAnimation.setDuration(150);
                scaleAnimation.setFillAfter(true);
                profileLayout.startAnimation(scaleAnimation);

                selectedTab = 3;
            }
        });

    }

}
