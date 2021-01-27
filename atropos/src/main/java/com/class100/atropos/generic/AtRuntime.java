package com.class100.atropos.generic;

import com.class100.atropos.AtAbilityAdapter;

public final class AtRuntime extends AtAbilityAdapter {
    public static void throwNPE(String message) {
        throw new NullPointerException(message);
    }

    public static String logTraceStack(int stackTopOffset, int maxStackDepth) {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        StringBuilder sb = new StringBuilder();
        for (int i = stackTopOffset; i < maxStackDepth && i < elements.length; i++) {
            StackTraceElement e = elements[i];
            sb.append(e.getClassName())
                .append("#")
                .append(e.getMethodName())
                .append("\n");
        }
        return sb.toString();
    }
}