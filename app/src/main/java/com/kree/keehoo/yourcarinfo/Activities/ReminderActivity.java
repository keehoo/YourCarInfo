package com.kree.keehoo.yourcarinfo.Activities;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.kree.keehoo.yourcarinfo.BuildConfig;
import com.kree.keehoo.yourcarinfo.DaoGeneratedFiles.Car;
import com.kree.keehoo.yourcarinfo.DaoGeneratedFiles.CarDao;
import com.kree.keehoo.yourcarinfo.R;
import com.kree.keehoo.yourcarinfo.Services.NotificationService;

public class ReminderActivity extends AppCompatActivity {
    public static final String BROADCAST_ACTION = BuildConfig.APPLICATION_ID + ".BROADCAST_ACTION";

    Long currentDaoId;
    MainActivity mainActivity;
    Car currentObject;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        currentDaoId = getIntent().getExtras().getLong("currentDaoId", 9000L);
        if (currentDaoId == 9000L) {
            Log.d("ReminderActivity", " current dao id 9000L,     ERROR");
            finish();
        }

        mainActivity = new MainActivity();
        CarDao carDao = mainActivity.initializeDaoSession();
        currentObject = carDao.load(currentDaoId);
    }

    private void scheduleNotification(String text, long milliseconds, int id) {

        if (id == 10 || id == 20) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            Intent broadcast = new Intent(BROADCAST_ACTION);
            broadcast.putExtra(NotificationService.EXTRA_NOTIFICATION_TEXT, text);
            Log.d("MainActivity", "Nowy intent o nazwie broadcast (INTENT(BROADCAST_ACTION) put Extra pod zmienna text= EXTRA_NOTIFICATION_TEXT");
            PendingIntent alarmAction = PendingIntent.getBroadcast(this, id, broadcast, PendingIntent.FLAG_UPDATE_CURRENT);
            Log.d("MainActivity", " PendingIntent alarmAction z parametrami this, hashCode, broadcast i pendingIntent.FLAG_UPDATE_CURRENT");
            pendingIntent = alarmAction; // to jest po to, zeby miec dostep do tego samego intentu zeby go anulowac (cancel)
            alarmManager.set(AlarmManager.RTC_WAKEUP, milliseconds, alarmAction);  //dodawanie long = int najwyrazniej daje longa...
            Log.d("MainActivity", "setAlarmManager z parametrami AlarmManager.RTC_WAKEUP, currentTime + sekundy ustawione wczesniej, alarmAction... alarmAction to jest pendingItentn powyzej");
            Toast.makeText(ReminderActivity.this, "Alarm Ustawiony na " + milliseconds + " sekund", Toast.LENGTH_SHORT).show();
        } else
            Log.d("scheduleNotification", "Bad id parameter for the schedule notification method, should be 10 for insurance or 20 for technical check");
    }

    protected void removeScheduledNotification(Long currentDaoId) {
        Intent broadcast = new Intent(BROADCAST_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 10, broadcast, PendingIntent.FLAG_CANCEL_CURRENT);
        boolean alarmUp = (PendingIntent.getBroadcast(this, 10,
                new Intent(BROADCAST_ACTION),
                PendingIntent.FLAG_NO_CREATE) != null);


        if (alarmUp == true) {

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
            Log.d("SetNotificationActivity", "       Disabling the alarm . . . . . DISABLED!  ");
            sharedPreferences.edit().putBoolean(MainActivity.INS_REMINDER_SET, false).apply();
            //sharedPreferences.edit().putBoolean(MainActivity.TECH_REMINDER_SET, false).apply();
            setInsReminderSet(false);
            resetSwitches();

        } else {
            Log.d("Is Alarm Set?", "                 Alarm isn't set at this moment, no need to disable anything");
            status.setText("Alarm is already disabled");
            resetSwitches();
            setInsReminderSet(false);
        }

    }
}