package com.zebra.rfid.demo.sdksample;



import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.zebra.rfid.api3.TagData;





public class AccessOperationsFragment {

    private int accessOperationCount = -1;
    private boolean showAdvancedOptions = false;
    private AccessOperationsAdapter mAdapter;
    private ViewPager viewPager;

    public AccessOperationsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AccessOperationsFragment.
     */
    public static AccessOperationsFragment newInstance() {
        return new AccessOperationsFragment();
    }



//    public void handleTagResponse(TagData tagData) {
////        if (mAdapter != null && viewPager != null) {
////            Fragment fragment = mAdapter.getFragment(viewPager.getCurrentItem());
////            if (fragment != null && getClass(WriteTag.class) ) {
////                (getClass(WriteTag.class)).handleTagResponse(tagData);
////            }
////        }
//    }
//
//    private boolean getClass(Class<WriteTag> writeTagClass) {
//    }


    /**
     * interface to maintain last entered access tag id in access control fragments
     */
    public interface OnRefreshListener {
        /**
         * method to update accessControlTag value
         */
        void onUpdate();

        /**
         * method to refresh the fragment details with updated tag id
         */
        void onRefresh();
    }
}
