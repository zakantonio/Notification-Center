package it.tim.test.zakantonio.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

/**
 * Created by zakantonio on 26/05/2015.
 */
public class NotificationCenter {

    // All the costant to set to have a perfect notification
    public static final String NOT_TITLE = "not_title";
    public static final String NOT_MSG = "not_msg";
    public static final String NOT_ID = "not_id";
    public static final String NOT_ICON = "not_icon";
    public static final String NOT_NEXT = "not_next";

    private static NotificationManager mManager;

    public static void makeNotification (Context context, Intent intent) {
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mManager = (NotificationManager) context.getApplicationContext().getSystemService(context.getApplicationContext().NOTIFICATION_SERVICE);

        Intent notIntent = new Intent(context.getApplicationContext(), (Class) intent.getExtras().get(NOT_NEXT));

        Notification notification = new Notification(intent.getExtras().getInt(NOT_ICON, R.drawable.ic_launcher),
                intent.getExtras().getString(NOT_TITLE), System.currentTimeMillis());

        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingNotificationIntent = PendingIntent.getActivity( context.getApplicationContext(),0, notIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.setLatestEventInfo(context.getApplicationContext(),
                intent.getExtras().getString(NOT_TITLE),
                intent.getExtras().getString(NOT_MSG),
                pendingNotificationIntent);
        notification.sound = sound;
        mManager.notify(intent.getExtras().getInt(NOT_ID, 0), notification);
    }
}
