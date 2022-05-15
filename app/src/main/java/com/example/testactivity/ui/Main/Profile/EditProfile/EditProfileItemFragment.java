package com.example.testactivity.ui.Main.Profile.EditProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testactivity.R;
import com.example.testactivity.databinding.FragmentEditProfileItemBinding;
import com.example.testactivity.ui.Main.Profile.ProfileFragment;

public class EditProfileItemFragment extends Fragment {

    private FragmentEditProfileItemBinding binding;
    ImageView back;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditProfileItemBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        back=binding.editProfileItemBack;

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_profile_container_view, ProfileFragment.class, null)
                        .commit();
            }
        });
    }
}