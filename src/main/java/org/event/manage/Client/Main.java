package org.event.manage.Client;
import org.event.manage.Helper.AdminHelper;
import org.event.manage.Helper.RegHelper;

import org.event.manage.Helper.ServiceHelper;
import org.event.manage.Helper.StudentHelper;
import org.event.manage.Model.AdminLogin;
import org.event.manage.Model.EventModel;
import org.event.manage.Service.AdminLoginService;
import org.event.manage.Service.AdminLoginServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        do{
            Scanner sc = new Scanner(System.in);
            System.out.println("1. Admin Login");
            System.out.println("2. Student Login");
            System.out.println("Enter Your choice");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Enter your username and password for Admin");
                    sc.nextLine();
                    String uname = sc.nextLine();
                    String pass = sc.nextLine();
                    AdminLogin al = new AdminLogin();
                    al.setUsername(uname);
                    al.setPassword(pass);
                    AdminLoginService adService = new AdminLoginServiceImpl();
                    boolean b = adService.isAdminLogin(al);
                    if(b){
                        System.out.println("Admin Login successfully");
                        System.out.println("******************** Admin Services Are ************************");
                        AdminHelper.startWorking();
                    }
                    else{
                        System.out.println("Invalid Login");
                    }

                break;

                case 2:
                    sc.nextLine();
                    System.out.println("Enter username and password for the student login");
                    String suname = sc.nextLine();
                    String spass = sc.nextLine();
                    AdminLogin al1 = new AdminLogin();
                    al1.setUsername(suname);
                    al1.setPassword(spass);
                    AdminLoginService adminLoginService = new AdminLoginServiceImpl();
                    boolean b1 = adminLoginService.isStudentLogin(al1);
                    if(b1){
                        System.out.println("Student Login Successfully");
                        System.out.println("******************Student Services are ***************************");
                        List<EventModel> list = ServiceHelper.eventService.ViewUpcomingEvent();
                        System.out.println("******ALL the Upcoming Events Are**************");
                        list.forEach(eventModel -> {
                            System.out.println(eventModel.getEid()+" "+eventModel.getName()+" "+eventModel.getVenue()+" "+eventModel.getEdate()+"  "+eventModel.getCapacity());
                        });
                        RegHelper.startWorking();
                    }
                break;


                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong Choice");
            }

        }while (true);


    }
}