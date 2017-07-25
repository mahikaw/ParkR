package com.example.mananwason.parkr.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.mananwason.parkr.Fragment.FragmentCurrentGuestBookings;
import com.example.mananwason.parkr.Fragment.FragmentDisplaySlots;
import com.example.mananwason.parkr.R;

/**
 * Created by mananwason on 7/24/17.
 */

public class HomeFragment extends Fragment {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getFragmentManager();

            switch (item.getItemId()) {

                case R.id.navigation_slots:
                    fragmentManager.beginTransaction().replace(R.id.content, new FragmentDisplaySlots(), "SLOTS").commit();

                    return true;
                case R.id.navigation_guests:
                    fragmentManager.beginTransaction().replace(R.id.content, new FragmentCurrentGuestBookings(), "PHONE").commit();

                    return true;
            }
            return false;
        }

    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, new FragmentDisplaySlots(), "SLOTS").commit();

        BottomNavigationView navigation = (BottomNavigationView) view.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentDisplaySlots myFragment = (FragmentDisplaySlots) getFragmentManager().findFragmentByTag("SLOTS");
                if (myFragment != null && myFragment.isVisible()) {
                    // add your code here
                    Log.d("Hello", "From the rent fragment");
                    startActivity(new Intent(getActivity(), AddSlot.class));

                }
                else {
                    Log.d("Hello", "From the phone fragment");
                    //TODO : Change fragment

                    startActivity(new Intent(getActivity(), AddGuest.class));

                }

            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        switch (id) {
//            case R.id.sort_popular:
//                new APIClient().getMoviesAPI().getPopularMovies(ApiKey.API_KEY).enqueue(new MoviesListProcessor());
//                break;
//
//            case R.id.sort_top:
//                new APIClient().getMoviesAPI().getTopMovies(ApiKey.API_KEY).enqueue(new MoviesListProcessor());
//                break;
//
//        }
        return super.onOptionsItemSelected(item);
    }
}

