package com.example.foodplanner.singup.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.databinding.FragmentSignupBinding;
import com.example.foodplanner.singup.pojo.User;
import com.example.foodplanner.singup.presenter.SignupPresenterImpl;
import com.example.foodplanner.util.CustomAlertDialog;
import com.google.android.material.snackbar.Snackbar;

public class SignupFragment extends Fragment implements SignupView {
    private FragmentSignupBinding binding;
    private SignupPresenterImpl signupPresenter;
    CustomAlertDialog customAlertDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signupPresenter=new SignupPresenterImpl(this);
        customAlertDialog=new CustomAlertDialog(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignupBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tvLogin.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.action_signupFragment_to_loginFragment);
        });
        binding.btnSignup.setOnClickListener(v->{
            String userName=binding.usernameEditText.getText().toString().trim();
            String email=binding.emailEditText.getText().toString().trim();
            String password=binding.passwordEditText.getText().toString().trim();
            String confirmPassword=binding.confirmPasswordEditText.getText().toString().trim();

            if (validateInput(userName,email,password,confirmPassword)){
                User user =new User(userName,email,password);
                signupPresenter.signupWithEmail(user);
            }
        });
    }

    private boolean validateInput(String userName, String email, String password, String confirmPassword) {
        if (userName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Snackbar.make(requireView(), "All fields are required", Snackbar.LENGTH_LONG).show();
            return false;
        }
        if (!password.equals(confirmPassword)) {
            Snackbar.make(requireView(), "Passwords do not match", Snackbar.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    @Override
    public void successfulSignup() {
        Navigation.findNavController(requireView()).navigate(R.id.action_signupFragment_to_homeFragment);
    }

    @Override
    public void failureSignup(String message) {
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