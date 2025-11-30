package org.event.manage.repository;

import org.event.manage.Model.EventModel;
import org.event.manage.dbConfig.DbInitialize;

import java.sql.Date;

public class EventRepoImpl extends DbInitialize implements EventRepo{

    @Override
    public boolean addNewEvent(EventModel eventModel) {
        try{

            stmt = con.prepareStatement("insert into event values('0',?,?,?,?)");

            stmt.setString(1,eventModel.getName());
            stmt.setDate(2, java.sql.Date.valueOf(eventModel.getEdate()));
            stmt.setString(3,eventModel.getVenue());
            stmt.setInt(4,eventModel.getCapacity());

            int result = stmt.executeUpdate();

            return result>0?true:false;

        } catch (Exception e) {
            System.out.println(e);
            return false;

        }
    }
}
