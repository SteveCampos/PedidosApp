package energigas.apps.systemstrategy.energigas.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import energigas.apps.systemstrategy.energigas.R;
import energigas.apps.systemstrategy.energigas.entities.AlertaEntity;

/**
 * Created by kelvi on 25/01/2017.
 */

public class NotificacionManagerUtils {
    private AlertaEntity alertaEntity;
    private Intent intent;
    NotificationManager notificationManager;

    private NotificationCompat.Builder mBuilder;


    public NotificacionManagerUtils(AlertaEntity alertaEntity) {
        this.alertaEntity = alertaEntity;
        intent = new Intent(alertaEntity.getContext(), alertaEntity.getaClassIntent());
        notificationManager =
                (NotificationManager) alertaEntity.getContext().getSystemService(Context.NOTIFICATION_SERVICE);

    }

    public void showNotificacion() {
        Bitmap largeIcon = BitmapFactory.decodeResource(alertaEntity.getContext().getResources(), R.drawable.logo);

        // use System.currentTimeMillis() to have a unique ID for the pending intent

        mBuilder = new NotificationCompat.Builder(alertaEntity.getContext());
        mBuilder.setContentTitle(alertaEntity.getTitle())
                .setContentText(alertaEntity.getSubject())
                .setAutoCancel(false)
                .setSmallIcon(alertaEntity.getLogoNotification());


    }

    public void updateImportNotification(int cantidad) {
        mBuilder.setProgress(100, cantidad, false);
        if (cantidad == 0) {
            //  Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            //  mBuilder.setSound(uri);
        }

        if (cantidad == 100) {
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            mBuilder.setSound(uri);
            mBuilder.setContentTitle("Pedidos Agregados");
            mBuilder.setContentText("Importado exitosamente");
        }
        notificationManager.notify(alertaEntity.getId(), mBuilder.build());
    }

    public void showNotificationCustom() {


        RemoteViews contentView = new RemoteViews(alertaEntity.getContext().getPackageName(), R.layout.layout_item_notification);
        contentView.setTextViewText(R.id.textTitle, alertaEntity.getTitle());
        contentView.setTextViewText(R.id.textDescripcion, alertaEntity.getSubject());

        contentView.setImageViewResource(R.id.imageView3, alertaEntity.getLogoNotification());
        if (alertaEntity.getBundle() != null) {
            intent.putExtras(alertaEntity.getBundle());
        }

        PendingIntent pIntent = PendingIntent.getActivity(alertaEntity.getContext(), (int) System.currentTimeMillis(), intent, 0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(alertaEntity.getContext())
                // Set Icon
                .setSmallIcon(alertaEntity.getLogoNotification())
                // Set Ticker Message
                .setTicker(alertaEntity.getContext().getString(R.string.app_name))
                // Dismiss Notification
                .setAutoCancel(true)
                // Set PendingIntent into Notification
                .setContentIntent(pIntent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // build a complex notification, with buttons and such
            //
            builder = builder.setContent(contentView);
            builder.setSmallIcon(alertaEntity.getLogoNotification());
        } else {
            // Build a simpler notification, without buttons
            //
            builder = builder.setContentTitle(alertaEntity.getTitle())
                    .setContentText(alertaEntity.getSubject())
                    .setSmallIcon(R.drawable.logo);
        }


        Notification notification = builder.mNotification;


        NotificationManager mNotificationManager = (NotificationManager) alertaEntity.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notification.defaults = Notification.DEFAULT_LIGHTS;
        notification.defaults = Notification.DEFAULT_VIBRATE;
        notification.defaults = Notification.DEFAULT_SOUND;
        notification.contentIntent = pIntent;
        mNotificationManager.notify(alertaEntity.getId(), notification);
    }
}
