package com.example.mega;

import static android.os.Build.VERSION_CODES.R;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;
public class user_page extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    FirebaseAuth mAuth;
    ProgressBar pgbar;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)){
               return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R);

        SessionManagement sessionManagement = new SessionManagement(user_page.this);
        String session = sessionManagement.getSession();

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        int colorCodeDark = Color.parseColor("#117a11");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(colorCodeDark);
        }
        try
        {
            Objects.requireNonNull(this.getSupportActionBar()).setTitle("Bus Tracking System");
        }
        catch (NullPointerException e){
            Toast.makeText(getApplicationContext(),"Null Pointer Exception occurred",Toast.LENGTH_SHORT).show();
        }
        mAuth=FirebaseAuth.getInstance();

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        replaceFragment(new profileFragment());
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.profile: {
                        //Toast.makeText(user_page.this, "Profile selected", Toast.LENGTH_SHORT).show();
                        replaceFragment(new profileFragment());
                        drawerLayout.closeDrawers();
                        break;
                    }
                    case R.id.route: {
                        Intent intent=new Intent(getApplicationContext(), Routes.class);
                        drawerLayout.closeDrawers();
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);F
                        break;
                    }
                    case R.id.task: {
//                        replaceFragment(new taskFragment());
//                        drawerLayout.closeDrawers();
                        if (session.equals("admin")) {
                            Intent intent=new Intent(getApplicationContext(), AdminMapsSharing.class);
                            drawerLayout.closeDrawers();
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }else{
                            replaceFragment(new taskFragment());
                            drawerLayout.closeDrawers();
                        }
                        break;
                    }
                    case R.id.logout: {
                        //Toast.makeText(user_page.this, "Logout selected", Toast.LENGTH_SHORT).show();
                        SessionManagement sessionManagement=new SessionManagement(user_page.this);
                        sessionManagement.removeSession();
                        mAuth.signOut();
                        moveToLogin();
                        break;
                    }
                }
                return false;
            }
        });
    }
    private void moveToLogin() {
        Intent intent=new Intent(user_page.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        FragmentTransaction replace = fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    /**
     *
     */
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}