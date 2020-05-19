package com.matthewlooman.retriever.rest.resource;

import com.google.gson.annotations.SerializedName;

public class ServerRegistration {
  @SerializedName("device_identifier")
  private String mDeviceIdentifier;

  public ServerRegistration(String deviceIdentifier) {
    this.mDeviceIdentifier = deviceIdentifier;
  }

  public String getDeviceIdentifier() {
    return mDeviceIdentifier;
  }

  public void setDeviceIdentifier(String deviceIdentifier) {
    this.mDeviceIdentifier = deviceIdentifier;
  }
}
