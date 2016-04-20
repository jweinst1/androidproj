package com.user.globears;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.radialtimepicker.RadialTimePickerDialogFragment;

import org.w3c.dom.Text;

import java.util.Calendar;

public class TimesActivity extends AppCompatActivity {

    private static final int DATE_DIALOG_ID = 1;
    private static final int TIME_DIALOG_ID = 2;
    private TextView startDateText, startTimeText, endDateText, endTimeText;
    private Button startDate, startTime, endDate, endTime;
    private ImageView check;
    private int year, month, day, currYear, currMonth, currDay, startYear, startMonth, startDay, endYear, endMonth, endDay;
    private int hours, min, currHour, currMin, startHour, startMin, endHour, endMin;
    private String am_pm;
    boolean isStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times);

        startDate = (Button) findViewById(R.id.set_start_date);
        startTime = (Button) findViewById(R.id.set_start_time);

        endDate = (Button) findViewById(R.id.set_end_date);
        endTime = (Button) findViewById(R.id.set_end_time);

        startDateText = (TextView) findViewById(R.id.start_date_text);
        startTimeText = (TextView) findViewById(R.id.start_time_text);

        endDateText = (TextView) findViewById(R.id.end_date_text);
        endTimeText = (TextView) findViewById(R.id.end_time_text);

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                isStart = true;
                showDialog(DATE_DIALOG_ID);
            }
        });

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isStart = true;
                showDialog(TIME_DIALOG_ID);
            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isStart = false;
                showDialog(DATE_DIALOG_ID);
            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isStart = false;
                showDialog(TIME_DIALOG_ID);
            }
        });

        final Calendar calendar = Calendar.getInstance();
        year = startYear = endYear = currYear = calendar.get(Calendar.YEAR);
        month = startMonth = endMonth = currMonth = calendar.get(Calendar.MONTH);
        day = startDay = endDay = currDay = calendar.get(Calendar.DAY_OF_MONTH);

        hours = startHour = endHour = calendar.get(Calendar.HOUR);
        min = startMin = endMin = calendar.get(Calendar.MINUTE);

        isStart = true;
        updateDate();
        updateTime();

        isStart = false;
        updateDate();
        updateTime();

        check = (ImageView) findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timesAreValid()) {
                    displayToast("Valid!");
                }
            }
        });

    }

    private void updateTime() {
        int editedHours = hours;
        if (editedHours == 12) {
            am_pm = "PM";
            editedHours = 12;
        } else if (editedHours == 0) {
            am_pm = "AM";
            editedHours = 12;
        } else if (editedHours > 11) {
            am_pm = "PM";
            editedHours = editedHours % 12;
        } else {
            am_pm = "AM";
        }

        if (isStart) {
            startHour = hours;
            startMin = min;
            if (min < 10) {
                startTimeText.setText(new StringBuilder().append(editedHours).append(':').append(0)
                        .append(min).append(' ').append(am_pm));
            } else {
                startTimeText.setText(new StringBuilder().append(editedHours).append(':')
                        .append(min).append(' ').append(am_pm));
                }
        } else {
            endHour = hours;
            endMin = min;
            if (min < 10) {
                endTimeText.setText(new StringBuilder().append(editedHours).append(':').append(0)
                        .append(min).append(' ').append(am_pm));
            } else {
                endTimeText.setText(new StringBuilder().append(editedHours).append(':')
                        .append(min).append(' ').append(am_pm));
            }
        }
    }

    private void updateDate() {
        if (isStart) {
            startYear = year;
            startDay = day;
            startMonth = month;
            startDateText.setText(new StringBuilder().append(month + 1).append('-')
                    .append(day).append('-').append(year));
        } else {
            endYear = year;
            endDay = day;
            endMonth = month;
            endDateText.setText(new StringBuilder().append(month + 1).append('-')
                    .append(day).append('-').append(year));
        }
    }

    private DatePickerDialog.OnDateSetListener dateListener =
            new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int yr, int monthOfYear,
                                      int dayOfMonth) {
                    year = yr;
                    month = monthOfYear;
                    day = dayOfMonth;
                    updateDate();
                }
            };

    private TimePickerDialog.OnTimeSetListener timeListener =
            new TimePickerDialog.OnTimeSetListener() {

                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    hours = hourOfDay;
                    min = minute;
                    updateTime();
                }

            };
    protected Dialog onCreateDialog(int id){
        switch(id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, dateListener, year, month, day);
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, timeListener, hours, min, false);
        }
        return null;

    }

    private boolean timesAreValid() {
        if (startYear > endYear) {
            displayToast("Start date cannot exceed end date.");
            return false;
        } else if (startMonth > endMonth) {
            displayToast("Start date cannot exceed current date.");
            return false;
        } else if (startDay > endDay) {
            displayToast("Start date cannot exceed current date.");
            return false;
        }

        if (startYear == endYear && startMonth == endMonth && startDay == endDay) {
            if (startHour > endHour) {
                displayToast("Start time cannot exceed end time.");
                return false;
            } else if (startMin > endMin) {
                displayToast("Start time cannot exceed end time.");
                return false;
            }
        }
        return true;
    }

    private void displayToast(String string) {
        Toast toast = Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
}
