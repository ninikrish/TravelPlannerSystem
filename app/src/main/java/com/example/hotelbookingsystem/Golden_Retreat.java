package com.example.hotelbookingsystem;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent; //used to go connect Google Calendar
import android.provider.CalendarContract; //used to get the check-in and check-out dates from a calendar format
import android.widget.Toast; //used to display a message to confirm booking is done
import java.util.Calendar; // //used to get the check-in and check-out dates from a calendar format
import android.widget.DatePicker;

public class Golden_Retreat extends AppCompatActivity {

    //reference variables
    Button btnCheckIn, btnCheckOut, btnConfirm;
    private int checkInYear, checkInMonth, checkInDay, checkOutYear, checkOutMonth, checkOutDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_golden_retreat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /* Start of code */

        //referencing the check in control btn
        btnCheckIn = findViewById(R.id.btnCheckIn);
        btnCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDate(btnCheckIn);
            }
        });

        //referencing the checkout control btn
        btnCheckOut = findViewById(R.id.btnCheckOut);
        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDate(btnCheckOut);
            }
        });

        //referencing the Confirm Booking control btn
        btnConfirm = findViewById(R.id.btnBooking);
        btnConfirm.setOnClickListener(v -> addToCalendar());
    }

    /* This function used to gather the input data of check-in and
   checkout data, use intent to connect Google Calendar and save the date/ booking */
    private void addToCalendar() {

        // a pop-up toast message to confirm booking once user clicks on the button
        Toast.makeText(this, "Booking Confirmed!", Toast.LENGTH_SHORT).show();

        Calendar start = Calendar.getInstance(); //used to create a Calendar Object
        start.set(checkInYear, checkInMonth, checkInDay, 14, 0); //sets the check-in date based on what the user selects with the time being static

        Calendar end = Calendar.getInstance(); //used to create a Calendar Object
        end.set(checkOutYear, checkOutMonth, checkOutDay, 10, 0); //sets the check-out date based on what the user selects with the time being static

        /* This code is the integration of using Intent to open Google Calendar and
         * showing the booking dates are reflected on Google Calendar */
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI); //the data specified when inserting on Calendar event
        intent.putExtra(CalendarContract.Events.TITLE, "Hotel Booking - Golden Retreat Hotel");  //the title that will reflect on the Google Calendar
        intent.putExtra(CalendarContract.Events.DESCRIPTION, "Stay at Golden Retreat Hotel");
        //the start and end time to save the date on Google Calendar
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, start.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end.getTimeInMillis());

        startActivity(intent); // launch the Google Calendar app with the above details filled in
    }

    /* This function is used when the user clicks the  button to select the check-in and
     * checkout date which opens the date picker control*/
    private void SelectDate(Button button) {

        //creates the pop-up of the calendar
        DatePickerDialog dialog = new DatePickerDialog(this, (datePicker, year, month, dayOfMonth) -> {
            button.setText(dayOfMonth + "/" + (month + 1) + "/" + year);  //gets the date as text (month is month+1 as months start from 0 in Java)

            if (button == btnCheckIn) {
                //store selected check-in date
                checkInYear = year;
                checkInMonth = month;
                checkInDay = dayOfMonth;
            } else {
                //store selected checkout date
                checkOutYear = year;
                checkOutMonth = month;
                checkOutDay = dayOfMonth;
            }

        }, 2026, 4, 12); //this is the defaults date when the pop-up calendar is shown

        dialog.show();
    }
}