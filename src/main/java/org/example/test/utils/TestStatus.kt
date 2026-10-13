package org.example.test.utils

enum class TestStatus(val value: String) {
    FAILED("failed"),
    PASSED("passed");

    companion object {
        @JvmStatic
        fun getTestStatus(status: Boolean): String? {
            return if (status) PASSED.value else FAILED.value
        }
    }
}