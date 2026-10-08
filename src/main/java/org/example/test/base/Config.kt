package org.example.test.base

import org.springframework.stereotype.Component

/**
 * @field baseUrl This is the base url for appium server
 * @field appPackageId This is bundle id or app package name
 * @field appActivity Main activity to be launched in android
 * @field platform either android/ iOS to test each one
 */
@Component
class Config {
    var baseUrl: String? = null
    var appPackageId: String? = null
    var appActivity: String? = null
    var platform: String? = null

    //TODO: change baseUrl if not using docker
    constructor() {
        this.baseUrl = "http://localhost:4723/"
        this.appPackageId = ""
        this.appActivity = ""
        this.platform = "android"
    }

    constructor(baseUrl: String?, appPackageId: String?, appActivity: String?, platform: String?) {
        this.baseUrl = baseUrl
        this.appPackageId = appPackageId
        this.appActivity = appActivity
        this.platform = platform
    }
}