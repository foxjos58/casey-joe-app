package joecasey.com.nothingyet.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import joecasey.com.nothingyet.Constants;
import joecasey.com.nothingyet.MultichoiceActivity;
import joecasey.com.nothingyet.R;
import joecasey.com.nothingyet.base.BaseFragment;
import joecasey.com.nothingyet.managers.ProgressManager;
import joecasey.com.nothingyet.utils.AndroidUtils;
import joecasey.com.nothingyet.views.ProgressView;

/**
 * Created by Joe F on 3/16/2015.
 */
public class ProgressFragment extends BaseFragment {

    private String mType;
    public static ProgressFragment newInstance(String type) {
        ProgressFragment progressFragment = new ProgressFragment();
        progressFragment.setType(type);
        return progressFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.progress_fragment;
    }

    @Override
    protected void initView() {
        populatePlaceholderProgress();
        final GridView gridView = (GridView)mRootView.findViewById(R.id.grid);
        final ProgressFragmentAdapter progressFragmentAdapter;
        gridView.setAdapter(progressFragmentAdapter = new ProgressFragmentAdapter(getActivity(), 0, getProgresses()));
        progressFragmentAdapter.setOnItemSelectedListener(new ProgressFragmentAdapter.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int position, Progress progress) {
                Intent intent = null;
                switch (position) {
                    case 0:
                    default:
                        break;
                    case 1:
                        intent = new Intent(getActivity(), MultichoiceActivity.class);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                }
                intent.putExtra(MultichoiceActivity.IntentExtras.TYPE, mType);
                getActivity().startActivity(intent);
            }
        });
    }

    private void setType(String type) {
        mType = type;
    }

    private List<Progress> getProgresses() {
        List<Progress> progresses = new ArrayList<>();
        progresses.add(new Progress(getProgress(Constants.Modes.WRITING), Constants.Modes.WRITING));
        progresses.add(new Progress(getProgress(Constants.Modes.MULTICHOICE), Constants.Modes.MULTICHOICE));
        progresses.add(new Progress(getProgress(Constants.Modes.LISTEN), Constants.Modes.LISTEN));
        progresses.add(new Progress(getProgress(Constants.Modes.FLASH_CARDS), Constants.Modes.FLASH_CARDS));
        progresses.add(new Progress(getProgress(Constants.Modes.MEMORY), Constants.Modes.MEMORY));
        progresses.add(new Progress(getProgress(Constants.Modes.MIX_IT_UP), Constants.Modes.MIX_IT_UP));
        return progresses;
    }

    private float getProgress(String mode) {
        return ProgressManager.getProgress(getActivity(), mType, mode);
    }

    private void populatePlaceholderProgress() {
        ProgressManager.setProgress(getActivity(), mType, Constants.Modes.WRITING, .75f);
        ProgressManager.setProgress(getActivity(), mType, Constants.Modes.MULTICHOICE, .75f);
        ProgressManager.setProgress(getActivity(), mType, Constants.Modes.LISTEN, .6f);
        ProgressManager.setProgress(getActivity(), mType, Constants.Modes.MIX_IT_UP, .1f);
        ProgressManager.setProgress(getActivity(), mType, Constants.Modes.MEMORY, .0f);
        ProgressManager.setProgress(getActivity(), mType, Constants.Modes.FLASH_CARDS, 1.f);
    }

    public static class Progress implements Serializable {
        private float progress;
        private String label;

        Progress(float progress, String label) {
            this.progress = progress;
            this.label = label;
        }

        public float getProgress() {
            return progress;
        }

        public void setProgress(float progress) {
            this.progress = progress;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    private static class ProgressFragmentAdapter extends ArrayAdapter<Progress> {
        interface OnItemSelectedListener {
            public void onItemSelected(int position, Progress progress);
        }

        private List<Progress> progresses;
        private OnItemSelectedListener onItemSelectedListener;
        public ProgressFragmentAdapter(Context context, int resource, List<Progress> progresses) {
            super(context, resource, progresses);
            this.progresses = progresses;
        }

        public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
            this.onItemSelectedListener = onItemSelectedListener;
        }


        @Override
        public int getCount() {
            return progresses == null ? 0 : progresses.size();
        }

        @Override
        public Progress getItem(int index) {
            return progresses == null ? null : progresses.get(index);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.progress_fragment_item, container, false);
            }
            final Progress progress = getItem(position);
            ((ProgressView)convertView.findViewById(R.id.progress_view)).setProgress(progress.getProgress());
            ((TextView)convertView.findViewById(R.id.label)).setText(progress.getLabel());
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
