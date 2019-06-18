package hibernate.demo;

import hibernate.demo.entity.Instructor;

import java.util.ArrayList;
import java.util.List;

public class InstructorRegisterBean implements InstructorRegisterRemote {

    private List<Instructor> instructorRecord;

    public InstructorRegisterBean(List<Instructor> instructorRecord) {
        instructorRecord = new ArrayList<>();
    }

    @Override
    public void addInstructor(Instructor instructor) {
        instructorRecord.add(instructor);
    }

    @Override
    public List getInstructor() {
        return instructorRecord;
    }


}
