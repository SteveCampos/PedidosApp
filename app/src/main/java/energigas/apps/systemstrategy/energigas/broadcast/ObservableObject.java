package energigas.apps.systemstrategy.energigas.broadcast;

import java.util.Observable;

/**
 * Created by kelvi on 09/12/2016.
 */

public class ObservableObject extends Observable {
    private static ObservableObject instance = new ObservableObject();

    public static ObservableObject getInstance() {
        return instance;
    }

    private ObservableObject() {
    }

    public void updateValue(boolean data) {
        synchronized (this) {
            setChanged();
            notifyObservers(data);
        }
    }
}
