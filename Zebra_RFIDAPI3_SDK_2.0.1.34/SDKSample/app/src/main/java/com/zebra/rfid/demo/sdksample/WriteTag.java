package com.zebra.rfid.demo.sdksample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentHostCallback;
import androidx.viewpager.widget.ViewPager;


import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zebra.rfid.api3.ACCESS_OPERATION_CODE;
import com.zebra.rfid.api3.ACCESS_OPERATION_STATUS;
import com.zebra.rfid.api3.BEEPER_VOLUME;

import com.zebra.rfid.api3.InvalidUsageException;
import com.zebra.rfid.api3.OperationFailureException;
import com.zebra.rfid.api3.RFIDReader;
import com.zebra.rfid.api3.ReaderDevice;
import com.zebra.rfid.api3.Readers;
import com.zebra.rfid.api3.RfidEventsListener;
import com.zebra.rfid.api3.RfidReadEvents;
import com.zebra.rfid.api3.RfidStatusEvents;
import com.zebra.rfid.api3.TagData;
import com.zebra.rfid.demo.sdksample.model.hextoascii;

import java.util.Timer;
import java.util.TimerTask;


import static com.zebra.rfid.api3.Constants.TYPE_DEBUG;
import static com.zebra.rfid.demo.sdksample.AdvancedOptionsContent.DPO_ITEM_INDEX;
import static com.zebra.rfid.demo.sdksample.Application.TAG_LIST_MATCH_MODE;
import static com.zebra.rfid.demo.sdksample.Application.isActivityVisible;
import static com.zebra.rfid.demo.sdksample.Application.reset;
import static com.zebra.rfid.demo.sdksample.RFIDController.isAccessCriteriaRead;
import static com.zebra.rfid.demo.sdksample.RFIDController.mConnectedReader;
import static com.zebra.rfid.demo.sdksample.RFIDController.mIsInventoryRunning;
import static com.zebra.rfid.demo.sdksample.RFIDController.readers;

public class WriteTag extends AppCompatActivity implements AccessOperationsFragment.OnRefreshListener{
    private static final String TAG ="data" ;

    public static Timer tLED;
    protected int accessTagCount;
    public Timer tbeep;
    TextView textRWData;
    public static ReaderDevice mConnectedDevice;
    private static final String MSG_READ = "Reading Tags";
    private static Object mutex = new Object();
    View mView;
    private static volatile RFIDController instance;
    boolean mAdded;
    FloatingActionButton inventoryBT = null;
    protected CustomProgressDialog progressDialog;
    private ViewPager viewPager;
    private AccessOperationsAdapter mAdapter;
    boolean mHidden;
    protected static final String TAG_CONTENT_FRAGMENT = "ContentFragment";

    FragmentHostCallback mHost;
    private EditText offsetEditText;
    private EditText lengthEditText;
    private AutoCompleteTextView tagIDField;
    public static short TagProximityPercent = -1;
    private ArrayAdapter<String> adapter;
    private boolean beepON = false;
    private boolean LEDON = false;
    private int LED_STOP_TIME = 500;
    private NotificationManager notificationManager;
    private long BEEP_STOP_TIME = 20;
    private CheckBox accessEnableAdvanceOptions;
    private boolean showAdvancedOptions;
    public static boolean asciiMode = false;

