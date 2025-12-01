package org.event.manage.repository;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.event.manage.Model.EventModel;
import org.event.manage.dbConfig.DbInitialize;

import java.io.FileOutputStream;
import java.lang.annotation.Documented;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.*;
import java.sql.Date;

public class EventRepoImpl extends DbInitialize implements EventRepo{

    List<EventModel> list = new ArrayList<EventModel>();
    LocalDate currentDate = LocalDate.now();
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

    @Override
    public List<EventModel> ViewAllEvent() {
        try{
            list.clear();
            stmt = con.prepareStatement("select * from event");
            rs = stmt.executeQuery();
            while (rs.next()){
                EventModel eventModel = new EventModel();
                eventModel.setEid(rs.getInt(1));
                eventModel.setName(rs.getString(2));
                eventModel.setEdate(rs.getDate(3).toLocalDate());
                eventModel.setVenue(rs.getString(4));
                eventModel.setCapacity(rs.getInt(5));
                list.add(eventModel);
            }








        }catch(Exception e){
            System.out.println(e);


        }

        return list;
    }

    @Override
    public List<EventModel> ViewUpcomingEvent() {
        try {

            stmt = con.prepareStatement("select *from event where event_date > CURDATE()");
            rs = stmt.executeQuery();
            while (rs.next()){
                EventModel eventModel = new EventModel();
                eventModel.setEid(rs.getInt(1));
                eventModel.setName(rs.getString(2));
                eventModel.setEdate(rs.getDate(3).toLocalDate());
                eventModel.setVenue(rs.getString(4));
                eventModel.setCapacity(rs.getInt(5));
                list.add(eventModel);
            }


        }catch (Exception e){
            System.out.println(e);
        }

        return list;
    }

    @Override
    public boolean DeleteEvent(int id) {
        try{
            stmt = con.prepareStatement("delete from event where event_id=?");
            stmt.setInt(1,id);
            int value = stmt.executeUpdate();
            return value>0?true:false;


        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean UpdateEvent(EventModel model) {
       try{

           stmt = con.prepareStatement("update event SET event_date=?,venue=?,capacity=? where name=?");
           stmt.setDate(1, Date.valueOf(model.getEdate()));
           stmt.setString(2,model.getVenue());
           stmt.setInt(3,model.getCapacity());
           stmt.setString(4,model.getName());

           int result  = stmt.executeUpdate();
           return result>0?true:false;

       }catch (Exception e){
           System.out.println(e);
           return false;
       }
    }

    @Override
    public void exportsUsersToPDF(List<EventModel> userList, String path) {
        Document document = new Document();
        try{


            PdfWriter.getInstance(document,new FileOutputStream(path));
            document.open();
            PdfPTable table = new PdfPTable(5);

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("User List", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph("\n")); // space

            // Table Headers
            table.addCell("event_id");
            table.addCell("name");
            table.addCell("event_date");
            table.addCell("venue");
            table.addCell("capacity");

            for(EventModel e:list){
                table.addCell(String.valueOf(e.getEid()));
                table.addCell(e.getName());
                table.addCell(String.valueOf(e.getEdate()));
                table.addCell(e.getVenue());
                table.addCell(String.valueOf(e.getCapacity()));

            }

            document.add(table);




        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            if(document.isOpen()){
                document.close();
            }
        }


        System.out.println("PDF Created Successfully ");
    }
}
