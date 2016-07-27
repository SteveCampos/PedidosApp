package energigas.apps.systemstrategy.energigas.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import energigas.apps.systemstrategy.energigas.R;

/**
 * Created by Steve on 26/07/2016.
 */

public class PrintFacturaActivity extends AppCompatActivity{


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_print);

        setSupportActionBar(toolbar);

        setTitle("Title");
        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setTitle("Impresión");
        }
    }
}
