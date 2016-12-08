package energigas.apps.systemstrategy.energigas.interfaces;

import java.util.HashMap;
import java.util.List;

import energigas.apps.systemstrategy.energigas.entities.AccessFragment;

/**
 * Created by kelvi on 05/12/2016.
 */

public interface IntentListenerAccess {
    void onIntentListenerAcces(HashMap<String,Boolean> booleanHashMap);
    void onFragmentAccess(List<AccessFragment> accessFragmentList);
}
