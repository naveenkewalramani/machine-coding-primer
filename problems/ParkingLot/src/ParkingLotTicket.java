public class ParkingLotTicket {
    private int floor;
    private int slot;
    private String ticketNumber;
    private String vehicleNumber;
    private String vehicleType;

    public ParkingLotTicket(int floor, int slot, String vehicleNumber, String vehicleType) {
        this.floor = floor;
        this.slot = slot;
        this.vehicleNumber = vehicleNumber;
        this.ticketNumber = buildTicketNumber(floor,slot, vehicleNumber);
        this.vehicleType = vehicleType;
        System.out.printf("Ticket created, Number: %s, Floor: %d, Slot: %d\n", this.ticketNumber, this.floor, this.slot);
    }

    public String getTicketNumber(){
        return this.ticketNumber;
    }

    public int getFloor(){
        return this.floor;
    }

    public int getSlot(){
        return this.slot;
    }

    public String getVehicleType(){
        return this.vehicleType;
    }

    private String buildTicketNumber(int floor, int slot, String vehicleNumber){
        StringBuilder sb = new StringBuilder();
        sb.append(vehicleNumber).append("_").append(floor).append("_").append(slot);
        return sb.toString();
    }
}
