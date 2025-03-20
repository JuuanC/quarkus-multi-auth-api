package org.auth.common.utils;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.UUID;

@RegisterForReflection
public class GenerateUUID {

    public static String generate(String requestId) {
        return (requestId != null) ? requestId : UUID.randomUUID().toString();
    }
}
