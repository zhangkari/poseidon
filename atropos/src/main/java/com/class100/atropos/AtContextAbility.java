package com.class100.atropos;

public abstract class AtContextAbility extends AtAbilityAdapter {
    protected static AtContextEnv env;

    static void initialize(AtContextEnv env) {
        AtContextAbility.env = env;
    }
}
