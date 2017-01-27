package energigas.apps.systemstrategy.energigas.entities;

import android.content.Context;
import android.os.Bundle;

/**
 * Created by kelvi on 25/01/2017.
 */

public class AlertaEntity {

    private int id;
    private String title;
    private String subject;
    private int logoNotification;
    private Context context;
    private Class aClassIntent;
    private String messageAction;
    private int logoAction;
    private Bundle bundle;


    public AlertaEntity(int id, String title, String subject, int logoNotification, Context context, Class aClassIntent, String messageAction, int logoAction, Bundle bundle) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.logoNotification = logoNotification;
        this.context = context;
        this.aClassIntent = aClassIntent;
        this.messageAction = messageAction;
        this.logoAction = logoAction;
        this.bundle = bundle;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getLogoNotification() {
        return logoNotification;
    }

    public void setLogoNotification(int logoNotification) {
        this.logoNotification = logoNotification;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Class getaClassIntent() {
        return aClassIntent;
    }

    public void setaClassIntent(Class aClassIntent) {
        this.aClassIntent = aClassIntent;
    }

    public String getMessageAction() {
        return messageAction;
    }

    public void setMessageAction(String messageAction) {
        this.messageAction = messageAction;
    }

    public int getLogoAction() {
        return logoAction;
    }

    public void setLogoAction(int logoAction) {
        this.logoAction = logoAction;
    }
}
