package org.example.test.base

import org.springframework.stereotype.Component

@Component
data class Config(
    var baseUrl: String? = System.getenv("APPIUM_URL") ?: "http://localhost:4723/",
    /** Android package */
    var appPackageId: String? = "com.alfamart.alfagift.staging",
    var appActivity: String? = "",
    /** Must match appium --base-path. Running server uses "" (root). Use "wd/hub" if started with -pa /wd/hub */
    var basePath: String = System.getenv("APPIUM_BASE_PATH") ?: "",
    /** Apple team id, needed to sign WebDriverAgent on real iPhone */
    var xcodeOrgId: String? = System.getenv("XCODE_ORG_ID") ?: "",
    var xcodeSigningId: String? = "iPhone Developer",
    var updatedWDABundleId: String? = System.getenv("WDA_BUNDLE_ID") ?: "com.alfamart.WebDriverAgentRunner",
    var platform: String? = "Apalah"
) {
    companion object {
        val instance by lazy { Config() }
    }
}
