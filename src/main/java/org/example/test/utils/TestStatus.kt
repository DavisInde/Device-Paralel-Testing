package org.example.test.utils;

public enum TestStatus {
    FAILED("failed"),
    PASSED("passed");

    public final String value;
    TestStatus(String value) {this.value = value;}

    public static String getTestStatus(boolean status) {
        if (status) return PASSED.value;
        else return FAILED.value;
    }
}