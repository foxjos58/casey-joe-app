package joecasey.com.nothingyet.managers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Joe F on 3/18/2015.
 */
public class ProgressManager {

    public static float getProgress(Context context, String type, String section) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences(type, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(section, 0.f);
    }

    public static void setProgress(Context context, String type, String section, float value) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences(type, Context.MODE_PRIVATE);
        sharedPreferences.edit().putFloat(section, value).commit();
    }
}
