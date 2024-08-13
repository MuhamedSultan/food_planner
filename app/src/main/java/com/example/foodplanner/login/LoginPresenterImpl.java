package com.example.foodplanner.login;

import com.example.foodplanner.login.pojo.User;
import com.example.foodplanner.login.view.LoginView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginPresenterImpl implements LoginPresenter{

    private LoginView view;
    FirebaseAuth firebaseAuth;

    public LoginPresenterImpl(LoginView view){
        this.view=view;
        firebaseAuth=FirebaseAuth.getInstance();
    }



     @Override
    public void loginWithEmail(User user) {
        try {
         view.showLoading();
         firebaseAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                 .addOnCompleteListener(task -> {
                     view.hideLoading();
                     if (task.isSuccessful()) {
                         view.successfulLogin();
                     } else {
                         Exception exception = task.getException();
                         handleLoginException(exception);
                     }
                 });
     } catch (Exception e) {
        view.hideLoading();
        handleLoginException(e);
    }
}

    private void handleLoginException(Exception exception) {
        if (exception instanceof FirebaseAuthInvalidCredentialsException) {
            view.failureLogin("Invalid email or password");
        } else {
            view.failureLogin("Something went wrong please, tyr again");
        }
    }
    }