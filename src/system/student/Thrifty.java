package system.student;

import system.service.Lodging;

public interface Thrifty extends Student{
    boolean isThrifty();

    void changeHome(Lodging newHome);
}
