package com.example.mananwason.parkr.Activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.mananwason.parkr.Fragment.FragmentNewGuests;
import com.example.mananwason.parkr.R;

/**
 * Created by mananwason on 7/24/17.
 */

public class AddGuest extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        Toolbar toolbar = (Toolbar) findViewById(R.id.ac_new_toolbar);



        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        textView = (TextView) findViewById(R.id.toolbar_title_new_ac);
        Typeface khandBold = Typeface.createFromAsset(getApplication().getAssets(), "Thinlines.ttf");
        textView.setTypeface(khandBold);
        textView.setText("Register Guest");

        getSupportFragmentManager().beginTransaction().replace(R.id.content_new_frame, new FragmentNewGuests()).commit();
    }

}
