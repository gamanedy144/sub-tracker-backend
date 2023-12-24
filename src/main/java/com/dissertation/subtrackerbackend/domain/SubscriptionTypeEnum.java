package com.dissertation.subtrackerbackend.domain;

public enum SubscriptionTypeEnum {
    DAILY("daily"),
    MONTHLY("monthly"),
    BIMONTHLY("bi-monthly"),
    YEARLY("yearly"),
    WEEKLY("weekly");

    public final String label;

    private SubscriptionTypeEnum(String label){
        this.label = label;
    }
}
