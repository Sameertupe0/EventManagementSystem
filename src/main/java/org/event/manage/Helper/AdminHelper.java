package org.event.manage.Helper;
import jdk.jfr.Event;
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
    static List<EventModel> list = null;
    static public void startWorking() {
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("1. TASK FOR EVENTS ");
            System.out.println("2. TASK FOR STUDENTS");
            int option = sc.nextInt();
            switch (option){
                case 1:
                    sc.nextLine();
                    System.out.println("*****************************Event Services Are ********************************");
                    System.out.println("1. ADD NEW EVENETS");
                    System.out.println("2. VIEW ALL EVENETS");
                    System.out.println("3. VIEW ALL UPCOMING EVENETS");
                    System.out.println("4. DELETE EVENT USING EVENT ID");
                    System.out.println("5. UPDATE EVENT USING ITS NAME");
                    System.out.println("6. DOWNLOAD EVENT REPORT");
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

                        case 2:
                            System.out.println("***********List Of All The Events **************");
                            List<EventModel> list = ServiceHelper.eventService.ViewAllEvent();
                            list.forEach((model)->{
                                System.out.println(model.getEid()+" | "+model.getName()+" | "+model.getEdate()+" | "+model.getVenue()+" | "+model.getCapacity());
                            });

                            break;

                        case 3:
                            System.out.println("**************List all the Upcoming Events *********");
                            List<EventModel> list1 = ServiceHelper.eventService.ViewUpcomingEvent();
                            list1.forEach((model)->{
                                System.out.println(model.getEid()+" | "+model.getName()+" | "+model.getEdate()+" | "+model.getVenue()+" | "+model.getCapacity());
                            });

                            break;

                        case 4:
                            System.out.println("Enter an Event id to be deleted ");

                            int id = sc.nextInt();
                            //EventModel eventModel1 = new EventModel();
                            // eventModel1.setEid(id);
                            boolean result = ServiceHelper.eventService.DeleteEvent(id);
                            if(result){
                                System.out.println("Value Deleted");


                            }
                            else{
                                System.out.println("Value Did not deleted");
                            }

                            break;
                        case 5:
                            System.out.println("Enter Event name");

                            String names = sc.nextLine();
                            System.out.println("Enter Updated values of date ,venue, capacity by using name");
                            String dates  = sc.nextLine();
                            try{
                                d = LocalDate.parse(dates, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            }catch (Exception e){
                                System.out.println(e);
                            }
                            String venues = sc.nextLine();
                            int capacities = sc.nextInt();
                            EventModel ev2  = new EventModel();
                            ev2.setEdate(d);
                            ev2.setVenue(venues);
                            ev2.setName(names);
                            ev2.setCapacity(capacities);
                            boolean updatedResult = ServiceHelper.eventService.UpdateEvent(ev2);
                            if(updatedResult){
                                System.out.println("Values updated");
                            }
                            else {
                                System.out.println("Values did not updated ");
                            }

                            break;

                        case 6:
                            System.out.println("Event Report in pdf are stored in your desktop");
                            List<EventModel> listDownload  = ServiceHelper.eventService.ViewAllEvent();
                            ServiceHelper.eventService.exportsUsersToPDF(listDownload,"C:\\Users\\ACER\\Desktop\\output.pdf");


                        default:
                            System.out.println("Invalid choice");


                    }

                    break;

                case 2:

                    StudentHelper.startWorking();

                    break;

                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }



        }while (true);
    }

}
