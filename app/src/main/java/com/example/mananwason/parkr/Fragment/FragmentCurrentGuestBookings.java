package com.example.mananwason.parkr.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mananwason.parkr.Activities.LoginActivity;
import com.example.mananwason.parkr.Models.GuestBooking;
import com.example.mananwason.parkr.Models.Slots;
import com.example.mananwason.parkr.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mananwason on 7/25/17.
 */

public class FragmentCurrentGuestBookings extends Fragment {

    private FirebaseRecyclerAdapter<GuestBooking, FragmentCurrentGuestBookings.SlotsHolder> mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_display_slots, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_tracks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        String uid = getActivity().getSharedPreferences(LoginActivity.UID, Context.MODE_PRIVATE).getString(LoginActivity.UID, "");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("guests/" + uid);
        mAdapter = new FirebaseRecyclerAdapter<GuestBooking, FragmentCurrentGuestBookings.SlotsHolder>(
                GuestBooking.class,
                R.layout.slot_item,
                FragmentCurrentGuestBookings.SlotsHolder.class,
                ref) {
            @Override
            public void populateViewHolder(FragmentCurrentGuestBookings.SlotsHolder holder, GuestBooking chat, int position) {
                holder.setName("For " + chat.getGuestName() + " in Apartment Number " +chat.getApartmentNum());
                holder.setMessage("From " + chat.getStart() + " To " + chat.getEnd());
            }
        };

        recyclerView.setAdapter(mAdapter);
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

    public static class SlotsHolder extends RecyclerView.ViewHolder {
        private final TextView mNameField;
        private final TextView mMessageField;

        public SlotsHolder(View itemView) {
            super(itemView);
            mNameField = (TextView) itemView.findViewById(R.id.startTime);
            mMessageField = (TextView) itemView.findViewById(R.id.endTime);
        }

        public void setName(String name) {
            mNameField.setText(name);
        }

        public void setMessage(String message) {
            mMessageField.setText(message);
        }
    }
}