package com.example.mananwason.parkr.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mananwason.parkr.Fragment.FragmentNewGuest;
import com.example.mananwason.parkr.Fragment.FragmentNewSlot;
import com.example.mananwason.parkr.R;

/**
 * Created by mananwason on 7/24/17.
 */

public class AddGuest extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_new_frame, new FragmentNewGuest()).commit();
    }

}
