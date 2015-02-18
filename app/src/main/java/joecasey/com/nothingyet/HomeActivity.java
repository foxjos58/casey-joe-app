package joecasey.com.nothingyet;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import joecasey.com.nothingyet.base.BaseActivity;
import joecasey.com.nothingyet.fragments.HomeFragment;


public class HomeActivity extends BaseActivity {

    @Override
    protected void initContent() {
        getSupportFragmentManager().beginTransaction().add(R.id.content_container, HomeFragment.newInstance(), "homeFragment").commit();
    }

    @Override
    protected void initActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayShowCustomEnabled(true);
            TextView title = new TextView(this);
            title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.f);
            title.setText(R.string.app_name);
            title.setTextColor(Color.WHITE);
            getSupportActionBar().setCustomView(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_hamburger);
        }
    }
}
