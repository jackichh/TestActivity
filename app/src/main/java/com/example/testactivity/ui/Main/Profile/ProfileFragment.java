package com.example.testactivity.ui.Main.Profile;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testactivity.R;
import com.example.testactivity.databinding.FragmentProfileBinding;
import com.example.testactivity.logIn.LogInActivity;
import com.example.testactivity.ui.Main.Profile.EditProfile.EditProfileItemFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    TextView nickName, nick_name, email, full_name, phone, phoneNumber;
    ImageView rateUsImage, nickNameEdit;

    String userId;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nickNameEdit = binding.nickNameEdit;

        nick_name = binding.nickNameTxt;
        nickName = binding.nickName2Txt;
        email = binding.emailTxT;
        full_name = binding.fullNameTxt;
        phone = binding.phoneTxt;
        phoneNumber = binding.phoneNumber;

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        userId = firebaseAuth.getCurrentUser().getUid();


        DocumentReference docRef = db.collection("users").document(userId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        phoneNumber.setText(String.valueOf(document.getData().get("phone")));
                        nickName.setText(String.valueOf(Objects.requireNonNull(document.getData()).get("nickname")));
                        phone.setText(String.valueOf(document.getData().get("phone")));
                        full_name.setText(String.valueOf(document.getData().get("fullName")));
                        nick_name.setText(String.valueOf(document.getData().get("nickname")));
                        email.setText(String.valueOf(document.getData().get("email")));
                    } else {
                        Toast.makeText(getContext(), "Document does not exist!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        nickNameEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    getParentFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_profile_container_view, EditProfileItemFragment.class, null)
                            .commit();
                } catch (Exception e){
                    Log.d("TAG", e.getMessage());
                }

            }
        });


        rateUsImage = binding.rateUs;
        rateUsImage.setOnClickListener(v -> {
            RateUsDialog rateUsDialog = new RateUsDialog(Objects.requireNonNull(getContext()));
            rateUsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
            rateUsDialog.setCancelable(false);
            rateUsDialog.show();
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater = requireActivity().getMenuInflater();
        inflater.inflate(R.menu.profile_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logOut:
                AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
                ad.setCancelable(false);
                ad.setTitle("Log Out");
                ad.setMessage("Do you want to Log Out?");
                ad.setPositiveButton("Yes", (dialog, which) -> {
                    FirebaseAuth.getInstance().signOut();

                    startActivity(new Intent(getContext(), LogInActivity.class));
                });
                ad.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
                ad.show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
