package org.example.test.models

class Device {
    var userBlocked: Boolean = false

    //Port for iOS
    var wdaLocalPort: Int = 0
    var mjpegServerPort: Int = 0

    //Port for Android
    var adbPort: Int
    var systemPort: Int

    var name: String?
    var host: String?
    var sdk: String?
    var offline: Boolean = false
    var realDevice: Boolean = false
    var udid: String?
    var busy: Boolean = false
    var platform: String? = null
    var liveStreaming: Boolean = false

    constructor() {
        this.userBlocked = false

        this.wdaLocalPort = 0
        this.mjpegServerPort = 0
        this.adbPort = 0
        this.systemPort = 0

        this.name = ""
        this.host = ""
        this.sdk = ""
        this.offline = false
        this.realDevice = false
        this.udid = ""
        this.busy = false
        this.platform = ""
        this.liveStreaming = false
    }

    constructor(
        userBlocked: Boolean,
        wdaLocalPort: Int,
        mjpegServerPort: Int,
        adbPort: Int,
        systemPort: Int,
        name: String?,
        host: String?,
        sdk: String?,
        offline: Boolean,
        realDevice: Boolean,
        udid: String?,
        busy: Boolean,
        platform: String?,
        liveStreaming: Boolean
    ) {
        this.userBlocked = userBlocked

        this.wdaLocalPort = wdaLocalPort
        this.mjpegServerPort = mjpegServerPort
        this.adbPort = adbPort
        this.systemPort = systemPort

        this.name = name
        this.host = host
        this.sdk = sdk
        this.offline = offline
        this.realDevice = realDevice
        this.udid = udid
        this.busy = busy
        this.platform = platform
        this.liveStreaming = liveStreaming
    }

    constructor(
        deviceName: String?,
        hostName: String?,
        deviceUdid: String?,
        sdkVersion: String?,
        systemPort: Int,
        adbPort: Int
    ) {
        this.adbPort = adbPort
        this.systemPort = systemPort

        this.name = deviceName
        this.host = hostName
        this.sdk = sdkVersion
        this.udid = deviceUdid
    }
}
