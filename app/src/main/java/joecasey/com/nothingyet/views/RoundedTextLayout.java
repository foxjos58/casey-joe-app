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
import android.widget.TextView;

import joecasey.com.nothingyet.R;
import joecasey.com.nothingyet.utils.AndroidUtils;

/**
 * Created by Joe F on 3/16/2015.
 */
public class RoundedTextLayout extends RelativeLayout {
    private TextView mText;
    private Paint mCenterPaint;
    private float DEFAULT_PADDING;

    public RoundedTextLayout(Context context) {
        super(context);
        init();
    }

    public RoundedTextLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundedTextLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View roundedTextLayout;
        addView(roundedTextLayout = LayoutInflater.from(getContext()).inflate(R.layout.rounded_text_layout, null, false),
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setWillNotDraw(false);
        mText = (TextView)roundedTextLayout.findViewById(R.id.label);

        mCenterPaint = new Paint();
        mCenterPaint.setColor(getResources().getColor(R.color.primary_color));
        mCenterPaint.setShadowLayer(AndroidUtils.toDip(getContext(), 4.f), 0.f, AndroidUtils.toDip(getContext(), 2.f), 0xbfffffff & Color.BLACK);
        mCenterPaint.setAntiAlias(true);

        DEFAULT_PADDING = getContext().getResources().getDimension(R.dimen.default_spacing) / 2.f;
    }

    public void setText(String text) {
        mText.setText(text);
    }

    @Override
    public void onDraw(Canvas canvas) {
        float c = getWidth() / 2.f;
        float radius = c - DEFAULT_PADDING;
        canvas.drawCircle(c, c, radius, mCenterPaint);
        super.onDraw(canvas);
    }
}
