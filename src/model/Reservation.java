package model;

import java.util.Date;

public class Reservation {
    private Customer customer;
    private IRoom room;
    private Date checkin;
    private Date checkout;

    public Reservation(Customer customer, IRoom room, Date checkin, Date checkout) {
        this.customer = customer;
        this.room = room;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public void setRoom(IRoom room) {
        this.room = room;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "Customer: " + customer + "\nroom: " + room + "\nCheckin: " + checkin + "\nCheckout: " + checkout;
    }
}
