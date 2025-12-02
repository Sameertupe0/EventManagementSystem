package org.event.manage.repository;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.event.manage.Model.EventModel;
import org.event.manage.Model.StudentModel;
import org.event.manage.dbConfig.DbInitialize;

import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepoImpl extends DbInitialize implements StudentRepo{
    List<StudentModel> list = new ArrayList<>();
    @Override
    public boolean AddStudent(StudentModel studentModel) {
        try{

            stmt = con.prepareStatement("insert into student values('0',?,?,?)");
            stmt.setString(1,studentModel.getName());
            stmt.setString(2,studentModel.getEmail());
            stmt.setString(3,studentModel.getDepartment());

            int result  = stmt.executeUpdate();

            return result>0?true:false;


        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public List<StudentModel> ViewAllStudent() {

        try{
            list.clear();
            stmt = con.prepareStatement("select *from student");
            rs = stmt.executeQuery();

            while(rs.next()){
                StudentModel s = new StudentModel();
                s.setSid(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setEmail(rs.getString(3));
                s.setDepartment(rs.getString(4));
                list.add(s);
            }


        }catch (Exception e){
            System.out.println(e);


        }
        return list;
    }

    @Override
    public boolean DeleteStudent(int id) {
        try{

            stmt  = con.prepareStatement("delete from student where student_id=?");
            stmt.setInt(1,id);
            int result =  stmt.executeUpdate();

            return result>0?true:false;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    @Override
    public boolean UpdateStudent(StudentModel studentModel) {
        try{

            stmt = con.prepareStatement("update student set name=?,email=?,department=? where student_id=?");
            stmt.setString(1,studentModel.getName());
            stmt.setString(2,studentModel.getEmail());
            stmt.setString(3,studentModel.getDepartment());
            stmt.setInt(4,studentModel.getSid());

            int result = stmt.executeUpdate();

            return  result>0?true:false;

        }catch (Exception e){
            System.out.println(e);
            return false;
        }

    }

    @Override
    public List<StudentModel> SearchStudentByDept(String department) {
        try{
            list.clear();
            stmt = con.prepareStatement("select *from student where department=?");
            stmt.setString(1,department);
            rs = stmt.executeQuery();
            while (rs.next()){
                StudentModel studentModel = new StudentModel();
                studentModel.setSid(rs.getInt(1));
                studentModel.setName(rs.getString(2));
                studentModel.setEmail(rs.getString(3));
                studentModel.setDepartment(rs.getString(4));
                list.add(studentModel);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }

    @Override
    public List<StudentModel> SearchStudentByEmail(String email) {
        try{
            list.clear();
            stmt = con.prepareStatement("select *from student where email=?");
            stmt.setString(1,email);
            rs = stmt.executeQuery();
            while (rs.next()){
                StudentModel studentModel = new StudentModel();
                studentModel.setSid(rs.getInt(1));
                studentModel.setName(rs.getString(2));
                studentModel.setEmail(rs.getString(3));
                studentModel.setDepartment(rs.getString(4));
                list.add(studentModel);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return list;

    }

    @Override
    public void exportsStudentToPDF(List<StudentModel> studentList, String path) {
        Document document = new Document();
        try{


            PdfWriter.getInstance(document,new FileOutputStream(path));
            document.open();
            PdfPTable table = new PdfPTable(4);

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Student List", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph("\n")); // space

            // Table Headers
            table.addCell("student_id");
            table.addCell("name");
            table.addCell("email");
            table.addCell("Department");


            for(StudentModel e:list){
                table.addCell(String.valueOf(e.getSid()));
                table.addCell(e.getName());
                table.addCell(String.valueOf(e.getEmail()));
                table.addCell(e.getDepartment());

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
