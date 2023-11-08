package com.simplemealplanner.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdUtils {
    public static String createId() {
        return UUID.randomUUID().toString();
    }
}
