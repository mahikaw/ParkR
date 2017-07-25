package com.example.mananwason.parkr.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.mananwason.parkr.Activities.LoginActivity;
import com.example.mananwason.parkr.Activities.MainActivity;
import com.example.mananwason.parkr.Models.Slots;
import com.example.mananwason.parkr.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


/**
 * Created by mananwason on 7/20/17.
 */

public class FragmentNewSlot extends Fragment {
    private DatabaseReference mDatabase;
    static EditText startEditText;
    static EditText endEditText;
    private EditText aptEditText;
    public static String current = "";
    private Button confirm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_rent_parking, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        confirm = (Button) view.findViewById(R.id.confirmSlot);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid = getActivity().getSharedPreferences(LoginActivity.UID, Context.MODE_PRIVATE).getString(LoginActivity.UID, "");
                Slots user = new Slots(uid, startEditText.getText().toString(), endEditText.getText().toString(), aptEditText.getText().toString());
                mDatabase.child("slots/" + uid).push().setValue(user);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        aptEditText = (EditText) view.findViewById(R.id.ed_apt);
        startEditText = (EditText) view.findViewById(R.id.ed_start_time);
        startEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current = "START";
                showTruitonTimePickerDialog(v);
                showTruitonDatePickerDialog(v);
            }
        });
        endEditText = (EditText) view.findViewById(R.id.ed_end_time);
        endEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current = "END";
                showTruitonTimePickerDialog(v);
                showTruitonDatePickerDialog(v);
            }
        });


        return view;
    }

    public void showTruitonDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            if (FragmentNewSlot.current.contentEquals("START")) {
                startEditText.setText(day + "/" + (month + 1) + "/" + year);
            } else {
                endEditText.setText(day + "/" + (month + 1) + "/" + year);
            }
        }
    }

    public void showTruitonTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
    }

    public static class TimePickerFragment extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            if (FragmentNewSlot.current.contentEquals("START")) {
                startEditText.setText(startEditText.getText() + "-" + hourOfDay + ":" + minute);
            } else {
                endEditText.setText(endEditText.getText() + "-" + hourOfDay + ":" + minute);
            }

        }

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

}


