package com.itechart.call.me.library.entity.enumirator;

import java.io.Serializable;

/**
 * Class represents base phone types
 *
 * @author Yuriy
 */
public enum PhoneType implements Serializable {
    HOME("HOME"), MOBILE("MOBILE");

    private String phoneType;

    PhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public static PhoneType getPhoneType(String phoneType) {
        if (phoneType == null)
            return null;
        for (PhoneType g : values()) {
            if (g.getPhoneType().equals(phoneType))
                return g;
        }
        return null;
    }
}
