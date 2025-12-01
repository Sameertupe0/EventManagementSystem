package org.event.manage.Client;
import org.event.manage.Helper.AdminHelper;
import org.event.manage.Model.AdminLogin;
import org.event.manage.Service.AdminLoginService;
import org.event.manage.Service.AdminLoginServiceImpl;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        do{
            Scanner sc = new Scanner(System.in);
            System.out.println("1. Admin Login");
            System.out.println("Enter Your choice");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Enter your username and password");
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
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong Choice");
            }

        }while (true);


    }
}