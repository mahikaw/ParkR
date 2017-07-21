package com.example.mananwason.parkr;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * Created by mananwason on 7/16/17.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        View bottomSheet = findViewById(R.id.bottom_sheet);
        Button emailLoginButton = (Button) findViewById(R.id.email_login_button);
        Button phoneLoginButton = (Button) findViewById(R.id.phone_login_button);

        emailLoginButton.setOnClickListener(this);
        phoneLoginButton.setOnClickListener(this);

        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setPeekHeight(200);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetBehavior.setPeekHeight(0);
                }
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.email_login_button: {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentEmail fragment = new FragmentEmail();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();

                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                break;
            }
            case R.id.phone_login_button: {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentPhone fragment = new FragmentPhone();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();

                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            }

        }
    }
}
