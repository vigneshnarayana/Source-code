package com.zebra.rfid.demo.sdksample;




import android.util.Log;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.HashMap;

/**
 * Class to handle the details about access operations(No of Tabs, Class acting as UI for the tabs) etc..
 */
public class AccessOperationsAdapter {
    private static final int NO_OF_TABS = 3;
    private HashMap<Integer, Fragment> currentlyActiveFragments;
    public AccessOperationsAdapter(FragmentManager fm) {
        super();
    }


    public Fragment getItem(int index) {
        if (currentlyActiveFragments == null)
            currentlyActiveFragments = new HashMap<>();

        Class<WriteTag> fragment;

        switch (index) {
            case 0:
                Log.d(getClass().getSimpleName(), "1st Tab Selected");
                fragment = WriteTag.class;
                break;


            default:
                fragment = null;
                break;
        }

        //Store the reference
        currentlyActiveFragments.put(index, getFragment(index));
        return getFragment(index);
    }


    public Fragment getFragment(int key) {
        if (currentlyActiveFragments != null)
            return currentlyActiveFragments.get(key);
        else
            return null;
    }



}

