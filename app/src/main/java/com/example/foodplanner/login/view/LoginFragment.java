package com.example.foodplanner.login.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.databinding.FragmentLoginBinding;
import com.example.foodplanner.login.LoginPresenterImpl;
import com.example.foodplanner.login.pojo.User;
import com.example.foodplanner.util.CustomAlertDialog;
import com.google.android.material.snackbar.Snackbar;


public class LoginFragment extends Fragment implements LoginView{
private FragmentLoginBinding binding;
private LoginPresenterImpl loginPresenter;
private CustomAlertDialog customAlertDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPresenter=new LoginPresenterImpl(this);
        customAlertDialog=new CustomAlertDialog(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentLoginBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tvSignup.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_signupFragment);
        });
        binding.btnLogin.setOnClickListener(v->{
            String email=binding.emailEditText.getText().toString().trim();
            String password=binding.passwordEditText.getText().toString().trim();
            if (validateInput(email,password)) {
                User user = new User(email, password);
                loginPresenter.loginWithEmail(user);
            }
        });
    }

    private boolean validateInput(String email, String password) {
        if ( email.isEmpty() || password.isEmpty()) {
            Snackbar.make(requireView(), "All fields are required", Snackbar.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    @Override
    public void successfulLogin() {
        Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_homeFragment);
    }

    @Override
    public void failureLogin(String message) {
        Snackbar.make(requireView(),message,Snackbar.LENGTH_LONG).show();
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