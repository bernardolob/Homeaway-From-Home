package system.student;

import system.service.Lodging;

import java.io.Serializable;

public interface Thrifty extends Serializable, Student{
    boolean isThrifty();

    void changeHome(Lodging newHome);
}
