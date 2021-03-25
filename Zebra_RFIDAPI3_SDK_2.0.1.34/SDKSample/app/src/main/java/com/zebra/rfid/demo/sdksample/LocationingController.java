package com.zebra.rfid.demo.sdksample;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.zebra.rfid.api3.InvalidUsageException;
import com.zebra.rfid.api3.OperationFailureException;
import com.zebra.rfid.api3.START_TRIGGER_TYPE;

public class LocationingController {

    protected LocationingController() {
    }

    public void locationing(final String locateTag, final RfidListeners rfidListeners) {

        if (RFIDController.mConnectedReader != null && RFIDController.mConnectedReader.isConnected()) {
            if (!RFIDController.isLocatingTag) {
                RFIDController.currentLocatingTag = locateTag;
                RFIDController.TagProximityPercent = 0;
                if (locateTag != null && !locateTag.isEmpty()) {
                    RFIDController.isLocatingTag = true;
                    new AsyncTask<Void, Void, Boolean>() {
                        private InvalidUsageException invalidUsageException;
                        private OperationFailureException operationFailureException;

                        @Override
                        protected Boolean doInBackground(Void... voids) {
                            try {
                                if (RFIDController.asciiMode) {
                                    RFIDController.mConnectedReader.Actions.TagLocationing.Perform(asciitohex.convert(locateTag), null, null);
                                }else {
                                    RFIDController.mConnectedReader.Actions.TagLocationing.Perform(locateTag, null, null);
                                    //RFIDController.isLocatingTag = true;
                                }
                            } catch (InvalidUsageException e) {
                                e.printStackTrace();
                                invalidUsageException = e;
                            } catch (OperationFailureException e) {
                                e.printStackTrace();
                                operationFailureException = e;
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Boolean result) {
                            if (invalidUsageException != null) {
                                RFIDController.currentLocatingTag = null;
                                rfidListeners.onFailure(invalidUsageException);
                            } else if (operationFailureException != null) {
                                RFIDController.currentLocatingTag = null;
                                rfidListeners.onFailure(operationFailureException);
                            } else
                                rfidListeners.onSuccess(null);
                        }
                    }.execute();
                } else {
                    Log.d(RFIDController.TAG, Constants.TAG_EMPTY);
                    rfidListeners.onFailure(Constants.TAG_EMPTY);
                }

            } else {
                boolean isLocationingAborted = false;
                boolean mIsInventoryRunning = false;
                boolean isLocatingTag = false;
                boolean  isInventoryAborted = false;
                new AsyncTask<Void, Void, Boolean>() {
                    private InvalidUsageException invalidUsageException;
                    private OperationFailureException operationFailureException;

                    @Override
                    protected Boolean doInBackground(Void... voids) {
                        try {
                            RFIDController.mConnectedReader.Actions.TagLocationing.Stop();
                            if (((RFIDController.settings_startTrigger != null && (RFIDController.settings_startTrigger.getTriggerType() == START_TRIGGER_TYPE.START_TRIGGER_TYPE_HANDHELD || RFIDController.settings_startTrigger.getTriggerType() == START_TRIGGER_TYPE.START_TRIGGER_TYPE_PERIODIC)))
                                    || (RFIDController.isBatchModeInventoryRunning != null && RFIDController.isBatchModeInventoryRunning))
                                ConnectionController.operationHasAborted(rfidListeners);
                        } catch (InvalidUsageException e) {
                            invalidUsageException = e;
                            e.printStackTrace();
                        } catch (OperationFailureException e) {
                            operationFailureException = e;
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Boolean result) {
                        RFIDController.currentLocatingTag = null;
                        if (invalidUsageException != null) {
                            rfidListeners.onFailure(invalidUsageException);

                        } else if (operationFailureException != null) {
                            rfidListeners.onFailure(operationFailureException);
                        } else
                            rfidListeners.onSuccess(null);
                    }
                }.execute();
            }
        } else
            rfidListeners.onFailure("No Active Connection with Reader");
    }


}
