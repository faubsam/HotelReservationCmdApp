import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;

import javax.sound.midi.SysexMessage;
import java.text.ParseException;
import java.util.Scanner;

public class AdminMenu {
    public static void showAdminMenu() throws ParseException {
        System.out.println("1. See all customers");
        System.out.println("2. See all reservations");
        System.out.println("3. See all rooms");
        System.out.println("4. Add a room");
        System.out.println("5. Add test data");
        System.out.println("6. Back to main menu");
        System.out.println("Please select a number from the menu options: ");
        System.out.println("---------------------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        try {
            choice = Integer.parseInt(String.valueOf(scanner.next()));
            System.out.println("You selected: " + choice);
        } catch (Exception e) {
            System.out.println(e + " --- Please enter a valid choice");
        }

        switch (choice) {
            case 1:
                for(Customer customer: AdminResource.getAllCustomers()) {
                    System.out.println(customer);
                }
                break;
            case 2:
                AdminResource.displayAllReservations();
                break;
            case 3:
                for(IRoom room: AdminResource.getAllRooms()) {
                    System.out.println(room);
                };
                break;
            case 4:
                addRoom();
                String addAnotherRoom = null;
                System.out.println("Would you like to add another room? y/n");
                addAnotherRoom = scanner.next();
                while(!(addAnotherRoom.equals("y")) && !(addAnotherRoom.equals("n"))) {
                    System.out.println("Enter a valid option: y or n");
                    addAnotherRoom = scanner.next();
                }
                while(addAnotherRoom.equals("y")) {
                    addRoom();
                    System.out.println("Would you like to add another room? y/n");
                    addAnotherRoom = scanner.next();
                }
                if(addAnotherRoom.equals("n")) {
                    System.out.println("Rooms have been added");
                }

                break;
            case 5:
                System.out.println("This feature is not available at this time. ");
                MainMenu.printMainMenu();
                break;
            case 6:
                break;
            default:
                System.out.println("Please enter a valid number from the menu");
        }
    }
    public static void addRoom() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        String num;
        double price = 0;
        String type;
        RoomType roomType = null;

        System.out.println("Enter the room number: ");
        num = scanner.next();
        try {
            int roomNum = Integer.parseInt(String.valueOf(num));
        } catch (NumberFormatException e) {
            System.out.println("The room number you entered is not valid.");
            return;
        }
        System.out.println("Enter the price per night: ");
        try {
            price = Double.parseDouble(String.valueOf(scanner.next()));
        } catch (NumberFormatException e) {
            System.out.println("The price you entered is not valid.");
            return;

        }
        System.out.println("Enter the room type: 1 for single bed, 2 for double bed");
        type = scanner.next();
        if (type.equals("1")) {
            roomType = RoomType.SINGLE;
        } else if (type.equals("2")) {
            roomType = RoomType.DOUBLE;
        } else {
            System.out.println("The type you entered is not valid.");
            return;
        }
        Room room = new Room(num, price, roomType);
        ReservationService.addRoom(room);
    }

}
