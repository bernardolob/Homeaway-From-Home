package system.service;

import java.io.Serializable;

/**
 * Represents a lodging service, storing its location, price, name,
 * and capacity. Extends the limited-capacity service class and
 * provides lodging-specific behavior.
 */
public interface Lodging extends Serializable, LimitedService{

    /**
     * Checks if this lodging is cheaper than another lodging.
     *
     * @param home lodging to compare against
     * @pre home != null
     * @return true if this lodging has a lower price, false otherwise
     */
    boolean isCheaper(Lodging home);

    /**
     * Returns the service type of this lodging.
     *
     * @return ServiceType.LODGING
     */
    ServiceType getType();


}
