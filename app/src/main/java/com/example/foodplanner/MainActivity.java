package com.example.foodplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.foodplanner.databinding.ActivityMainBinding;
import com.example.foodplanner.db.LocalDataSource;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;
    NavController navController;
    FirebaseAuth firebaseAuth;
    private Menu menu;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(
                        R.id.homeFragment,R.id.searchFragment,
                        R.id.favouriteFragment,R.id.calenderFragment,R.id.loginFragment,R.id.signupFragment).build();

       Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        navController = Navigation.findNavController(this, R.id.navHostfragment);
        NavigationUI.setupActionBarWithNavController(this, navController,appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (LocalDataSource.getSavedUser(this) !=null){
            navController.navigate(R.id.homeFragment);
        }else {
            navController.navigate(R.id.splashFragment);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu,menu);
        this.menu=menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.signOut){
            LocalDataSource.clearUser(this);
            navController.navigate(R.id.loginFragment);
        } else if (item.getItemId()==R.id.Login) {
            navController.navigate(R.id.loginFragment);

        }
        return super.onOptionsItemSelected(item);

    }
    public void hideMenuItem(int itemId) {
        if (menu != null) {
            MenuItem item = menu.findItem(itemId);
            if (item != null) {
                item.setVisible(false);
            }
        }
    }
    public void showMenuItem(int itemId) {
        if (menu != null) {
            MenuItem item = menu.findItem(itemId);
            if (item != null) {
                item.setVisible(true);
            }
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (firebaseAuth.getCurrentUser() == null) {
            menu.findItem(R.id.signOut).setVisible(false);
            menu.findItem(R.id.Login).setVisible(true);
        } else {
            menu.findItem(R.id.signOut).setVisible(true);
            menu.findItem(R.id.Login).setVisible(false);
        }
        return true;
    }
}