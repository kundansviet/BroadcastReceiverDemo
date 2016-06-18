package androidwarriors.broadcastrecieverdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

/**
 * Created by kundan on 6/12/2016.
 */
public class MyReciever extends BroadcastReceiver {

    private String notification_msg;
    NotificationManager mNotificationManager;
    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        mNotificationManager = (NotificationManager) context.getApplicationContext().getSystemService(context.NOTIFICATION_SERVICE);

        final LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            notification_msg = "Gps is Disabled";
        } else {
            notification_msg = "Gps is Enabled";
        }

        genrateSimpleNotification();
    }

    /*
    Method to generate simple notification
     */
    void genrateSimpleNotification() {

        Intent resultIntent = new Intent(context, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

        stackBuilder.addParentStack(MainActivity.class);

        stackBuilder.addNextIntent(resultIntent);


        PendingIntent resultPending = stackBuilder
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        // Building the notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                .setContentTitle(" Androidwarriors Gps notifier") // main title of the notification
                .setContentText(notification_msg) // notification text
                .setContentIntent(resultPending); // notification intent

        // mId allows you to update the notification later on.
        mNotificationManager.notify(10, mBuilder.build());
    }

}
