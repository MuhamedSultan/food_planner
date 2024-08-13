package com.example.foodplanner.auth.presenter;

import com.example.foodplanner.auth.view.AuthView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class AuthPresenterImpl implements AuthPresenter{

    private final AuthView view;
    private final FirebaseAuth firebaseAuth;

    public AuthPresenterImpl(AuthView view) {
        this.view = view;
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void signInWithGoogle(String idToken) {
        view.showLoading();
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    view.hideLoading();
                    if (task.isSuccessful()) {
                        view.successSignIn();
                    } else {
                        view.failureSignIn("Something went wrong please, try again");
                    }
                });
    }
}

