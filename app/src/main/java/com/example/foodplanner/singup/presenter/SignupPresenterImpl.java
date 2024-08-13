package com.example.foodplanner.singup.presenter;

import com.example.foodplanner.singup.pojo.User;
import com.example.foodplanner.singup.view.SignupView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignupPresenterImpl implements SignupPresenter{

    private final SignupView view;
    private final FirebaseAuth firebaseAuth;


    public SignupPresenterImpl(SignupView view){
        this.view=view;
        this.firebaseAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void signupWithEmail(User user) {
        view.showLoading();

        try {
            firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                    .addOnCompleteListener(task -> {
                        view.hideLoading();
                        if (task.isSuccessful()) {
                            view.successfulSignup();
                        } else {
                            Exception exception = task.getException();
                            handleSignupException(exception);
                        }
                    });
        } catch (Exception e) {
            view.hideLoading();
            handleSignupException(e);
        }
    }

    private void handleSignupException(Exception exception) {
        if (exception instanceof FirebaseAuthUserCollisionException) {
            view.failureSignup("Email already in use");
        } else if (exception instanceof FirebaseAuthInvalidCredentialsException) {
            view.failureSignup("Invalid email or password");
        } else {
            view.failureSignup("Something went wrong please, tyr again");
        }
    }
}