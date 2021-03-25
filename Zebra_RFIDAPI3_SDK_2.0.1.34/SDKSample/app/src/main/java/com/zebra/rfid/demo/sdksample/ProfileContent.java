package com.zebra.rfid.demo.sdksample;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ProfileContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<ProfilesItem> ITEMS = new ArrayList<ProfilesItem>();
    public static int MAX_NO_PROFILES = 6;
    private Context m_context;
    private static int[] powerLevels;
    static LinkProfileUtil linkProfileUtil;
    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, ProfilesItem> ITEM_MAP = new HashMap<String, ProfilesItem>();

    public ProfileContent(Context context) {
        m_context = context;
    }

    public void LoadDefaultProfiles() {
        if (ITEMS.size() == 0) {
            // Add profiles
            int index = 0;
            addItem(new ProfilesItem(String.valueOf(index), "Fastest Read", "Read as many tags as fast as possible", 300, 1, 0, false, false, false));
            addItem(new ProfilesItem(String.valueOf(++index), "Cycle Count", "Read as many unique tags possible", 300, 0, 2, false, false, false));
            addItem(new ProfilesItem(String.valueOf(++index), "Dense Readers", "Use when multiple readers in close proximity", 300, 17, 1, false, false, false));
            addItem(new ProfilesItem(String.valueOf(++index), "Optimal Battery", "Gives best battery life", 240, 0, 1, false, true, false));
            addItem(new ProfilesItem(String.valueOf(++index), "Balanced Performance", "Maintains balance between performance and battery life", 270, 0, 1, false, true, false));
            addItem(new ProfilesItem(String.valueOf(++index), "User Defined", "Custom profile \nUsed for custom requirement", 270, 0, 1, false, true, true));
            addItem(new ProfilesItem(String.valueOf(++index), "Reader Defined", "Maintains Reader configurations\nApplication does not configure the reader after connection", 270, 0, 1, false, true, true));

            MAX_NO_PROFILES = index;

            // check if profiles are stored
            SharedPreferences settingsprofile = m_context.getSharedPreferences(Constants.APP_SETTINGS_PROFILE, MODE_PRIVATE);
            boolean stored = settingsprofile.getBoolean("STOREDV1", false);
            if (!stored) {
                // first time make first profile default
                ITEMS.get(0).isOn = true;
                for (ProfilesItem item : ITEMS) {
                    item.StoreProfile();
                }
                SharedPreferences.Editor editor = settingsprofile.edit();
                editor.putBoolean("STOREDV1", true);
                editor.commit();
            }
        }
        for (ProfilesItem item : ITEMS) {
            item.GetStoredProfile();
        }


    }

    public static void UpdateProfilesForRegulatory() {
        // TODO: make generic and remove hard coded references
        ProfileContent.ProfilesItem FastReadProfile = ProfileContent.ITEMS.get(0);
        ProfileContent.ProfilesItem DenseModeProfile = ProfileContent.ITEMS.get(2);
        ProfileContent.ProfilesItem CycleCountProfile = ProfileContent.ITEMS.get(1);
        ProfileContent.ProfilesItem OptBatteryProfile = ProfileContent.ITEMS.get(3);
        ProfileContent.ProfilesItem BalancePerformProfile = ProfileContent.ITEMS.get(4);
        ProfileContent.ProfilesItem UserProfile = ProfileContent.ITEMS.get(5);



        //
        linkProfileUtil = LinkProfileUtil.getInstance();
        FastReadProfile.powerLevel = linkProfileUtil.getMaxPowerIndex();
        CycleCountProfile.powerLevel = linkProfileUtil.getMaxPowerIndex();
        DenseModeProfile.powerLevel = linkProfileUtil.getMaxPowerIndex();
        OptBatteryProfile.powerLevel = 240;
        BalancePerformProfile.powerLevel = 270;
        if (linkProfileUtil.getMaxPowerIndex() < 240) {
            OptBatteryProfile.powerLevel = linkProfileUtil.getMaxPowerIndex();
        }
        if (linkProfileUtil.getMaxPowerIndex() < 270) {
            BalancePerformProfile.powerLevel = linkProfileUtil.getMaxPowerIndex();
        }
        //
        FastReadProfile.StoreProfile();
        CycleCountProfile.StoreProfile();
        DenseModeProfile.StoreProfile();
        OptBatteryProfile.StoreProfile();
        BalancePerformProfile.StoreProfile();
        // incorrect user settings
        if (UserProfile.powerLevel > linkProfileUtil.getMaxPowerIndex()) {
            UserProfile.powerLevel = linkProfileUtil.getMaxPowerIndex();
            UserProfile.StoreProfile();
        }
        for (ProfilesItem item : ITEMS) {
            item.GetStoredProfile();
        }

    }



    private static void addItem(ProfilesItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public class ProfilesItem {
        public final String id;
        public final String content;
        public final String details;
        public int powerLevel = 0;
        public int LinkProfileIndex = 0;
        public int SessionIndex = 0;
        public boolean isOn = false;
        public boolean DPO_On = false;
        public boolean bUIEnabled;

        public ProfilesItem(String id, String content, String details, int power, int linkprofile, int session, boolean on, boolean DPO, boolean enabled) {
            this.id = id;
            this.content = content;
            this.details = details;
            powerLevel = power;
            LinkProfileIndex = linkprofile;
            SessionIndex = session;
            isOn = on;
            DPO_On = DPO;
            bUIEnabled = enabled;
        }

        @Override
        public String toString() {
            return content;
        }

        public void GetStoredProfile() {
            SharedPreferences settingsprofile = m_context.getSharedPreferences(Constants.APP_SETTINGS_PROFILE + this.id, 0);
            powerLevel = settingsprofile.getInt(Constants.PROFILE_POWER, 300);
            LinkProfileIndex = settingsprofile.getInt(Constants.PROFILE_LINK_PROFILE, 1);
            SessionIndex = settingsprofile.getInt(Constants.PROFILE_SESSION, 0);
            DPO_On = settingsprofile.getBoolean(Constants.PROFILE_DPO, false);
            isOn = settingsprofile.getBoolean(Constants.PROFILE_IS_ON, false);
            bUIEnabled = settingsprofile.getBoolean(Constants.PROFILE_UI_ENABLED, false);
        }

        public void StoreProfile() {
            SharedPreferences settings = m_context.getSharedPreferences(Constants.APP_SETTINGS_PROFILE + this.id, MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt(Constants.PROFILE_POWER, powerLevel);
            editor.putInt(Constants.PROFILE_LINK_PROFILE, LinkProfileIndex);
            editor.putInt(Constants.PROFILE_SESSION, SessionIndex);
            editor.putBoolean(Constants.PROFILE_DPO, DPO_On);
            editor.putBoolean(Constants.PROFILE_IS_ON, isOn);
            editor.putBoolean(Constants.PROFILE_UI_ENABLED, bUIEnabled);
            editor.commit();
            // if switched on make this as active profile
        }

    }
}
