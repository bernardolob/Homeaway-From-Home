package system.service;

import java.io.Serializable;

/**
 * Represents a leisure service
 * with associated location, price, and evaluation capabilities.
 * Extends the general Service interface.
 */
public interface Leisure extends Serializable, Service {

    /**
     * Returns the type of this service.
     *
     * @return ServiceType.LEISURE
     */
    ServiceType getType();

}
