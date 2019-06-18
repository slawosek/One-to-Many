package hibernate.demo;

import hibernate.demo.entity.Instructor;

import java.util.List;

public interface InstructorRegisterRemote {
    void addInstructor(Instructor instructor);
    List getInstructor();
}
