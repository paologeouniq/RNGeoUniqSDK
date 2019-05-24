
package com.geouniq.rngeouniq;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class RNGeouniqSdkModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNGeouniqSdkModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNGeouniqSdk";
  }
}