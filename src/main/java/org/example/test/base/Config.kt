package org.example.test.base

import org.springframework.stereotype.Component

@Component
class Config(
    var baseUrl: String? = System.getenv("APPIUM_URL") ?: "http://localhost:4723/",
    /** Android package */
    var appPackageId: String? = "com.alfamart.mylawson.staging",
    var appActivity: String? = "",
    /** Must match appium --base-path. Running server uses "" (root). Use "wd/hub" if started with -pa /wd/hub */
    var basePath: String = System.getenv("APPIUM_BASE_PATH") ?: "",
    /** iOS bundle id, change if differs from Android package */
    var iosBundleId: String? = System.getenv("IOS_BUNDLE_ID") ?: "com.alfamart.mylawson.staging",
    /** Apple team id, needed to sign WebDriverAgent on real iPhone */
    var xcodeOrgId: String? = System.getenv("XCODE_ORG_ID") ?: "",
    var xcodeSigningId: String? = "iPhone Developer",
    var updatedWDABundleId: String? = System.getenv("WDA_BUNDLE_ID") ?: "com.alfamart.WebDriverAgentRunner"
)
