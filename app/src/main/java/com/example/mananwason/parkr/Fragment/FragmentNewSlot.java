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
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mananwason.parkr.Activities.LoginActivity;
import com.example.mananwason.parkr.Activities.MainActivity;
import com.example.mananwason.parkr.Models.Slots;
import com.example.mananwason.parkr.R;
import com.example.mananwason.parkr.Utils.DateUtils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;


/**
 * Created by mananwason on 7/20/17.
 */

public class FragmentNewSlot extends Fragment {
    private DatabaseReference mDatabase;
    static TextView startTimeTV;
    static TextView endTimeTV;
    static TextView startDateTV;
    static TextView endDateTV;
    private Spinner floor;
    private EditText HouseNumberTV;
    public static String current = "";
    static SimpleDateFormat uiDateFormat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_add_slot, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        floor = (Spinner) view.findViewById(R.id.planets_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        floor.setAdapter(adapter);
        uiDateFormat = new SimpleDateFormat("EEE dd MMM, yyyy", Locale.ENGLISH);

        HouseNumberTV = (EditText) view.findViewById(R.id.ed_apt);
        startTimeTV = (TextView) view.findViewById(R.id.ed_start_time);
        startDateTV = (TextView) view.findViewById(R.id.ed_start_date);
        endDateTV = (TextView) view.findViewById(R.id.ed_end_date);
        endTimeTV = (TextView) view.findViewById(R.id.ed_end_time);

        startTimeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current = "START";
                showTruitonTimePickerDialog(v);
            }
        });
        endTimeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current = "END";
                showTruitonTimePickerDialog(v);
            }
        });
        startDateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current = "START";
                showTruitonDatePickerDialog(v);
            }
        });
        endDateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current = "END";
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
            Calendar calendar = new GregorianCalendar(year, month, day);
//            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            uiDateFormat.setCalendar(calendar);
            if (FragmentNewSlot.current.contentEquals("START")) {
                startDateTV.setText(uiDateFormat.format(calendar.getTime()));

            } else {
                endDateTV.setText(uiDateFormat.format(calendar.getTime()));
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
            boolean isPM = (hourOfDay >= 12);
            String time = String.format("%02d:%02d %s", (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minute, isPM ? "PM" : "AM");

            if (FragmentNewSlot.current.contentEquals("START")) {
                startTimeTV.setText(time);
            } else {
                endTimeTV.setText(time);
            }

        }

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
                DateUtils utils = new DateUtils();


                String uid = getActivity().getSharedPreferences(LoginActivity.UID, Context.MODE_PRIVATE).getString(LoginActivity.UID, "");
                Calendar startTime = utils.UiToCalendar(startDateTV.getText().toString(), startTimeTV.getText().toString());
                Calendar endTime = utils.UiToCalendar(endDateTV.getText().toString(), endTimeTV.getText().toString());
                if (startTime.compareTo(endTime) < 0) {
                    Slots user = new Slots(uid, utils.CalendarToISO(startTime), utils.CalendarToISO(endTime), HouseNumberTV.getText().toString());
                    mDatabase.child("slots/" + uid).push().setValue(user);
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    Toast.makeText(getActivity(), "End Time should be greater than Start Time. Please try again!", Toast.LENGTH_LONG).show();
                }

        }

        return super.onOptionsItemSelected(item);
    }

}


