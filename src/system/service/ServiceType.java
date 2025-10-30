package system.service;

import exceptions.InvalidServiceTypeException;

public enum ServiceType {
    LODGING,
    EATING,
    LEISURE;

    private final String alias;

    ServiceType() {
        this.alias = this.toString();
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    public static ServiceType fromString(String text) {
        for (ServiceType type : values()) {
            if (type.alias.equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new InvalidServiceTypeException();
    }
}
