import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import service.CustomerService;
import service.ReservationService;
import api.*;

import javax.sound.midi.SysexMessage;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {
    public static void printMainMenu() throws ParseException {
        int choice = 0;
        while(choice != 5) {
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            System.out.println("Please select a number from the menu options: ");
            System.out.println("---------------------------------------------------------------------");


            try {
                Scanner scanner = new Scanner(System.in);
                choice = Integer.parseInt(String.valueOf(scanner.next()));
                System.out.println("You selected: " + choice);
            } catch (Exception e) {
                System.out.println(e + " --- Please enter a valid choice");
                choice = 0;
            }

            switch (choice) {
                case 1:
                    newReservation();
                    break;
                case 2:
                    showCustomerReservations();
                    break;
                case 3:
                    createAccount();
                    break;
                case 4:
                    AdminMenu.showAdminMenu();
                    break;
                case 5:
                    System.out.println("Exiting the application");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter a valid number from the menu");
            }
        }
    }

    public static void createAccount() throws ParseException {
        String email;
        String fname;
        String lname;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter email address: (format example@domain.com)");
        email = scanner.next();
        System.out.println("Enter first name: ");
        fname = scanner.next();
        System.out.println("Enter last name: ");
        lname = scanner.next();
        try {
            HotelResource.createACustomer(email, fname, lname);
        } catch(IllegalArgumentException e) {
            System.out.println(e + " --- Please follow the correct email address format");

        }
    }

    public static void newReservation() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        String email = null;
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        Customer customer;
        String date;
        Date checkin = null;
        Date checkout = null;
        IRoom room;
        String reserveRoom;
        String hasAccount;
        String roomNumber;



        System.out.println("Enter checkin date: (format mm-dd-yyyy) ");
        date = scanner.nextLine();
        try {
            checkin = formatter.parse(date);
        } catch (ParseException e) {
            System.out.println(e + " --- Please enter a date in the format mm-dd-yyyy");
            return;
        }
        System.out.println("Enter checkout date: (format mm-dd-yyyy) ");
        date = scanner.nextLine();
        try {
            checkout = formatter.parse(date);
        } catch (ParseException e) {
            System.out.println(e + " --- Please enter a date in the format mm-dd-yyyy");
            return;

        }

        if(!(ReservationService.findRooms(checkin, checkout)).isEmpty()) {
            for(IRoom r: HotelResource.findARoom(checkin, checkout)) {
                System.out.println(r);
            };
        } else {
            LocalDate nextCheckinWeek = checkin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(7);
            LocalDate nextCheckoutWeek = checkout.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(7);
            checkin = java.sql.Date.valueOf(nextCheckinWeek);
            checkout = java.sql.Date.valueOf(nextCheckoutWeek);

            if(!(ReservationService.findRooms(checkin, checkout)).isEmpty()) {
                System.out.println("There were no rooms available during your preferred dates. Please consider these alternatives:  Check-in: " + checkin + "   Checkout: " + checkout);
                System.out.println("Available rooms");
                System.out.println("--------------------------------------------------------");
                for(IRoom r: HotelResource.findARoom(checkin, checkout)) {
                    System.out.println(r);
                }
                System.out.println("--------------------------------------------------------");
                return;
            } else {
                System.out.println("There are no available rooms for your selected date or for the following week. Please try again.");
                System.out.println("--------------------------------------------------------");
                return;
            }
        }


        System.out.println("Would you like to reserve a room? y/n");
        reserveRoom = scanner.next();
        while(!reserveRoom.equalsIgnoreCase("y") && !reserveRoom.equalsIgnoreCase("n")) {
            System.out.println("Enter a valid option: y or n");
            reserveRoom = scanner.next();
        }
        if(reserveRoom.equalsIgnoreCase("y")) {
            System.out.println("Do you have an account with us? y/n");
            hasAccount = scanner.next();
            while(!hasAccount.equalsIgnoreCase("y") && !hasAccount.equalsIgnoreCase("n")) {
                System.out.println("Enter a valid option: y or n");
                hasAccount = scanner.next();
            }
            if (hasAccount.equalsIgnoreCase("y")) {
                System.out.println("Enter your account's email address: ");
                email = scanner.next();
            } else if(hasAccount.equalsIgnoreCase("n")) {
                System.out.println("Please create an account before making a reservation");
                return;
            }
            System.out.println("Which room would you like to reserve? Enter the room number:");
            roomNumber = scanner.next();
            try {
                int roomIntCheck = Integer.parseInt(String.valueOf(roomNumber));
                if (!HotelResource.findARoom(checkin, checkout).contains(HotelResource.getRoom(roomNumber))) {
                    System.out.println("This room is not available at your specified date.");
                    System.out.println();
                    return;
                }
                HotelResource.bookARoom(email, HotelResource.getRoom(roomNumber), checkin, checkout);
            } catch(Exception e) {
                System.out.println(e + " --- Please ensure that the information you entered is valid and follows the correct formats.");
                return;
            }
            System.out.println("Your reservation has been saved.");
        } else if(reserveRoom.equalsIgnoreCase("n")) {
            return;
        }
    }

    public static void showCustomerReservations() {
        String email;
        Customer customer;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your email address: ");
        email = scanner.next();
        System.out.println("--------------------------------------------------------");
        try {
            if (HotelResource.getCustomerReservations(email).isEmpty()) {
                System.out.println("This email address was not found in the system.");
            }
            for(Reservation res: HotelResource.getCustomerReservations(email)) {
                System.out.println(res);
                System.out.println();
            }
        } catch (NullPointerException e) {

            return;
        }
        System.out.println("--------------------------------------------------------");



    }
}
