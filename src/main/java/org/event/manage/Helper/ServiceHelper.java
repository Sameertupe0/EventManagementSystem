package org.event.manage.Helper;

import org.event.manage.Service.EventService;
import org.event.manage.Service.EventServiceImpl;
import org.event.manage.Service.StudentService;
import org.event.manage.Service.StudentServiceImpl;

public class ServiceHelper {
    static EventService eventService = new EventServiceImpl();
    static StudentService studentService = new StudentServiceImpl();

}
