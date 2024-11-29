import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import constants.VehicleTypes;

public class ParkingLot {
    private int totalFloors;
    private ParkingLotFloor[] parkingLotFloorList;
    private Map<String, ParkingLotTicket> vehicleNumberToTicketMap;

    // ParkingLot : method to create parking lot for given floors and slots
    public ParkingLot(int floors, int bikeSlots, int carSlots, int truckSlots) {
        this.totalFloors = floors;
        this.parkingLotFloorList = new ParkingLotFloor[floors];
        this.vehicleNumberToTicketMap = new HashMap<>();
        for (int i = 0; i < floors; i++) {
            ParkingLotFloor parkingLotFloor = new ParkingLotFloor(i, bikeSlots, carSlots, truckSlots);
            this.parkingLotFloorList[i] = parkingLotFloor;
        }
    }

    // addFloorToParkingLot : method to increase floor count
    void addFloorToParkingLot(int bikeSlots, int carSlots, int truckSlots) {
        this.totalFloors++;
        ParkingLotFloor[] newParkingLotFloorList = new ParkingLotFloor[this.totalFloors];
        if (this.totalFloors - 1 >= 0)
            System.arraycopy(this.parkingLotFloorList, 0, newParkingLotFloorList, 0, this.totalFloors - 1);
        ParkingLotFloor parkingLotFloor = new ParkingLotFloor(this.totalFloors, bikeSlots, carSlots, truckSlots);
        newParkingLotFloorList[this.totalFloors - 1] = parkingLotFloor;
        this.parkingLotFloorList = newParkingLotFloorList;
    }

    // getFreeFloorForVehicle : method to get first free floor for parking
    public int getFreeFloorForVehicle(String type) {
        int floorNumber = -1;
        for (int i = 0; i < this.totalFloors; i++) {
            if ((Objects.equals(type, VehicleTypes.vehicleTypeBike) && this.parkingLotFloorList[i].freeBikeSlots() > 0) ||
                    (Objects.equals(type, VehicleTypes.vehicleTypeCar) && this.parkingLotFloorList[i].freeCarSlots() > 0) ||
                    (Objects.equals(type, VehicleTypes.vehicleTypeTruck) && this.parkingLotFloorList[i].freeTruckSlots() > 0)) {
                floorNumber = i;
                break;
            }
        }
        return floorNumber;
    }

    // occupySlotOnFloor: method to occupy slot.
    public int occupySlotInParkingLot(String vehicleType, int floorNumber) {
        return switch (vehicleType) {
            case VehicleTypes.vehicleTypeBike -> this.parkingLotFloorList[floorNumber].occupyBikeSlot();
            case VehicleTypes.vehicleTypeCar -> this.parkingLotFloorList[floorNumber].occupyCarSlot();
            case VehicleTypes.vehicleTypeTruck -> this.parkingLotFloorList[floorNumber].occupyTruckSlot();
            default -> -1;
        };
    }

    public void getFreeSlotsForVehicle(String vehicleType) {
        for (int i = 0; i < this.totalFloors; i++) {
            switch (vehicleType) {
                case VehicleTypes.vehicleTypeBike:
                    System.out.printf("Floor Number: %d, Free Slots: %d \n", i, this.parkingLotFloorList[i].freeBikeSlots());
                    break;
                case VehicleTypes.vehicleTypeCar:
                    System.out.printf("Floor Number: %d, Free Slots: %d \n", i, this.parkingLotFloorList[i].freeCarSlots());
                    break;
                case VehicleTypes.vehicleTypeTruck:
                    System.out.printf("Floor Number: %d, Free Slots: %d \n", i, this.parkingLotFloorList[i].freeTruckSlots());
                    break;
                default:
                    System.out.println("Vehicle Type not recognized");
                    break;
            }
        }
    }

    public void updateParkingLotTicketMapping(Vehicle vehicle, ParkingLotTicket parkingLotTicket) {
        this.vehicleNumberToTicketMap.put(vehicle.getRegistrationNumber(), parkingLotTicket);
    }

    public void printParkingLotTicketMapping() {
        for (Map.Entry<String, ParkingLotTicket> entry : this.vehicleNumberToTicketMap.entrySet())
            System.out.println("Vehicle Number = " + entry.getKey() + ", Ticket Number = " + entry.getValue().getTicketNumber());
    }

    public void addSlotOnFloor(int floorNumber, String type) {
        this.parkingLotFloorList[floorNumber].addParkingSlot(type);
    }

    public boolean checkIfVehicleParked(String vehicleNumber) {
        return this.vehicleNumberToTicketMap.containsKey(vehicleNumber);
    }

    public void unOccupySlotInParkingLot(String vehicleNumber) {
        ParkingLotTicket parkingLotTicket = this.vehicleNumberToTicketMap.get(vehicleNumber);
        int floor = parkingLotTicket.getFloor();
        int slotNumber = parkingLotTicket.getSlot();
        String vehicleType = parkingLotTicket.getVehicleType();
        switch (vehicleType) {
            case VehicleTypes.vehicleTypeBike:
                this.parkingLotFloorList[floor].unOccupyBikeSlot(slotNumber);
                break;
            case VehicleTypes.vehicleTypeCar:
                this.parkingLotFloorList[floor].unOccupyCarSlot(slotNumber);
                break;
            case VehicleTypes.vehicleTypeTruck:
                this.parkingLotFloorList[floor].unOccupyTruckSlot(slotNumber);
                break;
            default:
                System.out.println("Vehicle Type not recognized");
                break;
        }
        this.vehicleNumberToTicketMap.remove(vehicleNumber);
    }

}
