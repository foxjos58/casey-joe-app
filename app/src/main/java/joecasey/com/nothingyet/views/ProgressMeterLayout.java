package joecasey.com.nothingyet.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import joecasey.com.nothingyet.R;
import joecasey.com.nothingyet.managers.MultichoiceManager;
import joecasey.com.nothingyet.models.Multichoice;

/**
 * Created by Joe F on 3/18/2015.
 */
public class ProgressMeterLayout extends LinearLayout{
    public interface OnItemSelectedListener {
        public void onItemSelected(int position, Multichoice multichoice);
    }

    private OnItemSelectedListener mOnItemSelectedListener;
    public ProgressMeterLayout(Context context) {
        super(context);
        init();
    }

    public ProgressMeterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressMeterLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ProgressMeterLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setOrientation(LinearLayout.HORIZONTAL);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        mOnItemSelectedListener = onItemSelectedListener;
    }

    public void setMultichoices(String type, List<Multichoice> multichoices) {
        removeAllViews();
        for (int i = 0; i < multichoices.size(); i++) {
            final int position = i;
            final Multichoice currentMultichoice = multichoices.get(i);
            int status = MultichoiceManager.getMultichoiceStatus(getContext(), type, currentMultichoice.getAnswer());
            int color;
            switch (status) {
                case MultichoiceManager.Status.DONE:
                default:
                    color = getResources().getColor(R.color.status_done);
                    break;
                case MultichoiceManager.Status.INCOMPLETE:
                    color = getResources().getColor(R.color.status_incomplete);
                    break;
                case MultichoiceManager.Status.BOOKMARKED:
                    color = getResources().getColor(R.color.status_bookmarked);
                    break;
            }
            LayoutParams layoutParams = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.f);
            View view = new View(getContext());
            view.setBackgroundColor(color);
            addView(view, layoutParams);
            addView(new View(getContext()), layoutParams);

            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemSelectedListener != null) {
                        mOnItemSelectedListener.onItemSelected(position ,currentMultichoice);
                    }
                }
            });
        }
    }
}
