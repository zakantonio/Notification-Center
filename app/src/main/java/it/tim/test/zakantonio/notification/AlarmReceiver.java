package it.tim.test.zakantonio.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by zakantonio on 13/04/2015.
 */
public class AlarmReceiver extends BroadcastReceiver {

    private NotificationManager mManager;

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction() == "my.action.notification.event") {
                NotificationCenter.makeNotification(context, intent);
        }
    }
}
