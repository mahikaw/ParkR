package com.example.mananwason.parkr.Activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mananwason.parkr.Fragment.FragmentRentParking;
import com.example.mananwason.parkr.R;

public class MainActivity extends AppCompatActivity {

    private static final String FRAGMENT_TAG_HOME = "HOMEFRAG";
    private NavigationView navigationView;
    //    private CoordinatorLayout mainFrame;
    private SmoothActionBarDrawerToggle smoothActionBarToggle;

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private int currentMenuItemId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
//        mainFrame = (CoordinatorLayout) findViewById(R.id.layout_main);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.content, new HomeFragment()).commit();
        setUpToolbar();
        setUpNavDrawer();
        setupDrawerContent(navigationView);
        if (savedInstanceState == null) {
            currentMenuItemId = R.id.nav_home;
        }

//
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void setUpToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    private void setUpNavDrawer() {
        if (toolbar != null) {
            final ActionBar ab = getSupportActionBar();
            assert ab != null;
            smoothActionBarToggle = new SmoothActionBarDrawerToggle(this,
                    drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

            drawerLayout.addDrawerListener(smoothActionBarToggle);
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);
            smoothActionBarToggle.syncState();
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        doMenuAction(id);

                        return true;
                    }
                });
    }

    private void doMenuAction(int menuItemId) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        addShadowToAppBar(true);
        switch (menuItemId) {
            case R.id.nav_home:
                Log.d("ABC", "HOME");
                smoothActionBarToggle.runWhenIdle(new Runnable() {
                    @Override
                    public void run() {
                        fragmentManager.beginTransaction()
                                .replace(R.id.content_frame, new HomeFragment(), FRAGMENT_TAG_HOME).commit();
                        if (getSupportActionBar() != null) {
                            getSupportActionBar().setTitle(R.string.menu_home);
                        }
                        appBarLayout.setExpanded(true, true);
                    }
                });

                break;
        }
        currentMenuItemId = menuItemId;
        drawerLayout.closeDrawers();
    }

    public void addShadowToAppBar(boolean addShadow) {
        if (addShadow) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                appBarLayout.setElevation(12);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                appBarLayout.setElevation(0);
            }
        }
    }

}
