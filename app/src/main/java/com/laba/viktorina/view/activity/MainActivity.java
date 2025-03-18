package com.laba.viktorina.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.firebase.FirebaseApp;
import com.laba.viktorina.R;
import com.laba.viktorina.utils.NavigationListener;
import com.laba.viktorina.view.fragment.MenuFragment;

public class MainActivity extends AppCompatActivity implements NavigationListener {

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        hideSystemUI();
        navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        FirebaseApp.initializeApp(this);
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    public void navigateTo(int fragmentId,Bundle bundle) {
        navController.navigate(fragmentId,bundle);
    }
}