    private Object response_tagData;
    private static final String MSG_WRITE = "Writing Data";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_tag);

        initializeSpinner();
        offsetEditText = findViewById(R.id.accessRWOffsetValue);
        lengthEditText = findViewById(R.id.accessRWLengthValue);
        tagIDField = ((AutoCompleteTextView) findViewById(R.id.accessRWTagID));
        textRWData = (TextView) findViewById(R.id.accessRWData);
        offsetEditText.setHorizontallyScrolling(false);
        lengthEditText.setHorizontallyScrolling(false);

        //handle Seek Operations
        handleSeekOperations();
        RFIDController.getInstance().updateTagIDs();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, Application.tagIDs);
        tagIDField.setAdapter(adapter);
        if (RFIDController.asciiMode == true) {
            tagIDField.setFilters(new InputFilter[]{filter});
            textRWData.setFilters(new InputFilter[]{filter});
        } else {
            tagIDField.setFilters(new InputFilter[]{filter, new InputFilter.AllCaps()});
            textRWData.setFilters(new InputFilter[]{filter, new InputFilter.AllCaps()});

        }
        if (RFIDController.accessControlTag != null) {
            if (RFIDController.asciiMode == true)
                tagIDField.setText(hextoascii.convert(RFIDController.accessControlTag));
            else
                tagIDField.setText((RFIDController.accessControlTag));
            offsetEditText.setText("2");
        } else {
            offsetEditText.setText("0");
        }

        //
        SharedPreferences settings = this.getSharedPreferences(Constants.APP_SETTINGS_STATUS, 0);
        showAdvancedOptions = settings.getBoolean(Constants.ACCESS_ADV_OPTIONS, false);
        UpdateViews();
    }

    private void UpdateViews() {
        LinearLayout advancedOptions = (LinearLayout) findViewById(R.id.accessRWAdvanceOption);
        if (advancedOptions != null) {
            if (showAdvancedOptions) {
                advancedOptions.setVisibility(View.VISIBLE);
                //getActivity().findViewById(R.id.seperaterData).setVisibility(View.GONE);
            } else {
                advancedOptions.setVisibility(View.INVISIBLE);
                //getActivity().findViewById(R.id.seperaterData).setVisibility(View.VISIBLE);
            }
        }
    }
    public static InputFilter filter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            for (int i = start; i < end; i++) {
                if (!isAllowed(source.charAt(i)))
                    return "";
            }
            return null;
        }

        String allowed = "0123456789ABCDEFabcdef";

        private boolean isAllowed(char c) {
            if (asciiMode == false) {
                for (char ch : allowed.toCharArray()) {
                    if (ch == c)
                        return true;
                }
                return false;
            }
            return true;
        }
    };

    /**
     * Method to initialize the seekbars
     */
    private void handleSeekOperations() {
        offsetEditText.setFilters(new InputFilter[]{new InputFilterMax(Long.valueOf(Constants.MAX_OFFSET))});
        lengthEditText.setFilters(new InputFilter[]{new InputFilterMax(Long.valueOf(Constants.MAX_LEGTH))});
    }

    private void initializeSpinner() {
        Spinner memoryBankSpinner = (Spinner) findViewById(R.id.accessRWMemoryBank);
        if (memoryBankSpinner != null) {
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> memoryBankAdapter = ArrayAdapter.createFromResource(this, R.array.acess_read_write_memory_bank_array, R.layout.custom_spinner_layout);
            // Specify the layout to use when the list of choices appears
            memoryBankAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            memoryBankSpinner.setAdapter(memoryBankAdapter);
            //
            memoryBankSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
                            offsetEditText.setText("2"); // EPC
                            lengthEditText.setText("0");
                            break;
                        case 1:
                        case 2:
                            offsetEditText.setText("0"); // TID USER
                            lengthEditText.setText("0");
                            break;
                        case 4:
                            offsetEditText.setText("0"); // kill password
                            lengthEditText.setText("2");
                            break;
                        case 3: // access password
                            offsetEditText.setText("2");
                            lengthEditText.setText("2");
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }


    public void handleTagResponse( TagData response_tagData) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (response_tagData != null) {
                    ACCESS_OPERATION_CODE readAccessOperation = response_tagData.getOpCode();
                    if (readAccessOperation != null) {
                        if (response_tagData.getOpStatus() != null && !response_tagData.getOpStatus().equals(ACCESS_OPERATION_STATUS.ACCESS_SUCCESS)) {
                            String strErr = response_tagData.getOpStatus().toString().replaceAll("_", " ");
                            Toast.makeText(WriteTag.this, strErr.toLowerCase(), Toast.LENGTH_SHORT).show();
                        } else {
                            if (response_tagData.getOpCode() == ACCESS_OPERATION_CODE.ACCESS_OPERATION_READ) {
                                TextView text = (TextView) findViewById(R.id.accessRWData);
                                if (text != null) {
                                    text.setText(RFIDController.asciiMode == true ? hextoascii.convert(response_tagData.getMemoryBankData()) : response_tagData.getMemoryBankData());
                                }
                                Toast.makeText(WriteTag.this, R.string.msg_read_succeed, Toast.LENGTH_SHORT).show();
                                startbeepingTimer();
                            } else {
                            }
                        }
                    } else {
                        Toast.makeText(WriteTag.this, R.string.err_access_op_failed, Toast.LENGTH_SHORT).show();
                        Constants.logAsMessage(Constants.TYPE_DEBUG, "ACCESS READ", "memoryBankData is null");
                    }
                } else {
                    Toast.makeText(WriteTag.this, R.string.err_access_op_failed, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    @Override
//    public void onUpdate() {
//        if (isVisible() && tagIDField != null) {
//            RFIDController.accessControlTag = tagIDField.getText().toString();
//        }
//    }

    @Override
    public void onUpdate() {
        if (isVisible() && tagIDField != null) {
            RFIDController.accessControlTag = tagIDField.getText().toString();
        }
    }
//
//    private boolean isVisible() {
//                return this.isAdded() && !this.isHidden() && this.mView != null && this.mView.getWindowToken() != null && this.mView.getVisibility() ==0;
//
//    }

    @Override
    public void onRefresh() {
        if (RFIDController.accessControlTag != null && tagIDField != null) {
            tagIDField.setText(RFIDController.accessControlTag);
        }
    }

    public void startbeepingTimer() {
        if (RFIDController.beeperVolume != BEEPER_VOLUME.QUIET_BEEP) {
            if (!beepON) {
                beepON = true;
                beep();
                if (tbeep == null) {
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            stopbeepingTimer();
                            beepON = false;
                        }
                    };
                    tbeep = new Timer();
                    tbeep.schedule(task, BEEP_STOP_TIME);
                }
            }
        }
    }

    /**
     * method to stop timer
     */
    public void stopbeepingTimer() {
        if (tbeep != null) {
            if (RFIDController.toneGenerator != null)
                RFIDController.toneGenerator.stopTone();
            tbeep.cancel();
            tbeep.purge();
        }
        tbeep = null;
    }

    public void beep() {
        if (RFIDController.toneGenerator != null) {
            int toneType = ToneGenerator.TONE_PROP_BEEP;
            RFIDController.toneGenerator.startTone(toneType);
        }
    }

    public void btn_back_write(View view) {
        Intent intent=new Intent(this,LandingPage.class);
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    public final boolean isVisible() {
        return this.isAdded() && !this.isHidden() && this.mView != null && this.mView.getWindowToken() != null ;
    }
    public final boolean isAdded() {
        return this.mHost != null && this.mAdded;
    }
    public final boolean isHidden() {
        return this.mHidden;
    }


    public void accessOperationsReadClicked(View view) {
//        handleTagResponse((com.zebra.rfid.api3.TagData) response_tagData);

        //////////////

//        AutoCompleteTextView tagIDField = findViewById(R.id.accessRWTagID);
//        final String tagId = (asciiMode == true ? asciitohex.convert(tagIDField.getText().toString()) : tagIDField.getText().toString());
//        String offsetText = ((EditText) findViewById(R.id.accessRWOffsetValue)).getText().toString();
//        String lengthText = ((EditText) findViewById(R.id.accessRWLengthValue)).getText().toString();
//        String accessRWpassword = ((EditText) findViewById(R.id.accessRWPassword)).getText().toString();
//        String bankItem = ((Spinner) findViewById(R.id.accessRWMemoryBank)).getSelectedItem().toString();
//        getInstance().accessOperationsRead(tagId, offsetText, lengthText, accessRWpassword, bankItem,
//                new RfidListeners() {
//                    @Override
//                    public void onSuccess(Object object) {
//                        progressDialog.dismiss();
//                        if (isAccessCriteriaRead && !mIsInventoryRunning) {
//                                  handleTagResponse((TagData) object);
//                        }
//
//
//                    }
//
//                    @Override
//                    public void onFailure(Exception exception) {
//                        progressDialog.dismiss();
//                        if (exception instanceof InvalidUsageException) {
//                            sendNotification(Constants.ACTION_READER_STATUS_OBTAINED, ((InvalidUsageException) exception).getInfo());
//                        } else if (exception instanceof OperationFailureException) {
//                            sendNotification(Constants.ACTION_READER_STATUS_OBTAINED, ((OperationFailureException) exception).getVendorMessage());
//                        } else {
//                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(String message) {
//                        progressDialog.dismiss();
//                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
//                    }
//                });



        //////////////////////////////////
        AutoCompleteTextView tagIDField = findViewById(R.id.accessRWTagID);
        final String tagId = (asciiMode == true ? asciitohex.convert(tagIDField.getText().toString()) : tagIDField.getText().toString());
        String offsetText = ((EditText) findViewById(R.id.accessRWOffsetValue)).getText().toString();
        String lengthText = ((EditText) findViewById(R.id.accessRWLengthValue)).getText().toString();
        final String accessRWData = ((EditText) findViewById(R.id.accessRWData)).getText().toString();
        String accessRWpassword = ((EditText) findViewById(R.id.accessRWPassword)).getText().toString();
        String bankItem = ((Spinner) findViewById(R.id.accessRWMemoryBank)).getSelectedItem().toString();
        progressDialog = new CustomProgressDialog(this, MSG_WRITE);
        progressDialog.show();
        timerDelayRemoveDialog(Constants.RESPONSE_TIMEOUT, progressDialog, "Write");
        String tagValue;
        if (asciiMode == true)
            tagValue = asciitohex.convert(tagId);
        else tagValue = tagId;
        if (mConnectedReader == null || !mConnectedReader.isConnected())
            Toast.makeText(getApplicationContext(), "No Active Connection with Reader", Toast.LENGTH_SHORT).show();
        else if (!mConnectedReader.isCapabilitiesReceived())
            Toast.makeText(getApplicationContext(), "Reader capabilities not updated", Toast.LENGTH_SHORT).show();
        else if (tagValue.isEmpty())
            Toast.makeText(getApplicationContext(), "Please fill Tag Id", Toast.LENGTH_SHORT).show();
        else if (offsetText.isEmpty())
            Toast.makeText(getApplicationContext(), "Please fill offset", Toast.LENGTH_SHORT).show();
        else if (lengthText.isEmpty())
            Toast.makeText(getApplicationContext(), "Please fill length", Toast.LENGTH_SHORT).show();
        else
            getInstance().accessOperationsWrite(tagValue, offsetText, lengthText, accessRWData, accessRWpassword, bankItem,
                    new RfidListeners() {
                        @Override
                        public void onSuccess(Object object) {
                            progressDialog.dismiss();
                            startbeepingTimer();
                            Toast.makeText(getApplicationContext(), getString(R.string.msg_write_succeed), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Exception exception) {
                            progressDialog.dismiss();
                            if (exception instanceof InvalidUsageException) {
                                sendNotification(Constants.ACTION_READER_STATUS_OBTAINED, ((InvalidUsageException) exception).getInfo());
                            } else if (exception instanceof OperationFailureException) {
                                sendNotification(Constants.ACTION_READER_STATUS_OBTAINED, ((OperationFailureException) exception).getVendorMessage());
                            }

                        }

                        @Override
                        public void onFailure(String message) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        }
                    });

        ////////////

    }

//
public void timerDelayRemoveDialog(long time, final Dialog d, final String command) {
    new Handler().postDelayed(new Runnable() {
        public void run() {
            if (d != null && d.isShowing()) {
                d.dismiss();
                //TODO: cross check on selective flag clearing
                if (isAccessCriteriaRead) {
                    if (accessTagCount == 0)
                        sendNotification(Constants.ACTION_READER_STATUS_OBTAINED, getString(R.string.err_access_op_failed));
                    isAccessCriteriaRead = false;
                } else {
                    sendNotification(Constants.ACTION_READER_STATUS_OBTAINED, command + " timeout");
                    if (isActivityVisible())
                        callBackPressed();
                }
                isAccessCriteriaRead = false;
                accessTagCount = 0;
            }
        }
    }, time);
}
    public void callBackPressed() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                WriteTag.super.onBackPressed();
            }
        });
    }
    public void sendNotification(String action, String data) {
    if (isActivityVisible()) {
        if (action.equalsIgnoreCase(Constants.ACTION_READER_BATTERY_CRITICAL) || action.equalsIgnoreCase(Constants.ACTION_READER_BATTERY_LOW)) {

        } else {
            Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
        }
    } else {
          /*  Intent i = new Intent(MainActivity.this, NotificationsService.class);
            i.putExtra(Constants.INTENT_ACTION, action);
            i.putExtra(Constants.INTENT_DATA, data);
            startService(i);*/
//        NotificationUtil.displayNotification(this, action, data);


    }
}

        public static RFIDController getInstance() {
            RFIDController result = instance;
            if (result == null) {
                synchronized (mutex) {
                    result = instance;
                    if (result == null)
                        instance = result = new RFIDController();
                }
            }
            return result;
        }

    public void accessOperationsWriteClicked(View view) {
        AutoCompleteTextView tagIDField = findViewById(R.id.accessRWTagID);
        final String tagId = (asciiMode == true ? asciitohex.convert(tagIDField.getText().toString()) : tagIDField.getText().toString());
        String offsetText = ((EditText) findViewById(R.id.accessRWOffsetValue)).getText().toString();
        String lengthText = ((EditText) findViewById(R.id.accessRWLengthValue)).getText().toString();
        final TextView accessRWData = findViewById(R.id.accessRWData);
        String accessRWpassword = ((EditText) findViewById(R.id.accessRWPassword)).getText().toString();
        String bankItem = ((Spinner) findViewById(R.id.accessRWMemoryBank)).getSelectedItem().toString();
        progressDialog = new CustomProgressDialog(this, MSG_READ);
        progressDialog.show();
        final Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_CONTENT_FRAGMENT);
        if (accessRWData != null) {
            accessRWData.setText("");
        }
        timerDelayRemoveDialog(Constants.RESPONSE_TIMEOUT, progressDialog, "Read");
        String tagValue;
        if (asciiMode == true)
            tagValue = asciitohex.convert(tagId);
        else tagValue = tagId;
        if (mConnectedReader == null || !mConnectedReader.isConnected())
            Toast.makeText(getApplicationContext(), "No Active Connection with Reader", Toast.LENGTH_SHORT).show();
        else if (!mConnectedReader.isCapabilitiesReceived())
            Toast.makeText(getApplicationContext(), "Reader capabilities not updated", Toast.LENGTH_SHORT).show();
        else if (tagValue.isEmpty())
            Toast.makeText(getApplicationContext(), "Please fill Tag Id", Toast.LENGTH_SHORT).show();
        else if (offsetText.isEmpty())
            Toast.makeText(getApplicationContext(), "Please fill offset", Toast.LENGTH_SHORT).show();
        else if (lengthText.isEmpty())
            Toast.makeText(getApplicationContext(), "Please fill length", Toast.LENGTH_SHORT).show();
        else
            getInstance().accessOperationsRead(tagId, offsetText, lengthText, accessRWpassword, bankItem,
                    new RfidListeners() {
                        @Override
                        public void onSuccess(Object object) {
                            progressDialog.dismiss();
                            if (isAccessCriteriaRead && !mIsInventoryRunning) {
                              handleTagResponse((TagData) object);
                            }


                        }

                        @Override
                        public void onFailure(Exception exception) {
                            progressDialog.dismiss();
                            if (exception instanceof InvalidUsageException) {
                                sendNotification(Constants.ACTION_READER_STATUS_OBTAINED, ((InvalidUsageException) exception).getInfo());
                            } else if (exception instanceof OperationFailureException) {
                                sendNotification(Constants.ACTION_READER_STATUS_OBTAINED, ((OperationFailureException) exception).getVendorMessage());
                            } else {
                                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(String message) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        }
                    });


    }
    public class EventHandler implements RfidEventsListener {

        @Override
        public void eventReadNotify(RfidReadEvents e) {
            if (mConnectedReader != null) {
                if (!mConnectedReader.Actions.MultiTagLocate.isMultiTagLocatePerforming()) {
                    final TagData[] myTags = mConnectedReader.Actions.getReadTags(100);
                    if (myTags != null) {
                        //Log.d("RFID_EVENT","l: "+myTags.length);
                        final Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_CONTENT_FRAGMENT);
                        for (int index = 0; index < myTags.length; index++) {
                            if (myTags[index].getOpCode() == ACCESS_OPERATION_CODE.ACCESS_OPERATION_READ &&
                                    myTags[index].getOpStatus() == ACCESS_OPERATION_STATUS.ACCESS_SUCCESS) {
                            }
                            if (myTags[index].isContainsLocationInfo()) {
                                final int tag = index;
                                TagProximityPercent = myTags[tag].LocationInfo.getRelativeDistance();
                                if (TagProximityPercent > 0) {

                                } else {
                                    if (isAccessCriteriaRead && !mIsInventoryRunning) {
                                        accessTagCount++;
                                    } else {
                                        if (myTags[index] != null && (myTags[index].getOpStatus() == null || myTags[index].getOpStatus() == ACCESS_OPERATION_STATUS.ACCESS_SUCCESS)) {

                                        }
                                    }
                                }
                            }
                        }
                    } else { ////multi-tal locationing results

                    }
                }
            }


        }

        @Override
        public void eventStatusNotify(RfidStatusEvents rfidStatusEvents) {

        }


//        private void disconnectReaderConnections() {
//            //disconnect from reader
//            if (mConnectedReader != null) {
//
//                try {
//                    if (mConnectedReader.isConnected()) {
//                        mConnectedReader.Events.removeEventsListener(eventHandler);
//                    }
//                    mConnectedReader.disconnect();
//                } catch (InvalidUsageException e) {
//                    e.printStackTrace();
//                } catch (OperationFailureException e) {
//                    e.printStackTrace();
//                }
//            }
//            mConnectedReader = null;
//            // update dpo icon in settings list
//
//            getInstance().clearSettings();
//            mConnectedDevice = null;
//            if (readers != null) {
//                readers.deattach((Readers.RFIDReaderEventHandler) WriteTag.this);
//                readers.Dispose();
//                readers = null;
//            }
//            reset();
//        }
//
    }

    public synchronized void inventoryStartOrStop() {

    }
}