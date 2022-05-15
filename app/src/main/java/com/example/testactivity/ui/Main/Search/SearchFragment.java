package com.example.testactivity.ui.Main.Search;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testactivity.R;
import com.example.testactivity.databinding.FragmentSearchBinding;
import com.example.testactivity.ui.Main.Search.Meaning.SearchMeaningFragment;
import com.example.testactivity.ui.Main.Search.Translation.SearchTranslationFragment;

public class SearchFragment extends Fragment {
    private FragmentSearchBinding binding;

    TextView meaningTab, translationTab;
    private int selectedTabNumber = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        meaningTab = binding.meaningTab;
        translationTab = binding.translationTab;

        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.search_fragment_container_view, SearchMeaningFragment.class, null)
                .commit();

        meaningTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTab(1);
            }
        });

        translationTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTab(2);
            }
        });
    }

    private void selectTab(int tabNumber) {
        TextView selectedTab, nonSelectedTab;

        if(tabNumber == 1){
            selectedTab = meaningTab;
            nonSelectedTab = translationTab;

            getParentFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.search_fragment_container_view, SearchMeaningFragment.class, null)
                    .commit();
        }
        else {
            selectedTab = translationTab;
            nonSelectedTab = meaningTab;

            getParentFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.search_fragment_container_view, SearchTranslationFragment.class, null)
                    .commit();
        }

        float slideTo = (tabNumber - selectedTabNumber) * selectedTab.getWidth();

        TranslateAnimation translateAnimation = new TranslateAnimation(0, slideTo, 0, 0);
        translateAnimation.setDuration(200);

        if(selectedTabNumber == 1){
            meaningTab.startAnimation(translateAnimation);
        }
        else{
            translationTab.startAnimation(translateAnimation);
        }

        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                selectedTab.setBackgroundResource(R.drawable.round_back_white15_20);
                selectedTab.setTypeface(null, Typeface.BOLD);
                selectedTab.setTextColor(Color.WHITE);

                nonSelectedTab.setBackgroundResource(android.R.color.transparent);
                nonSelectedTab.setTextColor(Color.parseColor("#7B7B7B"));
                nonSelectedTab.setTypeface(null, Typeface.NORMAL);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        selectedTabNumber = tabNumber;
    }

}
