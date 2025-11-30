package org.event.manage.Helper;
import org.event.manage.Model.EventModel;
import org.event.manage.Service.EventService;
import org.event.manage.Service.EventServiceImpl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AdminHelper {
    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
    static LocalDate d;
    static public void startWorking() {
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("1. ADD NEW EVENETS");
            System.out.println("Enter you Services for Admin");
            int choice  = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:
                    System.out.println("Enter Details for an Event Creation");
                    System.out.println("Enter id,name,date,venue & capacity..............");
                    System.out.println("Enter Date");
                    String date  = sc.nextLine();

                    try{
                         d = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    }catch (Exception e){
                        System.out.println(e);
                    }

                    String name = sc.nextLine();
                    String venue = sc.nextLine();
                    int capacity = sc.nextInt();
                    EventModel ev  = new EventModel();
                    ev.setName(name);
                    ev.setEdate(d);
                    ev.setVenue(venue);
                    ev.setCapacity(capacity);
                    EventService eventService = new EventServiceImpl();
                    boolean b = eventService.addNewEvent(ev);
                    if(b){
                        System.out.println("Event Added Successfully");
                    }
                    else{
                        System.out.println("Event Does not Added ");
                    }

                break;



                default:
                    System.out.println("Invalid choice");


            }


        }while (true);
    }

}
