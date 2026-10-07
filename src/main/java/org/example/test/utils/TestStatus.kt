package org.example.test.utils

enum class TestStatus(value: String) {
    FAILED("failed"),
    PASSED("passed");

    val value: String?

    init {
        this.value = value
    }

    companion object {
        @JvmStatic
        fun getTestStatus(status: Boolean): String? {
            if (status) return PASSED.value
            else return FAILED.value
        }
    }
}