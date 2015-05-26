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
                Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                 mManager = (NotificationManager) context.getApplicationContext().getSystemService(context.getApplicationContext().NOTIFICATION_SERVICE);

                Intent notIntent = new Intent(context.getApplicationContext(), NextActivity.class);

                Notification notification = new Notification(intent.getExtras().getInt("not_icon", R.drawable.ic_launcher),
                        intent.getExtras().getString("not_title"), System.currentTimeMillis());

                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                PendingIntent pendingNotificationIntent = PendingIntent.getActivity( context.getApplicationContext(),0, notIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                notification.flags |= Notification.FLAG_AUTO_CANCEL;
                notification.setLatestEventInfo(context.getApplicationContext(),
                        intent.getExtras().getString("not_title"),
                        intent.getExtras().getString("not_msg"),
                        pendingNotificationIntent);
                notification.sound = sound;
                mManager.notify(intent.getExtras().getInt("not_id", 0), notification);

        }
    }
}
