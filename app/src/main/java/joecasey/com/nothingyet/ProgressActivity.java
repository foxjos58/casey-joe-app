package joecasey.com.nothingyet;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.widget.TextView;

import joecasey.com.nothingyet.base.BaseActivity;
import joecasey.com.nothingyet.fragments.ProgressFragment;

/**
 * Created by Joe F on 3/16/2015.
 */
public class ProgressActivity extends BaseActivity {
    public static class IntentExtras {
        public static final String TYPE = "type";
    }

    private String mType;

    @Override
    protected void init() {
        mType = getIntent().getExtras() == null ? "" : getIntent().getExtras().getString(IntentExtras.TYPE);
        super.init();
    }

    @Override
    protected void initContent() {
        getSupportFragmentManager().beginTransaction().add(R.id.content_container, ProgressFragment.newInstance(mType), "progressFragment").commit();
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
            title.setText(mType);
            title.setTextColor(Color.WHITE);
            getSupportActionBar().setCustomView(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
