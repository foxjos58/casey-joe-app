package joecasey.com.nothingyet.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import joecasey.com.nothingyet.Constants;
import joecasey.com.nothingyet.MultichoiceActivity;
import joecasey.com.nothingyet.ProgressActivity;
import joecasey.com.nothingyet.R;
import joecasey.com.nothingyet.base.BaseFragment;
import joecasey.com.nothingyet.utils.AndroidUtils;
import joecasey.com.nothingyet.utils.Logger;
import joecasey.com.nothingyet.views.RoundedImageLayout;

/**
 * Created by Joe F on 2/17/2015.
 */
public class HomeFragment extends BaseFragment {

    private static final List<HomeSymbols> HOME_SYMBOLS = new ArrayList<>();
    static {
        HOME_SYMBOLS.add(new HomeSymbols("Katakana", R.drawable.abc_ab_share_pack_holo_dark));
        HOME_SYMBOLS.add(new HomeSymbols("Hiragana", R.drawable.abc_ab_share_pack_holo_dark));
        HOME_SYMBOLS.add(new HomeSymbols("Both", R.drawable.abc_ab_share_pack_holo_dark));
    }

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initView() {
        GridView gridView = (GridView)mRootView.findViewById(R.id.home_grid);
        HomeGridAdapter homeGridAdapter;
        gridView.setAdapter(homeGridAdapter = new HomeGridAdapter(getActivity(), R.layout.home_grid_item, HOME_SYMBOLS));
        homeGridAdapter.setOnItemSelectedListener(new HomeGridAdapter.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int position, HomeSymbols homeSymbols) {
                final Intent intent = new Intent(getActivity(), ProgressActivity.class);
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
                getActivity().startActivity(intent);
            }
        });
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
            ((RoundedImageLayout)convertView.findViewById(R.id.icon)).setIconResId(getItem(position).getIconId());
            ((TextView)convertView.findViewById(R.id.label)).setText(getItem(position).getName());

            int width = container.getMeasuredWidth();
            convertView.getLayoutParams().height = (width / 2) + AndroidUtils.toDip(getContext(), 32.f);
            convertView.forceLayout();
            convertView.findViewById(R.id.overlay).setOnClickListener(new View.OnClickListener() {
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
