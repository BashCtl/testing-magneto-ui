package org.magneto.entities;

import lombok.Getter;

public enum EntityType {
    CHECKOUT("checkout.json", Checkout.class);


    private final String file;

    public String getFile() {
        return file;
    }

    public Class getClassType() {
        return classType;
    }


    private final Class<? extends Entity> classType;

    EntityType(String file, Class<? extends Entity> classType) {
        this.file = file;
        this.classType = classType;
    }

}
