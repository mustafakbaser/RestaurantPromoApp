package com.mustafabaser.resto;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

public class HelloService extends Service {
    private static final int ONGOING_NOTIFICATION_ID = 12345;
    private Looper serviceLooper;
    private ServiceHandler serviceHandler;
    NotificationCompat.Builder notificationBuilder;
    NotificationManager notificationManager;
    private static final String CHANNEL_ID = "channel_id";

    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            stopSelf(msg.arg1);
        }
    }

    @Override
    public void onCreate() {
        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                THREAD_PRIORITY_BACKGROUND);
        thread.start();

        serviceLooper = thread.getLooper();
        serviceHandler = new ServiceHandler(serviceLooper);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, notificationIntent, 0);

        notificationBuilder =
                new NotificationCompat.Builder(this, "channel_id")
                        .setContentTitle(getString(R.string.Service_Title))
                        .setContentText(getString(R.string.Service_Welcome_Message))
                        .setSmallIcon(R.drawable.ic_menu)
                        .setContentIntent(pendingIntent);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "name", NotificationManager.IMPORTANCE_LOW));
            }
        }

        startForeground(ONGOING_NOTIFICATION_ID, notificationBuilder.build());

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, R.string.Service_Starting_Message, Toast.LENGTH_SHORT).show();

        Message msg = serviceHandler.obtainMessage();
        msg.arg1 = startId;
        serviceHandler.sendMessage(msg);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, R.string.Service_Done_Message, Toast.LENGTH_SHORT).show();
    }
}
