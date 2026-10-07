package org.example.test.models;

public class Device {
  public boolean userBlocked;

  //Port for iOS
  public int wdaLocalPort;
  public int mjpegServerPort;
  //Port for Android
  public int adbPort;
  public int systemPort;

  public String name;
  public String host;
  public String sdk;
  public boolean offline;
  public boolean realDevice;
  public String udid;
  public boolean busy;
  public String platform;
  public boolean liveStreaming;

  public Device() {
    this.userBlocked = false;

    this.wdaLocalPort = 0;
    this.mjpegServerPort = 0;
    this.adbPort = 0;
    this.systemPort = 0;

    this.name = "";
    this.host = "";
    this.sdk = "";
    this.offline = false;
    this.realDevice = false;
    this.udid = "";
    this.busy = false;
    this.platform = "";
    this.liveStreaming = false;
  }

  public Device(
          boolean userBlocked,
          int wdaLocalPort,
          int mjpegServerPort,
          int adbPort,
          int systemPort,
          String name,
          String host,
          String sdk,
          boolean offline,
          boolean realDevice,
          String udid,
          boolean busy,
          String platform,
          boolean liveStreaming
  ) {
    this.userBlocked = userBlocked;

    this.wdaLocalPort = wdaLocalPort;
    this.mjpegServerPort = mjpegServerPort;
    this.adbPort = adbPort;
    this.systemPort = systemPort;

    this.name = name;
    this.host = host;
    this.sdk = sdk;
    this.offline = offline;
    this.realDevice = realDevice;
    this.udid = udid;
    this.busy = busy;
    this.platform = platform;
    this.liveStreaming = liveStreaming;
  }

  public Device(
          String deviceName,
          String hostName,
          String deviceUdid,
          String sdkVersion,
          int systemPort,
          int adbPort
  ) {
    this.adbPort = adbPort;
    this.systemPort = systemPort;

    this.name = deviceName;
    this.host = hostName;
    this.sdk = sdkVersion;
    this.udid = deviceUdid;
  }
}
