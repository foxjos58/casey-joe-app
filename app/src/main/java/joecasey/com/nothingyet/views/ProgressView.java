package joecasey.com.nothingyet.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import joecasey.com.nothingyet.R;
import joecasey.com.nothingyet.utils.AndroidUtils;

/**
 * Created by Joe F on 2/25/2015.
 */
public class ProgressView extends RelativeLayout {

    private ImageView mIcon;
    private TextView mLabel;
    private float mProgress;
    private Paint mCenterPaint, mCompletedOutlinePaint, mIncompletedOutlinePaint, mWhitespacePaint;
    private ProgressAnimator mProgressAnimator;

    public ProgressView(Context context) {
        super(context);
        init();
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setWillNotDraw(false);
        LinearLayout container = new LinearLayout(getContext());
        container.setOrientation(LinearLayout.VERTICAL);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(CENTER_IN_PARENT);
        addView(container, layoutParams);

        mIcon = new ImageView(getContext());
        container.addView(mIcon);
        mLabel = new TextView(getContext());
        container.addView(mLabel);

        mCenterPaint = new Paint();
        mCenterPaint.setColor(getResources().getColor(R.color.secondary_grey));
        mCenterPaint.setShadowLayer(AndroidUtils.toDip(getContext(), 4.f), 0.f, AndroidUtils.toDip(getContext(), 2.f), 0xbfffffff & Color.BLACK);
        mCenterPaint.setAntiAlias(true);

        mCompletedOutlinePaint = new Paint();
        mCompletedOutlinePaint.setColor(getResources().getColor(R.color.primary_accent));
        mCompletedOutlinePaint.setAntiAlias(true);

        mIncompletedOutlinePaint = new Paint();
        mIncompletedOutlinePaint.setColor(0xa0ffffff & getResources().getColor(R.color.primary_accent));
        mIncompletedOutlinePaint.setAntiAlias(true);

        mWhitespacePaint = new Paint();
        mWhitespacePaint.setColor(Color.WHITE);
        mWhitespacePaint.setAntiAlias(true);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mProgressAnimator != null) {
            mProgressAnimator.removeCallbacks();
        }
    }

    public void setProgress(float progress) {
        mProgressAnimator = new ProgressAnimator(mProgress, progress, 1000);
        mProgress = progress;
        mLabel.setText(String.format("%d", (int)(progress * 100.f)));
        postInvalidate();
    }

    public void setIconResId(int resId) {
        mIcon.setImageResource(resId);
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (!(mProgressAnimator == null || mProgressAnimator.isRunning() || mProgressAnimator.isFinished())) {
            mProgressAnimator.start();
        }
        final int progressWidth = AndroidUtils.toDip(getContext(), 8.f);
        float radius = getWidth() / 2.f;
        canvas.drawCircle(radius, radius, radius, mIncompletedOutlinePaint);


        float completedAngle = (mProgressAnimator == null ? 0.f : mProgressAnimator.getCurrentValue()) * 360.f;
        canvas.drawArc(new RectF(0.f, 0.f, getWidth(), getHeight())
                , 270.f, completedAngle, true, mCompletedOutlinePaint);

        canvas.drawCircle(radius, radius, radius - progressWidth, mWhitespacePaint);

        canvas.drawCircle(radius, radius, radius - (progressWidth * 2.f), mCenterPaint);

        super.onDraw(canvas);
    }

    private class ProgressAnimator {
        boolean isRunning;
        boolean finished;
        int count;
        int stepSize;
        float angleStepSize;
        float currentValue;
        float endValue;
        private Runnable runnable;
        ProgressAnimator(float start, float end, int duration) {
            count = (1000 / 33);
            stepSize = duration / count;
            angleStepSize = (end - start) / count;
            currentValue = start;
            endValue = end;
        }

        public void removeCallbacks() {
            if (runnable != null) {
                ProgressView.this.removeCallbacks(runnable);
                runnable = null;
            }
        }

        public float getCurrentValue() {
            return currentValue;
        }

        public boolean isRunning() {
            return isRunning;
        }

        public boolean isFinished() {
            return finished;
        }

        public void start() {
            isRunning = true;
            ProgressView.this.postDelayed(runnable = (new Runnable() {
                @Override
                public void run() {
                    currentValue += angleStepSize;
                    count--;
                    if (count > 0) {
                        ProgressView.this.postDelayed(runnable, stepSize);
                    } else {
                        currentValue = endValue;
                        finished = true;
                        isRunning = false;
                    }
                    ProgressView.this.postInvalidate();
                }
            }),=-);
        }
    }
}
