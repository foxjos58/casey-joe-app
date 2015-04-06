package joecasey.com.nothingyet.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import joecasey.com.nothingyet.R;
import joecasey.com.nothingyet.utils.AndroidUtils;

/**
 * Created by Joe F on 3/16/2015.
 */
public class RoundedImageLayout extends RelativeLayout {
    private ImageView mIcon;
    private Paint mCenterPaint;
    private float DEFAULT_PADDING;

    public RoundedImageLayout(Context context) {
        super(context);
        init();
    }

    public RoundedImageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundedImageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View roundedImageLayout;
        addView(roundedImageLayout = LayoutInflater.from(getContext()).inflate(R.layout.rounded_image_layout, null, false),
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setWillNotDraw(false);
        mIcon = (ImageView)roundedImageLayout.findViewById(R.id.icon);

        mCenterPaint = new Paint();
        mCenterPaint.setColor(getResources().getColor(R.color.primary_color));
        mCenterPaint.setShadowLayer(AndroidUtils.toDip(getContext(), 4.f), 0.f, AndroidUtils.toDip(getContext(), 2.f), 0xbfffffff & Color.BLACK);
        mCenterPaint.setAntiAlias(true);

        DEFAULT_PADDING = getContext().getResources().getDimension(R.dimen.default_spacing) / 2.f;
    }

    public void setIconResId(int resId) {
        mIcon.setImageResource(resId);
    }

    @Override
    public void onDraw(Canvas canvas) {
        float c = getWidth() / 2.f;
        float radius = c - DEFAULT_PADDING;
        canvas.drawCircle(c, c, radius, mCenterPaint);
        super.onDraw(canvas);
    }
}
