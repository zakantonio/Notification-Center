package it.tim.test.zakantonio.notification;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class MainActivity extends ActionBarActivity {

    private Context context;

    private SimpleDateFormat dateFormatter, timeFormatter;
    private Calendar c;

    private TextView eventDate, eventTime;
    private DatePickerDialog alarmDatePicker;
    private TimePickerDialog alarmTimePicker;
    private Button btnConfirmEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ITALY);
        timeFormatter = new SimpleDateFormat("HH:mm", Locale.ITALY);
        c = Calendar.getInstance();

        eventDate = (TextView) findViewById(R.id.txv_event_date);
        eventTime = (TextView) findViewById(R.id.txv_event_time);
        btnConfirmEvent = (Button) findViewById(R.id.btn_confirm_event);

        eventDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmDatePicker = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        c.set(year, monthOfYear, dayOfMonth);
                        eventDate.setText(dateFormatter.format(c.getTime()));
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                alarmDatePicker.show();
            }
        });

        eventTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmTimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        c.set(Calendar.MINUTE, minute);
                        eventTime.setText(timeFormatter.format(c.getTime()));
                    }
                }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),true);
                alarmTimePicker.show();

            }
        });

        btnConfirmEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent("my.action.notification.event");
                myIntent.putExtra(NotificationCenter.NOT_ID, 0);
                myIntent.putExtra(NotificationCenter.NOT_TITLE, "My notification event");
                myIntent.putExtra(NotificationCenter.NOT_MSG, "This is a notification with a setted time.");
                myIntent.putExtra(NotificationCenter.NOT_ICON, R.drawable.ic_launcher);
                myIntent.putExtra(NotificationCenter.NOT_NEXT, NextActivity.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager)context.getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC, c.getTimeInMillis(), pendingIntent);
                eventDate.setText(getResources().getString(R.string.click_set_date));
                eventTime.setText(getResources().getString(R.string.click_set_time));

                Toast.makeText(context, "Notification created!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
