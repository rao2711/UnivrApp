package com.cellasoft.univrapp;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.content.SharedPreferences;

import com.cellasoft.univrapp.model.University;
import com.cellasoft.univrapp.model.University.Universites;

public class Settings {
	public static final String PREFS_NAME = "com.cellasoft.univrapp_preferences";
	public static final String AUTO_UPDATE_KEY = "auto_update";
	public static final String NOTIFICATIONS_UNIVRAPP = "notifications_univrapp";
	public static final String REG_ID_UNIVRAPP = "regid_univrapp";
	public static final String UPDATE_INTERVAL_KEY = "update_interval";
	public static final String WIFI_ONLY_KEY = "wifi_only";
	public static final String KEEP_MAX_ITEMS_KEY = "keep_max_items";
	public static final String MAX_ITEMS_FOR_CHANNEL_KEY = "max_items_for_channel";
	public static final String AD_CLICK_TIME = "ad_click_time";
	private static Context context;

	static {
		context = Application.getInstance();
	}

	public static boolean getFirstTime() {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		return prefs.getBoolean(context.getString(R.string.first_time_key),
				true);
	}

	public static void setFirstTime(boolean value) {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putBoolean(context.getString(R.string.first_time_key), value);
		editor.commit();
	}

	public static void saveFirstTime() {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putBoolean(context.getString(R.string.first_time_key), false);
		editor.putString(context.getString(R.string.update_interval_key), "15");
		editor.putBoolean(context.getString(R.string.auto_update_key), true);
		editor.putString(context.getString(R.string.language_key), "it");

		editor.putString("font", "sans");
		editor.putString("font_size", "1.0em");
		editor.putBoolean("notification_sound", false);
		editor.putBoolean("notification_vibrate", false);
		editor.putBoolean("notification_light", true);

		editor.putString(MAX_ITEMS_FOR_CHANNEL_KEY, "100");
		editor.putString(KEEP_MAX_ITEMS_KEY, "20");
		editor.putBoolean(WIFI_ONLY_KEY, false);
		editor.putLong(AD_CLICK_TIME, -25);

		editor.putBoolean(NOTIFICATIONS_UNIVRAPP, true);
		editor.putString(REG_ID_UNIVRAPP, "Not Registered");

		editor.commit();
	}

	public static void setAdClickTime(long value) {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putLong(AD_CLICK_TIME, value);
		editor.commit();
	}

	public static boolean hasPassed24Hours() {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		Date clickTime = new Date(prefs.getLong(AD_CLICK_TIME, 0));
		Calendar cal = Calendar.getInstance(Locale.ITALY);
		cal.setTime(new Date());
		Calendar cal2 = Calendar.getInstance(Locale.ITALY);
		cal2.setTime(clickTime);
		return Math
				.abs(((cal2.getTime().getTime() - cal.getTime().getTime()) / 3600000)) >= 24;
	}

	public static int getUpdateInterval() {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		return Integer.parseInt(prefs.getString(
				context.getString(R.string.update_interval_key), "5"));
	}

	public static boolean getAutoUpdate() {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		return prefs.getBoolean(context.getString(R.string.auto_update_key),
				true);
	}

	public static void setUniversity(String university) {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putInt("UNIVERSITY", Universites.DIP_DEST.get(university));
		editor.commit();
	}

	public static boolean getShowUpdatedChannels() {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		return prefs.getBoolean(
				context.getString(R.string.show_updated_channels_key), false);
	}

	public static String getLocale() {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		return prefs.getString(context.getString(R.string.language_key), "en");
	}

	public static University getUniversity() {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		int dest = prefs.getInt("UNIVERSITY", Universites.DEST_DIP_INFORMATICA);
		return University.getUniversityByDest(dest);
	}

	public static int getKeepMaxItems() {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		return Integer.parseInt(prefs.getString(KEEP_MAX_ITEMS_KEY, "20"));
	}

	public static int getKeepMaxImages() {
		return 2000;
	}

	public static boolean getWifiOnly() {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		return prefs.getBoolean(WIFI_ONLY_KEY, false);
	}

	public static int getMaxItemsForChannel() {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		return Integer.parseInt(prefs.getString(MAX_ITEMS_FOR_CHANNEL_KEY,
				"100"));
	}

	public static String getFont() {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		return prefs.getString("font", "sans");
	}

	public static String getFontSize() {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		return prefs.getString("font_size", "1.0em");
	}

	public static boolean getNightReadingMode() {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		return prefs.getBoolean("night_mode", false);
	}

	public static void saveNightReadingMode(boolean nightMode) {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putBoolean("night_mode", nightMode);
		editor.commit();
	}

	public static boolean getNotificationSound() {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		return prefs.getBoolean("notification_sound", false);
	}

	public static boolean getNotificationVibrate() {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		return prefs.getBoolean("notification_vibrate", false);
	}

	public static boolean getNotificationLight() {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		return prefs.getBoolean("notification_light", false);
	}

	public static boolean isEnabledSandingBugRepport() {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		return prefs.getBoolean("acra.enable", true);
	}

	public static boolean isEnabledNotificationUnivrApp() {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		return prefs.getBoolean(NOTIFICATIONS_UNIVRAPP, true);
	}

	public static void setNotificationUnivrApp(boolean enable) {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putBoolean(NOTIFICATIONS_UNIVRAPP, enable);
		editor.commit();
	}

	public static void setRegistrationId(String regid) {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(REG_ID_UNIVRAPP, regid);
		editor.commit();
	}

	public static String getRegistrationId() {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		return prefs.getString(REG_ID_UNIVRAPP, "Not Registered");
	}

}