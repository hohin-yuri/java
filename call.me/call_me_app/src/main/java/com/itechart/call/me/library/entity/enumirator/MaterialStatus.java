package com.itechart.call.me.library.entity.enumirator;

/**
 * Class represents peoples
 * material statuses
 *
 * @author Yuriy
 */
public enum MaterialStatus {
    MARRIED("MARRIED"), SINGLE("SINGLE"), DIVORCED("DIVORCED"), WIDOWED("WIDOWED");

    private String status;

    MaterialStatus(String status) {
        this.status = status;
    }

    public String getMaterialStatus() {
        return status;
    }

    public static MaterialStatus getGender(String status) {
        if (status == null)
            return null;
        for (MaterialStatus g : values()) {
            if (g.getMaterialStatus().equals(status))
                return g;
        }
        return null;
    }
}
