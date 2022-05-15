package com.example.testactivity.ui.Main.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testactivity.R;
import com.example.testactivity.databinding.FragmentParentProfileBinding;

public class ParentProfileFragment extends Fragment {

    private FragmentParentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentParentProfileBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_profile_container_view, ProfileFragment.class, null)
                .commit();
    }
}
