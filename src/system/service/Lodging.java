package system.service;

import java.io.Serializable;

public interface Lodging extends Serializable, LimitedService{
    boolean isCheaper(Lodging home);

    ServiceType getType();
    String getStringType();


}
