package com.example.mananwason.parkr.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mananwason.parkr.Activities.LoginActivity;
import com.example.mananwason.parkr.Activities.MainActivity;
import com.example.mananwason.parkr.Models.GuestBooking;
import com.example.mananwason.parkr.Models.Slots;
import com.example.mananwason.parkr.R;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * Created by mananwason on 7/25/17.
 */

public class FragmentGuestDetails extends Fragment implements View.OnClickListener {
    private EditText personName;
    private EditText carNum;
    private EditText aptNum;
    private Button confirm;
    private DatabaseReference mDatabase;
    private Slots extras;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        extras = (Slots) getArguments().getSerializable("SLOT");
        Log.d("TAG", extras.getStart());
        View view = inflater.inflate(R.layout.fragment_phone_login, container, false);
        personName = (EditText) view.findViewById(R.id.person_name);
        carNum = (EditText) view.findViewById(R.id.car_number);
        aptNum = (EditText) view.findViewById(R.id.apt_number);
        confirm = (Button) view.findViewById(R.id.phone_confirm);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        confirm.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.phone_confirm:
                Log.d("ABC", personName.getText().toString());
                String uid = getActivity().getSharedPreferences(LoginActivity.UID, Context.MODE_PRIVATE).getString(LoginActivity.UID, "");
                GuestBooking user = new GuestBooking(uid, extras.getStart(), extras.getEnd(), aptNum.getText().toString(), personName.getText().toString(), carNum.getText().toString());
                mDatabase.child("guests/" + uid).push().setValue(user);
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("guests/" + uid);
                Query queryRef = ref.orderByChild("start").equalTo(extras.getStart());

                queryRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                        snapshot.getRef().setValue(null);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }
    }

}