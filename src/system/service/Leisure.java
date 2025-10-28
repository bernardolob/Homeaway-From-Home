package system.service;

import java.io.Serializable;

public interface Leisure extends Serializable, Service{

    ServiceType getType();
}
