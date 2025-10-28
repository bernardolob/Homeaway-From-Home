package system.service;

import java.io.Serializable;

public interface Eating extends Serializable, LimitedService{

    ServiceType getType();
}
