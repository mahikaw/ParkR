package com.example.mananwason.parkr.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mananwason.parkr.Activities.LoginActivity;
import com.example.mananwason.parkr.Models.GuestBooking;
import com.example.mananwason.parkr.Models.Slots;
import com.example.mananwason.parkr.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by mananwason on 7/14/17.
 */

public class FragmentNewGuests extends Fragment {
    private FirebaseRecyclerAdapter<Slots, FragmentNewGuests.SlotsHolder> mAdapter;
    private DatabaseReference mDatabase;
    private View mEmptyListViewGuests;
    private View mEmptyListViewSlots;
    private ImageView parkingImage;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        Log.d("ABC", "ABC");
        String uid = getActivity().getSharedPreferences(LoginActivity.UID, Context.MODE_PRIVATE).getString(LoginActivity.UID, "");
//        GuestBooking user = new GuestBooking(uid, "ABC", "DF", "S");
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//
//        mDatabase.child("guests/" + uid).push().setValue(user);

        View view = inflater.inflate(R.layout.fragment_display_slots, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_tracks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mEmptyListViewSlots = view.findViewById(R.id.emptyTextViewSlots);
        mEmptyListViewGuests = view.findViewById(R.id.emptyTextViewGuests);
        parkingImage = view.findViewById(R.id.emptySlotsImage);
        mEmptyListViewGuests.setVisibility(View.INVISIBLE);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("slots/").child(uid);
        mAdapter = new FirebaseRecyclerAdapter<Slots, FragmentNewGuests.SlotsHolder>(
                Slots.class,
                R.layout.slot_item,
                FragmentNewGuests.SlotsHolder.class,
                ref) {
            @Override
            public void populateViewHolder(FragmentNewGuests.SlotsHolder holder, Slots chat, int position) {
                holder.setName(chat.getStart());
                holder.setMessage(chat.getEnd());
            }
            @Override
            public void onDataChanged() {
                // if there are no chat messages, show a view that invites the user to add a message
                mEmptyListViewSlots.setVisibility(mAdapter.getItemCount() == 0 ? View.VISIBLE : View.INVISIBLE);
                parkingImage.setVisibility(mAdapter.getItemCount() == 0 ? View.VISIBLE : View.INVISIBLE);
            }


            @Override
            public SlotsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                SlotsHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                viewHolder.setOnClickListener(new SlotsHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        FragmentGuestDetails fragment = new FragmentGuestDetails();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("SLOT", mAdapter.getItem(position));
                        fragment.setArguments(bundle);
                        getFragmentManager().beginTransaction().replace(R.id.content_new_frame, fragment).commit();
                    }
                });
                return viewHolder;
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

        return super.onOptionsItemSelected(item);
    }

    public static class SlotsHolder extends RecyclerView.ViewHolder {
        private final TextView mNameField;
        private final TextView mMessageField;

        public SlotsHolder(View itemView) {
            super(itemView);
            mNameField = (TextView) itemView.findViewById(R.id.startTime);
            mMessageField = (TextView) itemView.findViewById(R.id.endTime);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mClickListener.onItemClick(view, getAdapterPosition());
//                    view.getContext().startActivity(new Intent());
                }
            });
        }


        public void setName(String name) {
            mNameField.setText(name);
        }

        public void setMessage(String message) {
            mMessageField.setText(message);
        }

        private SlotsHolder.ClickListener mClickListener;

        //Interface to send callbacks...
        public interface ClickListener {
            public void onItemClick(View view, int position);

        }

        public void setOnClickListener(SlotsHolder.ClickListener clickListener) {
            mClickListener = clickListener;
        }

    }
}

