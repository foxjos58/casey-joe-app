package joecasey.com.nothingyet.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import joecasey.com.nothingyet.Constants;
import joecasey.com.nothingyet.ProgressActivity;
import joecasey.com.nothingyet.R;
import joecasey.com.nothingyet.fragments.ProgressFragment;

/**
 * Created by Joe F on 2/17/2015.
 */
public abstract class BaseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_activity);
        init();
    }

    protected void init() {
        initActionBar();
        initContent();
        initDrawer();
    }

    protected abstract void initActionBar();

    protected abstract void initContent();

    protected void initDrawer() {
        AdapterView adapterView = (ListView)findViewById(R.id.drawer_list);
        adapterView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getDrawerItems()));
        adapterView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent = new Intent(BaseActivity.this, ProgressActivity.class);
                switch (position) {
                    case 0:
                    default:
                        intent.putExtra(ProgressActivity.IntentExtras.TYPE, Constants.OptionType.KATAKANA);
                        break;
                    case 1:
                        intent.putExtra(ProgressActivity.IntentExtras.TYPE, Constants.OptionType.HIRAGANA);
                        break;
                    case 2:
                        intent.putExtra(ProgressActivity.IntentExtras.TYPE, Constants.OptionType.BOTH);
                        break;
                }
                startActivity(intent);
            }
        });
    }

    protected String[] getDrawerItems() {
        return new String[]{"katakana", "hiragana", "both", "feedback"};
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
