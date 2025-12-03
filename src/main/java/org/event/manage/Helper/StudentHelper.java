package org.event.manage.Helper;

import org.event.manage.Model.EventModel;
import org.event.manage.Model.StudentModel;
import org.event.manage.Service.StudentService;

import java.util.List;
import java.util.Scanner;

public class StudentHelper {

    static public void startWorking(){

            Scanner sc = new Scanner(System.in);
            System.out.println("*************************Student Services ************************************");
            System.out.println("1. Add Student ");
            System.out.println("2. View ALL Student");
            System.out.println("3. Delete Student By ID");
            System.out.println("4. Update Student By ID");
            System.out.println("5. Search Student By Dept");
            System.out.println("6. Search Student By Email");
            System.out.println("7. Export all the Students data to PDF");
            System.out.println("Enter your choice");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    sc.nextLine();
                    System.out.println("Enter name,email & department");
                    String sname = sc.nextLine();
                    String semail = sc.nextLine();
                    String sdept = sc.nextLine();
                    StudentModel s = new StudentModel();
                    s.setName(sname);
                    s.setEmail(semail);
                    s.setDepartment(sdept);
                    boolean b = ServiceHelper.studentService.AddStudent(s);
                    if(b){
                        System.out.println("Student Added Successfully");
                    }
                    else{
                        System.out.println("Student are not Added");
                    }

                break;

                case 2:
                    System.out.println("All Students List");
                    List<StudentModel> list = ServiceHelper.studentService.ViewAllStudent();
                    list.forEach((studentModel -> {
                        System.out.println(studentModel.getSid()+"  |  "+studentModel.getName()+"   |  "+studentModel.getEmail()+"   |  "+studentModel.getDepartment());
                    }));

                    break;

                case 3:
                    System.out.println("Enter an Id to be deleted");
                    int id = sc.nextInt();
                    boolean result = ServiceHelper.studentService.DeleteStudent(id);
                    if(result){
                        System.out.println("Student Deleted");
                    }else{
                        System.out.println("Student Not deleted");
                    }

                    break;

                case 4:

                    System.out.println("Enter an id of student for the update");
                    int idd = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter an values for update :- name ,email and department");
                    String uname = sc.nextLine();
                    String uemail = sc.nextLine();
                    String udept = sc.nextLine();
                    StudentModel ss = new StudentModel();
                    ss.setSid(idd);
                    ss.setName(uname);
                    ss.setEmail(uemail);
                    ss.setDepartment(udept);
                    boolean bb = ServiceHelper.studentService.UpdateStudent(ss);
                    if(bb){
                        System.out.println("Values Updated Successfully");
                    }else{
                        System.out.println("Values does not updated");
                    }

                break;

                case 5:
                    sc.nextLine();
                    System.out.println("Enter an department name to be serached");
                    String dept = sc.nextLine();
                    List<StudentModel> list1 = ServiceHelper.studentService.SearchStudentByDept(dept);
                    list1.forEach(studentModel -> {
                        System.out.println(studentModel.getSid()+"  |  "+studentModel.getName()+"   |  "+studentModel.getEmail()+"   |  "+studentModel.getDepartment());

                    });

                    break;

                case 6:
                    sc.nextLine();
                    System.out.println("Enter an email name to be serached");
                    String email = sc.nextLine();
                    List<StudentModel> list2 = ServiceHelper.studentService.SearchStudentByEmail(email);
                    list2.forEach(studentModel -> {
                        System.out.println(studentModel.getSid()+"  |  "+studentModel.getName()+"   |  "+studentModel.getEmail()+"   |  "+studentModel.getDepartment());

                    });

                    break;

                case 7:
                    System.out.println("Student Report in pdf are stored in your desktop");
                    List<StudentModel> listDownload  = ServiceHelper.studentService.ViewAllStudent();
                    ServiceHelper.studentService.exportsStudentToPDF(listDownload,"C:\\Users\\ACER\\Desktop\\student.pdf");

                    break;

                case 8:


                default:
                    System.out.println("Invalid choice");

            }

    }
}
