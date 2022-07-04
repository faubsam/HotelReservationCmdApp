package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static List<Reservation> reservations = new ArrayList<>();
    private static HashMap<String, IRoom> rooms = new HashMap<>();
    private static ReservationService reservationService = null;

    public static ReservationService getInstance() {
        if (reservationService == null) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public static void addRoom(IRoom room) {
        if (rooms.containsKey(room.getRoomNumber())) {
            System.out.println("A room with this number already exists");
        } else {
            rooms.put(room.getRoomNumber(), room);
        }
    }

    public static IRoom getARoom(String roomId) {
        /*for(IRoom room: rooms.values()) {
            if (room.getRoomNumber().equals(roomId)){
                return room;
            }
        }*/
        if(rooms.containsKey(roomId)) {
            return rooms.get(roomId);
        }
        return null;
    }

    public static Collection<IRoom> showAllRooms() {
        return rooms.values();
    }
    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkinDate, Date checkoutDate) {
        Reservation res = new Reservation(customer, room, checkinDate, checkoutDate);
        reservations.add(res);
        return res;
    }

    public static Collection<IRoom> findRooms(Date checkinDate, Date checkoutDate) {
        List<IRoom> availableRooms = new ArrayList<IRoom>(rooms.values());
        for(Reservation res: reservations) {
            if(res.getCheckin().equals(checkinDate) || res.getCheckout().equals(checkoutDate)) {
                availableRooms.remove(res.getRoom());
            } else if (res.getCheckin().before(checkinDate) && res.getCheckout().after(checkoutDate)) {
                availableRooms.remove(res.getRoom());
            } else if(res.getCheckin().after(checkinDate) && res.getCheckout().before(checkoutDate)) {
                availableRooms.remove(res.getRoom());
            } else if(res.getCheckin().before(checkinDate) && res.getCheckout().after(checkinDate)) {
                availableRooms.remove(res.getRoom());
            } else if(res.getCheckin().before(checkoutDate) && res.getCheckout().after(checkoutDate)) {
                availableRooms.remove(res.getRoom());
            } else {
                continue;
            }
        }
        return availableRooms;
    }

    public static Collection<Reservation> getCustomersReservations(Customer customer) {
        List<Reservation> customerReservations = new ArrayList<>();
        try {
            for (Reservation res : reservations) {
                if (res.getCustomer().getEmail().equals(customer.getEmail())) {
                    customerReservations.add(res);
                }
            }
        } catch (NullPointerException e) {

            return null;
        }

        return customerReservations;
    }

    public static void printAllReservations() {
        showReservations();
    }

    static void showReservations() {
        for(Reservation res: reservations) {
            System.out.println(res);
        }
    }
}
