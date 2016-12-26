package energigas.apps.systemstrategy.energigas;


import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.orm.SugarApp;

/**
 * Created by kelvi on 17/08/2016.
 */

public class Application extends SugarApp {
    @Override
    public void onCreate() {

        if (!FirebaseApp.getApps(this).isEmpty()) {
//            FirebaseApp.initializeApp(this, FirebaseOptions.fromResource(this));
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
        super.onCreate();//capau  //   //1/0
    }



}