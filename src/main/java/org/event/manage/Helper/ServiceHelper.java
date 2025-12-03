package org.event.manage.Helper;

import org.event.manage.Service.*;

public class ServiceHelper {
    static public EventService eventService = new EventServiceImpl();
    static public StudentService studentService = new StudentServiceImpl();
    static public RegStudentService regHelper = new RegStudentServiceImple();

}
