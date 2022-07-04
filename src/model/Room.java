package model;

import java.util.Objects;

public class Room implements IRoom {
    private String roomNumber;
    private double price;
    private RoomType type;

    public Room(String roomNumber, double price, RoomType type){
        this.roomNumber = roomNumber;
        this.price = price;
        this.type = type;
    }
    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    @Override
    public double getRoomPrice() {
        return 0;
    }

    @Override
    public RoomType getRoomType() {
        return this.type;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        return "Room  number: " + roomNumber + "    price: " + price + "$     type: " + type;
    }

    @Override
    public boolean equals(IRoom room) {
        if(room == this) return true;
        if(!(room instanceof Room)) {
            return false;
        }
        return (roomNumber.equals(room.getRoomNumber()) && price == room.getRoomPrice() && type == room.getRoomType());

    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, price, type);
    }
}
