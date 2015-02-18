package joecasey.com.nothingyet.utils;

import android.util.Log;

public class Logger {

	private static final boolean DEBUGGABLE = true;

	public static void i(String tag, String string) {
		if (DEBUGGABLE) {
			Log.i(tag, string);
		}
	}

	public static void i(String tag, String string, Object... args) {
		i(tag, String.format(string, args));
	}

	public static void d(String tag, String string) {
		if (DEBUGGABLE) {
			Log.d(tag, string);
		}
	}

	public static void d(String tag, String string, Object... args) {
		d(tag, String.format(string, args));
	}

	public static void e(String tag, String string) {
		if (DEBUGGABLE) {
			Log.e(tag, string);
		}
	}

	public static void e(String tag, String string, Throwable t) {
		if (DEBUGGABLE) {
			Log.e(tag, string, t);
		}
	}

	public static void printMethodTrace(Object tag) {
		if (DEBUGGABLE) {
			final int SYSTEM_CALL_DEPTH = 4;
			final int INITAL_CALL_DEPTH = 4;
			final String initialCaller = getMethodName(INITAL_CALL_DEPTH);
			final StringBuilder builder = new StringBuilder();
			final int size = Thread.currentThread().getStackTrace().length;
			for (int i = INITAL_CALL_DEPTH + 1; i < size - SYSTEM_CALL_DEPTH; i++) {
				builder.append(initialCaller).append(" - ")
						.append(getMethodName(i)).append("\n");
			}
			Log.i(tag.getClass().getSimpleName(), builder.toString());
		}
	}

	public static void e(String tag, String string, Object... args) {
		e(tag, String.format(string, args));
	}

	public static void i(Object tag, String string) {
		i(tag.getClass().getSimpleName(), string);
	}

	public static void i(Object tag, String string, Object... args) {
		i(tag.getClass().getSimpleName(), String.format(string, args));
	}

	public static void d(Object tag, String string) {
		d(tag.getClass().getSimpleName(), string);
	}

	public static void d(Object tag, String string, Object... args) {
		d(tag.getClass().getSimpleName(), String.format(string, args));
	}

	public static void e(Object tag, String string) {
		e(tag.getClass().getSimpleName(), string);
	}

	public static void e(Object tag, String string, Object... args) {
		e(tag.getClass().getSimpleName(), String.format(string, args));
	}

	public static void d(Object tag) {
		d(tag.getClass().getSimpleName(), getMethodName(4));
	}

	public static void i(Object tag) {
		i(tag.getClass().getSimpleName(), getMethodName(4));
	}

	public static void e(Object tag) {
		e(tag.getClass().getSimpleName(), getMethodName(4));
	}

	public static String getMethodName(final int depth) {
		return Thread.currentThread().getStackTrace()[depth].getMethodName();
	}
}
