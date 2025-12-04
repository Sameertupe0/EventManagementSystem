package org.event.manage.Helper;

import org.event.manage.Model.RegModel;
import org.event.manage.Model.StudentModel;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RegHelper {

    static public void startWorking(){
            do{
                Scanner sc = new Scanner(System.in);
                System.out.println("1. Register Student for Event");
                System.out.println("2. View All Students Event-wise");
                System.out.println("3. View All Students Student-wise");
                System.out.println("4. Check Event Capacity");
                System.out.println("5. Delete Registration using Registration ID");
                System.out.println("6. Update Registration :change Student or event");
                System.out.println("7. Download Registration Report pdf ");
                System.out.println("Enter your choice ");
                int choice = sc.nextInt();
                switch (choice){
                    case 1:
                        sc.nextLine();
                        System.out.println("Enter the event_id ,student_id and date for registration");
                        int eid = sc.nextInt();
                        int sid = sc.nextInt();
                        RegModel regModel = new RegModel();
                        regModel.setEid(eid);
                        regModel.setSid(sid);
                        boolean b = ServiceHelper.regHelper.RegisterStudent(regModel);
                        if(b){
                            System.out.println("Student Registerd Successfully");
                        }else{
                            System.out.println("Student Did not registered");
                        }

                        break;

                    case 2:
                        sc.nextLine();
                        List<Object[]> obj = ServiceHelper.regHelper.getRegistrationEventWise();

                        for(Object[] o:obj){
                            System.out.println(Arrays.toString(o));
                        }

                    break;
                    case 3:
                        sc.nextLine();
                        List<Object[]> obj1 = ServiceHelper.regHelper.getRegistrationStudentWise();

                        for(Object[] o:obj1){
                            System.out.println(Arrays.toString(o));
                        }

                    break;

                    case 4:
                        sc.nextLine();
                        RegModel regModel1 = new RegModel();
                        System.out.println("Enter the event id to check the capacity");
                        int id = sc.nextInt();
                        regModel1.setEid(id);
                        boolean available = ServiceHelper.regHelper.CheckEventCapacity(regModel1);
                        if (available) {
                            System.out.println("Seats available! You can register.");
                        } else {
                            System.out.println("Event is full!");
                        }

                        break;

                    case 5:
                        sc.nextLine();
                        System.out.println("Enter an Registration id to be deleted");
                        int did = sc.nextInt();
                        boolean bd =ServiceHelper.regHelper.DeleteUsingRegId(did);
                        if(bd){
                            System.out.println("Deleted Successfully");
                        }
                        else{
                            System.out.println("Delete not Happens");
                        }

                        break;

                    case 6:
                        sc.nextLine();
                        System.out.println("Enter an reg_id and event_id for the event changed");
                        int rid = sc.nextInt();
                        int esid = sc.nextInt();

                        RegModel regModel2 = new RegModel();
                        regModel2.setReg_id(rid);
                        regModel2.setEid(esid);
                        boolean bss = ServiceHelper.regHelper.UpdateStudentEvent(regModel2);
                        if (bss){
                            System.out.println("Events are updated to a student ");
                        }
                        else{
                            System.out.println("Events are not updated to a student");
                        }
                        break;

                    case 7:
                        System.out.println("Registration Report in pdf are stored in your desktop");
                        List<Object[]> obs = ServiceHelper.regHelper.getRegistrationEventWise();
                        for(Object[] o:obs){
                            System.out.println(Arrays.toString(o));
                        }
                        ServiceHelper.regHelper.exportsStudentToPDF(obs,"C:\\Users\\ACER\\Desktop\\registration.pdf");





                        break;

                    default:
                        System.out.println("Invalid choice");
                }

            }while (true);
    }
}
