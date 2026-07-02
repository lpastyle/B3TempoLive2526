package com.example.b3tempolive2526;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TempoNotifications {
    private static final String LOG_TAG = TempoNotifications.class.getSimpleName();

    public static final String RED_TEMPO_ALERT_CHANNEL_ID = "red_tempo_alert_channel_id";
    public static final String WHITE_TEMPO_ALERT_CHANNEL_ID = "white_tempo_alert_channel_id";
    public static final String BLUE_TEMPO_ALERT_CHANNEL_ID = "blue_tempo_alert_channel_id";
    public static final String LAST_TEMPO_NOTIFICATION_DATE_KEY = "last_tempo_notification_date";


    public static void createNotificationChannels(Context context) {
        Log.d(LOG_TAG,"createNotificationChannels()");

        String[] channelIds = {
                BLUE_TEMPO_ALERT_CHANNEL_ID,
                WHITE_TEMPO_ALERT_CHANNEL_ID,
                RED_TEMPO_ALERT_CHANNEL_ID
        };

        int[] channelNames = {
                R.string.blue_channel_name,
                R.string.white_channel_name,
                R.string.red_channel_name
        };

        int[] channelDescriptions = {
                R.string.blue_channel_description,
                R.string.white_channel_description,
                R.string.red_channel_description
        };

        List<NotificationChannel> channels = new ArrayList<>();
        for (int i=0 ; i < channelIds.length; i++) {
            NotificationChannel channel = new NotificationChannel(
                    channelIds[i],
                    context.getString(channelNames[i]),
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription(context.getString(channelDescriptions[i]));
            channels.add(channel);
        }

        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.
        createNotificationChannels(channels);
    }
}
