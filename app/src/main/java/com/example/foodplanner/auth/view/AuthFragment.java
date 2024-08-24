package com.example.foodplanner.auth.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.auth.presenter.AuthPresenter;
import com.example.foodplanner.auth.presenter.AuthPresenterImpl;
import com.example.foodplanner.databinding.FragmentAuthBinding;
import com.example.foodplanner.util.CustomAlertDialog;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.material.snackbar.Snackbar;


public class AuthFragment extends Fragment implements AuthView {
    private static final int RC_SIGN_IN = 100;
    private FragmentAuthBinding binding;
    private GoogleSignInClient googleSignInClient;
    private AuthPresenter authPresenter;
    CustomAlertDialog customAlertDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        authPresenter = new AuthPresenterImpl(this);

        GoogleSignInOptions gOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gOptions);
        customAlertDialog = new CustomAlertDialog(requireContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAuthBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hideActionBar();
        binding.guestMode.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.action_authFragment_to_homeFragment);
        });


        binding.btnEmailSignup.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_authFragment_to_signupFragment);
        });

        binding.tvLogin.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_authFragment_to_loginFragment);
        });

        binding.btnGoogleSignup.setOnClickListener(v -> {
            googleSignInClient.signOut().addOnCompleteListener(task -> {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            });
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            try {
                GoogleSignInAccount account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException.class);
                if (account != null) {
                    authPresenter.signInWithGoogle(account.getIdToken());
                }
            } catch (ApiException e) {
                failureSignIn("Google sign-in failed");
            }
        }
    }
    private void hideActionBar() {
        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().hide();
            }
        }
    }
    @Override
    public void successSignIn() {
        Navigation.findNavController(requireView()).navigate(R.id.action_authFragment_to_homeFragment);
    }

    @Override
    public void failureSignIn(String message) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        customAlertDialog.showDialog();
    }

    @Override
    public void hideLoading() {
       customAlertDialog.dismissDialog();
    }


}