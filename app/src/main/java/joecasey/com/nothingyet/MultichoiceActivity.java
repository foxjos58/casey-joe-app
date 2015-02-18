package joecasey.com.nothingyet;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.widget.TextView;

import joecasey.com.nothingyet.base.BaseActivity;
import joecasey.com.nothingyet.fragments.HomeFragment;
import joecasey.com.nothingyet.fragments.MultichoiceFragment;
import joecasey.com.nothingyet.utils.Logger;


public class MultichoiceActivity extends BaseActivity {

    public static class IntentExtras {
        public static final String TYPE = "type";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String type = getIntent().getExtras().getString(IntentExtras.TYPE);
        Logger.i(this, "Type: " + type);
    }

    @Override
    protected void initContent() {
        getSupportFragmentManager().beginTransaction().add(R.id.content_container, MultichoiceFragment.newInstance(), "multichoiceFragment").commit();
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
            title.setText("Multichoice");
            title.setTextColor(Color.WHITE);
            getSupportActionBar().setCustomView(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_hamburger);
        }
    }
}
