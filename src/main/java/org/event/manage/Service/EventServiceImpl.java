package org.event.manage.Service;

import org.event.manage.Model.EventModel;
import org.event.manage.repository.EventRepo;
import org.event.manage.repository.EventRepoImpl;

public class EventServiceImpl implements EventService{
    EventRepo erepo = new EventRepoImpl();
    @Override
    public boolean addNewEvent(EventModel eventModel) {
        return erepo.addNewEvent(eventModel);
    }
}
