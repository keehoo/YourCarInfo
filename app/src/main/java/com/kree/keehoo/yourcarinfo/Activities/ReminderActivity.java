package com.kree.keehoo.yourcarinfo.Activities;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kree.keehoo.yourcarinfo.BuildConfig;
import com.kree.keehoo.yourcarinfo.DaoGeneratedFiles.Car;
import com.kree.keehoo.yourcarinfo.DaoGeneratedFiles.CarApplication;
import com.kree.keehoo.yourcarinfo.DaoGeneratedFiles.CarDao;
import com.kree.keehoo.yourcarinfo.DaoGeneratedFiles.DaoSession;
import com.kree.keehoo.yourcarinfo.JavaClasses.DefaultMethods;
import com.kree.keehoo.yourcarinfo.R;
import com.kree.keehoo.yourcarinfo.Services.NotificationService;
import com.victor.ringbutton.RingButton;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ReminderActivity extends AppCompatActivity {
    public static final String BROADCAST_ACTION = BuildConfig.APPLICATION_ID + ".BROADCAST_ACTION";

    Long currentDaoId;
    Car currentObject;
    int currentObjectHashcode;
    PendingIntent pendingIntent;
    RingButton ringButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        final DateTimeFormatter fmt = DateTimeFormat.forPattern("d MMMM, yyyy");
        Intent intent = getIntent();
        currentDaoId = intent.getExtras().getLong("currentID", 9000L);
        if (currentDaoId == 9000L) {
            Log.d("ReminderActivity", " current dao id 9000L,     ERROR");
            finish();
        }
        DaoSession daoSession = ((CarApplication) getApplicationContext()).getDaoSession();
        CarDao carDao = daoSession.getCarDao();
        currentObject = carDao.load(currentDaoId);
        currentObjectHashcode = currentObject.hashCode();
        Toast.makeText(ReminderActivity.this, "Current Object hashCode:   " + currentObject.hashCode(), Toast.LENGTH_SHORT).show();

        //TODO - remove buttons, add logics in the ringbutton, could be for both reminders insurance and tech check.

        ringButton = (RingButton)findViewById(R.id.ringButton);
        ringButton.setOnClickListener(new RingButton.OnClickListener() {
            @Override
            public void clickUp() {
                String text = "Za dwa tygodnie kończy się ubezpieczenie smaochodu"+ currentObject.getBrand() + " "+currentObject.getModel();
scheduleNotification(text, currentObject.getDateOfInsuranceEnd());
            }

            @Override
            public void clickDown() {
                String text = "Za dwa tygodnie kończy ważność przeglądu technicznego"+ currentObject.getBrand() + " "+currentObject.getModel();
                scheduleNotification(text, currentObject.getDateOfTechEnd());
            }
        });
    }


    public static int safeLongToInt(long l) {
        int i = (int) l;
        if ((long) i != l) {
            throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
        }
        return i;
    }


    private void scheduleNotification(String text, long milliseconds) {


        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent broadcast = new Intent(BROADCAST_ACTION);
        broadcast.putExtra(NotificationService.EXTRA_NOTIFICATION_TEXT, text);
        PendingIntent alarmAction = PendingIntent.getBroadcast(this, currentObject.hashCode(), broadcast, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntent = alarmAction; // to jest po to, zeby miec dostep do tego samego intentu zeby go anulowac (cancel)
        alarmManager.set(AlarmManager.RTC_WAKEUP, milliseconds, alarmAction);  //dodawanie long = int najwyrazniej daje longa...

        Log.d("MainActivity", "Nowy intent o nazwie broadcast (INTENT(BROADCAST_ACTION) put Extra pod zmienna text= EXTRA_NOTIFICATION_TEXT");
        Log.d("MainActivity", " PendingIntent alarmAction z parametrami this, hashCode, broadcast i pendingIntent.FLAG_UPDATE_CURRENT");
        Log.d("MainActivity", "setAlarmManager z parametrami AlarmManager.RTC_WAKEUP, currentTime + sekundy ustawione wczesniej, alarmAction... alarmAction to jest pendingItentn powyzej");
        Toast.makeText(ReminderActivity.this, "Alarm Ustawiony na dwa tygodnie prze końcem", Toast.LENGTH_SHORT).show();
    }


    protected void removeScheduledNotification() {
        Intent broadcast = new Intent(BROADCAST_ACTION);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, currentObject.hashCode(), broadcast, PendingIntent.FLAG_CANCEL_CURRENT);
        boolean alarmUp = (PendingIntent.getBroadcast(this, currentObject.hashCode(),
                new Intent(BROADCAST_ACTION),
                PendingIntent.FLAG_NO_CREATE) != null);
        if (alarmUp == true) {
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();

        } else {

        }
    }
}