package joecasey.com.nothingyet.utils;

import android.content.Context;

/**
 * Created by Joe F on 2/17/2015.
 */
public class AndroidUtils {
    public static int toDip(Context context, float measurementInPixels) {
        return (int)(measurementInPixels * context.getResources().getDisplayMetrics().density);
    }
}
