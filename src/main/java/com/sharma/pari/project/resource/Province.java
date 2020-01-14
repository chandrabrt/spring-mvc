package com.sharma.pari.project.resource;

public enum Province {
    PROVINCE1("Province 1"),
    PROVINCE2("Province 2"),
    PROVINCE3("Province 3"),
    PROVINCE4("Province 4"),
    PROVINCE5("Province 5"),
    PROVINCE6("Province 6"),
    PROVINCE7("Province 7");

    private final String key;

    Province(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
