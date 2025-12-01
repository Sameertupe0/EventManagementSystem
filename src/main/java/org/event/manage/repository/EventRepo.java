package org.event.manage.repository;

import jdk.jfr.Event;
import org.event.manage.Model.EventModel;

import java.util.List;

public interface EventRepo {

    public boolean addNewEvent(EventModel eventModel);
    public List<EventModel> ViewAllEvent();
    public List<EventModel> ViewUpcomingEvent();
    public boolean DeleteEvent(int id);
    public boolean UpdateEvent(EventModel eventModel);
    public void exportsUsersToPDF(List<EventModel> userList,String path);

}
