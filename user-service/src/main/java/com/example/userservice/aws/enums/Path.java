package com.example.userservice.aws.enums;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/5/2023 12:53 PM
 */
public enum Path {
    USER("user/"),
    PRODUCT("product/");

    private final String url;

    Path(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
