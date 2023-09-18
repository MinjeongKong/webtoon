package com.minjeong.webtoon.domain;

public enum Genre {
    PURE, FANTASY, ACTION, DAILY, THRILL;

    private static final Genre[] ENUMS = Genre.values();

    public static Genre of(int code) {
        if (code < 1 || code > 5) {
            throw new IllegalArgumentException("Invalid value for genre: " + code);
        }
        return ENUMS[code-1];
    }
}
