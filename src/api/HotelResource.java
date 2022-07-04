package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class HotelResource {
    static CustomerService customerService = CustomerService.getInstance();
    static ReservationService reservationService = ReservationService.getInstance();

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public static IRoom  getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public static void  createACustomer(String email, String firstname, String lastname) {
        customerService.addCustomer(firstname, lastname, email);
    }

    public static Reservation bookARoom(String email, IRoom room, Date checkinDate, Date checkoutDate) {
        Customer customer = customerService.getCustomer(email);

        return reservationService.reserveARoom(customer, room, checkinDate, checkoutDate);
    }

    public static Collection<Reservation> getCustomerReservations(String email) {
        Customer customer = customerService.getCustomer(email);
        return reservationService.getCustomersReservations(customer);

    }

    public static Collection<IRoom> findARoom(Date checkin, Date checkout) {
        return reservationService.findRooms(checkin, checkout);
    }

}
