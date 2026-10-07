package org.example.test.base;

/**
 * Implement this if you need config
 */
abstract public class ConfigConsumer {
    protected Config config;

    protected ConfigConsumer(Config config) {
        this.config = config;
    }
}
