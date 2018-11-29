package com.itechart.call.me.library.entity.enumirator;


import java.io.Serializable;

/**
 * Class represents different types of genders
 *
 * @author Yuriy
 */
public enum Gender implements Serializable {

    UNDEFINED("UNDEFINED"), MALE("MALE"), FEMALE("FEMALE");

    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public static Gender getGender(String gender) {
        if (gender == null)
            return null;
        for (Gender g : values()) {
            if (g.getGender().equals(gender))
                return g;
        }
        return null;
    }
}
