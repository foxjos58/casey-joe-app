package joecasey.com.nothingyet.managers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Joe F on 3/18/2015.
 */
public class MultichoiceManager {

    public static class Status {
        public static final int DONE = 0;
        public static final int INCOMPLETE = 1;
        public static final int BOOKMARKED = 2;
    }

    public static int getMultichoiceStatus(Context context, String type, String name) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences(type, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(name, Status.INCOMPLETE);
    }

    public static void setMultichoiceStatus(Context context, String type, String name, int status) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences(type, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(name, status).commit();
    }
}
