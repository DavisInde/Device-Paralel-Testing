package org.example.test.utils

enum class PlatformType(val rawValue: String) {
    IOS("ios"),
    ANDROID("android");

    companion object {
        fun getPlatformByName(rawValue: String): PlatformType? {
            return when (rawValue.lowercase()) {
                ANDROID.rawValue -> ANDROID
                IOS.rawValue -> IOS
                else -> null
            }
        }
    }
}