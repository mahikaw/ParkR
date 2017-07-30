package com.example.mananwason.parkr.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.mananwason.parkr.Activities.LoginActivity;
import com.example.mananwason.parkr.Activities.MainActivity;
import com.example.mananwason.parkr.Models.GuestBooking;
import com.example.mananwason.parkr.Models.Slots;
import com.example.mananwason.parkr.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.regex.Pattern;

/**
 * Created by mananwason on 7/25/17.
 */

public class FragmentGuestDetails extends Fragment implements View.OnClickListener {
    private TextInputEditText personName;
    private TextInputEditText carNum;
    private TextInputEditText phoneNum;
    private TextInputEditText aptNum;
    private DatabaseReference mDatabase;
    private Slots extras;
    private Spinner floor;
    private TextInputLayout tilPersonName;
    private TextInputLayout tilCarNumber;
    private TextInputLayout tilHouseNumber;
    private TextInputLayout tilPhoneNumber;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        extras = (Slots) getArguments().getSerializable("SLOT");
        View view = inflater.inflate(R.layout.fragment_guest_details, container, false);
        personName = (TextInputEditText) view.findViewById(R.id.person_name);
        carNum = (TextInputEditText) view.findViewById(R.id.car_number);
        phoneNum = (TextInputEditText) view.findViewById(R.id.guest_phone_number);
        aptNum = (TextInputEditText) view.findViewById(R.id.apt_number);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        floor = (Spinner) view.findViewById(R.id.floor_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        floor.setAdapter(adapter);
        tilPersonName = (TextInputLayout) view.findViewById(R.id.text_input_layout2);
        tilCarNumber = (TextInputLayout) view.findViewById(R.id.text_input_layout3);
        tilHouseNumber = (TextInputLayout) view.findViewById(R.id.text_input_layout);
        tilPhoneNumber = (TextInputLayout) view.findViewById(R.id.text_input_layout4);
        return view;
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_add_slot, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.confirmSlot:
//                DateUtils utils = new DateUtils();
//
//
//                String uid = getActivity().getSharedPreferences(LoginActivity.UID, Context.MODE_PRIVATE).getString(LoginActivity.UID, "");
//                Calendar startTime = utils.UiToCalendar(startDateTV.getText().toString(), startTimeTV.getText().toString());
//                Calendar endTime = utils.UiToCalendar(endDateTV.getText().toString(), endTimeTV.getText().toString());
//                if (startTime.compareTo(endTime) < 0) {
//                    Slots user = new Slots(uid, utils.CalendarToISO(startTime), utils.CalendarToISO(endTime), HouseNumberTV.getText().toString());
//                    mDatabase.child("slots/" + uid).push().setValue(user);
//                    Intent intent = new Intent(getActivity(), MainActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//                    startActivity(intent);
//                    getActivity().getSupportFragmentManager().popBackStack();
//                } else {
//                    Toast.makeText(getActivity(), "End Time should be greater than Start Time. Please try again!", Toast.LENGTH_LONG).show();
//                }

                String name = personName.getText().toString();
                String apartmentNumber = aptNum.getText().toString();
                String carNo = carNum.getText().toString();
                String phone = phoneNum.getText().toString();
                boolean error = false;
                if (!isValidPhoneNumber(phone)) {
                    Log.d("TAG", "VALIp");
                    tilPhoneNumber.setError("You need to enter a valid phone number");
                    error = true;
                }
                if (name.length() < 5) {
                    Log.d("TAG", "VALIn");
                    tilPersonName.setError("Guest name needs to be atleast 5 characters");
                    error = true;
                }
                if (apartmentNumber.matches("") && apartmentNumber.length() < 3) {
                    Log.d("TAG", "VALIa");

                    tilHouseNumber.setError("You need to enter apartment number");
                    error = true;
                }
                if (carNo.length() < 7) {
                    Log.d("TAG", "VALIc");
                    tilCarNumber.setError("Not a valid car number");
                    error = true;
                }
                if (!error) {
                    String uid = getActivity().getSharedPreferences(LoginActivity.UID, Context.MODE_PRIVATE).getString(LoginActivity.UID, "");
                    GuestBooking user = new GuestBooking(uid, extras.getStart(), extras.getEnd(), aptNum.getText().toString(), personName.getText().toString(), carNum.getText().toString(), "");
                    mDatabase.child("guests/" + uid).push().setValue(user);
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("slots/" + uid);
                    Log.d("TAG", extras.getStart());
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


                }
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isValidPhoneNumber(CharSequence phoneNumber) {
        if (!TextUtils.isEmpty(phoneNumber)) {
            return Patterns.PHONE.matcher(phoneNumber).matches();
        }
        return false;
    }

    private boolean isValidCarNumber(String carNumber) {
        String vehiclePattern = "^[A-Z]{2}[ -][0-9]{1,2}(?: [A-Z])?(?: [A-Z]*)? [0-9]{4}$";

        Pattern pattern = Pattern.compile(vehiclePattern);
        if (pattern.matcher(carNumber).matches()) {
            return true;
        } else {
            return false;
        }
    }


}