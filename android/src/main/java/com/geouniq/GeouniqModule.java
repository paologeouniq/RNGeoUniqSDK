
package com.geouniq;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.geouniq.android.GeoUniq;

public class GeouniqModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public GeouniqModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "Geouniq";
    }

    @ReactMethod
    public void enable(final Callback successCallback) {
        GeoUniq.getInstance(reactContext).enable();
        successCallback.invoke();
    }

    @ReactMethod
    public void disable(final Callback successCallback) {
        GeoUniq.getInstance(reactContext).disable();
        successCallback.invoke();
    }

    @ReactMethod
    public void showConsentDialogAndSet(final Callback errorCallback, final Callback successCallback) {
        if (getCurrentActivity() == null) {
            errorCallback.invoke("Current activity is null");
            return;
        }

        GeoUniq.getInstance(reactContext).showConsentDialogAndSet(getCurrentActivity(), new GeoUniq.IConsentAlertResponseListener() {
            @Override
            public void onResponse(boolean b) {
                successCallback.invoke(b);
            }
        });
    }

    @ReactMethod
    private void setConsentStatus(final boolean status, final Callback successCallback) {
        GeoUniq.getInstance(reactContext).setConsentStatus(status);
        successCallback.invoke();
    }

    @ReactMethod
    private void getConsentStatus(final Callback successCallback) {
        boolean consentStatus = GeoUniq.getInstance(reactContext).getConsentStatus();
        successCallback.invoke(consentStatus);
    }

    @ReactMethod
    private void setDeviceIdListener(final Callback successCallback) {
        GeoUniq.getInstance(reactContext).setDeviceIdListener(new GeoUniq.IDeviceIdListener() {
            @Override
            public void onDeviceIdAvailable(String deviceID) {
                successCallback.invoke(deviceID);
            }
        });
    }

    @ReactMethod
    private void getDeviceId(final Callback successCallback) {
        String deviceId = GeoUniq.getInstance(reactContext).getDeviceId();
        successCallback.invoke(deviceId);
    }

    @ReactMethod
    private void isDeviceIdAvailable(final Callback errorCallback, final Callback successCallback) {
        if (getCurrentActivity() == null) {
            errorCallback.invoke("Current activity is null");
            return;
        }

        boolean isDeviceIdAvailable = GeoUniq.getInstance(reactContext).isDeviceIdAvailable();
        successCallback.invoke(isDeviceIdAvailable);
    }

    @ReactMethod
    private void setCustomId(final String customId, final Callback successCallback) {
        final boolean isSet = GeoUniq.getInstance(reactContext).setCustomId(customId);
        successCallback.invoke(customId, isSet);
    }

    @ReactMethod
    private void solveIssues(final Callback errorCallback, final Callback successCallback) {
        GeoUniq.getInstance(reactContext).setErrorListener(new GeoUniq.IErrorListener() {
            @Override
            public void onError(GeoUniq.RequestResult requestResult) {
                if (getCurrentActivity() == null) {
                    errorCallback.invoke("Current activity is null");
                    return;
                }

                if (requestResult.hasResolution()) {
                    requestResult.startResolution(getCurrentActivity());
                } else {
                    successCallback.invoke();
                }
            }
        });
    }
}