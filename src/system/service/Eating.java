package system.service;

import java.io.Serializable;

/**
 * Represents an eating service
 * with associated location, price, discount and capacity.
 * Extends the general Service interface.
 */
public interface Eating extends Serializable, LimitedService{

    /**
     * Returns the type of this service.
     *
     * @return ServiceType.LEISURE
     */
    ServiceType getType();

}
