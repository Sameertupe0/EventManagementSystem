package org.event.manage.Service;

import org.event.manage.Model.EventModel;
import org.event.manage.repository.EventRepo;
import org.event.manage.repository.EventRepoImpl;

import java.util.List;

public class EventServiceImpl implements EventService{
    EventRepo erepo = new EventRepoImpl();
    @Override
    public boolean addNewEvent(EventModel eventModel) {
        return erepo.addNewEvent(eventModel);
    }

    @Override
    public List<EventModel> ViewAllEvent() {
        return erepo.ViewAllEvent();
    }

    @Override
    public List<EventModel> ViewUpcomingEvent() {
        return erepo.ViewUpcomingEvent();
    }

    @Override
    public boolean DeleteEvent(int id) {
        return erepo.DeleteEvent(id);
    }

    @Override
    public boolean UpdateEvent(EventModel eventModel) {
        return erepo.UpdateEvent(eventModel);
    }

    @Override
    public void exportsUsersToPDF(List<EventModel> userList, String path) {
        erepo.exportsUsersToPDF(userList, path);
    }
}
