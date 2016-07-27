package energigas.apps.systemstrategy.energigas.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import energigas.apps.systemstrategy.energigas.R;

/**
 * Created by kelvi on 25/07/2016.
 */

public class FABScrollBehavior extends FloatingActionButton.Behavior {

    Context context;

    public FABScrollBehavior(Context context, AttributeSet attrs) {
        super();
        this.context = context;
    }

    @Override
    public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout,
                                       final FloatingActionButton child,
                                       final View directTargetChild, final View target,
                                       final int nestedScrollAxes) {
        // Ensure we react to vertical scrolling
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                               View target, int dxConsumed, int dyConsumed, int dxUnconsumed,
                               int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed,
                dyUnconsumed);
        Log.d("FAB: ", coordinatorLayout.getChildCount() + "-" + target.getId()+"+++"+child.getVisibility()+"-"+dyConsumed);

         if (dyConsumed > 0 && child.getVisibility() == View.INVISIBLE) {

             child.show();
            child.hide();

        }else
        if (dyConsumed > 0
                && child.getVisibility() == View.VISIBLE) {



            child.hide();

        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {

            for (int i = 0; i < coordinatorLayout.getChildCount(); i++) {

                if (coordinatorLayout.getChildAt(i) instanceof Snackbar.SnackbarLayout) {
                    child.show();
                    return;
                }
            }

           child.setTranslationY(0.0f);

            child.show();
        }
    }
}
