package system.student;

import system.service.Lodging;

import java.io.Serializable;

public interface Thrifty extends Serializable, Student{

    /**
     * Change home to a newHome.
     * @param newHome - the new lodging service to assign to the student
     */
    void changeHome(Lodging newHome);
}
