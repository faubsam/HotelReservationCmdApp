import model.Customer;
import model.FreeRoom;
import model.Room;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;

public class HotelApplication {
    public static void main(String[] args) throws ParseException {
        System.out.println("Welcome to the hotel reservation application");
        System.out.println("---------------------------------------------------------------------");
        //Test data to easily verify the app's functionality.
        Customer sam = new Customer("sam", "faubert", "sam@domain.com");
        Customer michelle = new Customer("michelle", "faubert", "michelle@domain.com");

        Room room1 = new Room("1010", 120.99, RoomType.DOUBLE);
        //Room room2 = new Room("1100", 200.49, RoomType.SINGLE);
        //Room room3= new Room("909", 200.49, RoomType.DOUBLE);
        //Room room4 = new Room("451", 200.49, RoomType.SINGLE);
        //Room froom = new FreeRoom("202", RoomType.SINGLE);


        CustomerService.addCustomer("sam", "faubert", "sam@domain.com");
        CustomerService.addCustomer("michelle", "faubert", "michelle@domain.com");

        ReservationService.addRoom(room1);
        //ReservationService.addRoom(room2);
        //ReservationService.addRoom(room3);
        //ReservationService.addRoom(room4);
        //ReservationService.addRoom(froom);


        MainMenu.printMainMenu();

    }
}
