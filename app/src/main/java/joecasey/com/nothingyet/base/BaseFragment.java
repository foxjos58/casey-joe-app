package joecasey.com.nothingyet.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import joecasey.com.nothingyet.R;

/**
 * Created by Joe F on 3/16/2015.
 */
public abstract class BaseFragment extends Fragment {

    protected View mRootView;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(container.getContext()).inflate(getLayoutId(), container, false);
        initView();
        return mRootView;
    }

    protected abstract void initView();

    protected abstract int getLayoutId();
}
