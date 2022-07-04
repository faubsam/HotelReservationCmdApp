package model;

public class FreeRoom extends Room{
    public FreeRoom(String roomNumber, RoomType type) {
        super(roomNumber, 0, type);
    }

    @Override
    public String toString() {
        return "Room number: " + this.getRoomNumber() + "     Price: FREE " + "     Room type: " + this.getRoomType();
    }
}
