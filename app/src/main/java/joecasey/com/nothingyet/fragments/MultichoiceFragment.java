package joecasey.com.nothingyet.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import joecasey.com.nothingyet.R;
import joecasey.com.nothingyet.utils.AndroidUtils;
import joecasey.com.nothingyet.utils.Logger;

/**
 * Created by Joe F on 2/17/2015.
 */
public class MultichoiceFragment extends Fragment {

    private View mRootView;
    private static final List<HomeSymbols> HOME_SYMBOLS = new ArrayList<>();
    static {
        HOME_SYMBOLS.add(new HomeSymbols("Katakana", R.drawable.abc_ab_share_pack_holo_dark));
        HOME_SYMBOLS.add(new HomeSymbols("Hiragana", R.drawable.abc_ab_share_pack_holo_dark));
        HOME_SYMBOLS.add(new HomeSymbols("Both", R.drawable.abc_ab_share_pack_holo_dark));
    }

    public static MultichoiceFragment newInstance() {
        MultichoiceFragment multichoiceFragment = new MultichoiceFragment();
        return multichoiceFragment;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(container.getContext()).inflate(R.layout.multichoice_fragment, container, false);
        initView();
        return mRootView;
    }

    private void initView() {
    }

    private static class HomeSymbols {
        private String name;
        private int iconId;

        HomeSymbols(String name, int iconId) {
            this.name = name;
            this.iconId = iconId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIconId() {
            return iconId;
        }

        public void setIconId(int iconId) {
            this.iconId = iconId;
        }
    }

    private static class HomeGridAdapter extends ArrayAdapter<HomeSymbols> {
        interface OnItemSelectedListener {
            public void onItemSelected(int position, HomeSymbols homeSymbols);
        }
        private List<HomeSymbols> symbols;
        private OnItemSelectedListener onItemSelectedListener;

        public HomeGridAdapter(Context context, int resource, List<HomeSymbols> objects) {
            super(context, resource, objects);
            this.symbols = objects;
        }

        public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
            this.onItemSelectedListener = onItemSelectedListener;
        }

        @Override
        public int getCount() {
            return symbols == null ? 0 : symbols.size();
        }

        @Override
        public HomeSymbols getItem(int index) {
            return symbols.get(index);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.home_grid_item, container, false);
            }
            ((ImageView)convertView.findViewById(R.id.icon)).setImageResource(getItem(position).getIconId());
            ((TextView)convertView.findViewById(R.id.label)).setText(getItem(position).getName());

            int width = container.getMeasuredWidth();
            convertView.getLayoutParams().height = (width / 2) + AndroidUtils.toDip(getContext(), 32.f);
            convertView.forceLayout();
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemSelectedListener != null) {
                        onItemSelectedListener.onItemSelected(position, getItem(position));
                    }
                }
            });
            return convertView;
        }
    }
 }
