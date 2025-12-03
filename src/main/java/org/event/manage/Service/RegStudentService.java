package org.event.manage.Service;

import jdk.jfr.Event;
import org.event.manage.Model.EventModel;
import org.event.manage.Model.RegModel;
import org.event.manage.Model.StudentModel;

import java.util.List;

public interface RegStudentService {
    public boolean RegisterStudent(RegModel regModel);
    public List<Object[]> getRegistrationEventWise();
    public List<Object[]> getRegistrationStudentWise();
    public boolean CheckEventCapacity(RegModel regModel);
    public boolean DeleteUsingRegId(int id);
    public boolean UpdateStudentEvent(RegModel regModel);
    public void exportsStudentToPDF(List<Object[]> obj, String path);


}
