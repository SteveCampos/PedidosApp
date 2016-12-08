package energigas.apps.systemstrategy.energigas.entities;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;

/**
 * Created by kelvi on 05/12/2016.
 */

public class AccessFragment {
    private String id;
    private Fragment fragment;
    private int drawable;
    private int orden;

    public AccessFragment() {
    }
    public AccessFragment(String id, Fragment fragment, int drawable, int orden) {
        this.id = id;
        this.fragment = fragment;
        this.drawable = drawable;
        this.orden = orden;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
