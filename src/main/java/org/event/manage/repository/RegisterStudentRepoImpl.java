package org.event.manage.repository;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.event.manage.Model.EventModel;
import org.event.manage.Model.RegModel;
import org.event.manage.Model.StudentModel;
import org.event.manage.dbConfig.DbInitialize;

import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RegisterStudentRepoImpl extends DbInitialize implements RegStudentRepo {
    EventModel eventModel = new EventModel();
    PreparedStatement stmt1 ;
    List<Object[]> list = new ArrayList<>();
    @Override
    public boolean RegisterStudent(RegModel regModel) {
        try{

            EventRepo eventRepo = new EventRepoImpl();
            boolean b = eventRepo.isEventFull(regModel.getEid());
            if(b){
                System.out.println("Event Capacity Full: Cannot Registerd");
            }

            stmt = con.prepareStatement("insert into registration values('0',?,?,?)");
//            stmt.setInt(1,regModel.getReg_id());
            stmt.setInt(1,regModel.getEid());
            stmt.setInt(2,regModel.getSid());
            stmt.setDate(3, Date.valueOf(LocalDate.now()));

            int value = stmt.executeUpdate();

            stmt1 = con.prepareStatement("update event set capacity = ? where event_id=?");

//            System.out.println(eventRepo.getCapacities(regModel.getEid()));
            stmt1.setInt(1,eventRepo.getCapacities(regModel.getEid())-1);
            stmt1.setInt(2,regModel.getEid());

            int values = stmt1.executeUpdate();
            if(values>0){
                System.out.println("Capacity is also updated in event table");
            }

            return value>0?true:false;



        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    @Override
    public List<Object[]> getRegistrationEventWise() {

        try{
            list.clear();
            stmt = con.prepareStatement("select e.name as event_name, s.name as student_name from registration r inner join student s on r.student_id = s.student_id inner join event e on r.event_id = e.event_id order by e.name ");
            rs = stmt.executeQuery();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            while (rs.next()){
                Object[] row = new Object[columnCount];

                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }

                list.add(row);
            }


        }catch (Exception e){
            System.out.println(e);
            return null;
        }

        return list;

    }

    @Override
    public List<Object[]> getRegistrationStudentWise() {
        try{
            list.clear();
            stmt = con.prepareStatement("select s.name as student_name,e.name as event_name from registration r inner join student s on r.student_id = s.student_id inner join event e on r.event_id = e.event_id order by s.name ");
            rs = stmt.executeQuery();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            while (rs.next()){
                Object[] row = new Object[columnCount];

                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }

                list.add(row);
            }


        }catch (Exception e){
            System.out.println(e);
            return null;
        }

        return list;

    }

    @Override
    public boolean CheckEventCapacity(RegModel regModel) {
        int RegisteredCount=0;
        try{
            EventRepo eventRepo = new EventRepoImpl();
            int capacity = eventRepo.getCapacities(regModel.getEid());

            stmt = con.prepareStatement("select count(*) from registration where event_id=?");
            stmt.setInt(1,regModel.getEid());

            rs = stmt.executeQuery();

            while(rs.next()){
                RegisteredCount = rs.getInt(1);
            }

            if(RegisteredCount<capacity){
                return true;
            }

        }catch(Exception e){
            System.out.println(e);

        }

        return false;

    }

    @Override
    public boolean DeleteUsingRegId(int id) {
        try{
            RegModel regModel = new RegModel();

            stmt = con.prepareStatement("delete from registration where registration_id=?");
            stmt.setInt(1,id);
            int value = stmt.executeUpdate();

//            stmt1 = con.prepareStatement("update event set capacity = ? where event_id=?");
//
////            System.out.println(eventRepo.getCapacities(regModel.getEid()));
//            EventRepo eventRepo = new EventRepoImpl();
//
//            stmt1.setInt(1,eventRepo.getCapacities(id)+1);
//            stmt1.setInt(2,id);
//
//            int values = stmt1.executeUpdate();
//            if(values>0){
//                System.out.println("Capacity is also updated in event table");
//            }

            return  value>0?true:false;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean UpdateStudentEvent(RegModel regModel) {
        try{

            stmt = con.prepareStatement("update registration set event_id=? where registration_id=?");
            stmt.setInt(1,regModel.getEid());
            stmt.setInt(2,regModel.getReg_id());

            int result = stmt.executeUpdate();

            return result>0?true:false;


        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    @Override
    public void exportsStudentToPDF(List<Object[]> obj, String path) {

        Document document = new Document();
        try{


            PdfWriter.getInstance(document,new FileOutputStream(path));
            document.open();
            PdfPTable table = new PdfPTable(2);

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Student List", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph("\n")); // space

            // Table Headers
            table.addCell("event_name");
            table.addCell("student_name");



            for(Object[]row :obj){
                table.addCell(row[0].toString());
                table.addCell(row[1].toString());

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
