import constants.VehicleTypes;

public class ParkingLotFloor {
    private int floorNumber;
    private  int[] bikeSlots;
    private int occupiedBikeSlots;
    private int[] carSlots;
    private int occupiedCarSlots;
    private int[] truckSlots;
    private int occupiedTruckSlots;

    public ParkingLotFloor(int floorNumber, int bikeSlot, int carSlot, int truckSlot) {
        this.floorNumber = floorNumber;
        this.bikeSlots = new int[bikeSlot];
        this.occupiedBikeSlots = 0;
        this.carSlots = new int[carSlot];
        this.occupiedCarSlots = 0;
        this.truckSlots = new int[truckSlot];
        this.occupiedTruckSlots = 0;
    }

    public int freeCarSlots() {
        return this.carSlots.length - this.occupiedCarSlots;
    }

    public int freeBikeSlots() {
        return this.bikeSlots.length - this.occupiedBikeSlots;
    }

    public int freeTruckSlots() {
        return this.truckSlots.length - this.occupiedTruckSlots;
    }

    public int occupyCarSlot() {
        int slotNumber = -1;
        boolean slotFound = false;
        for (int i = 0; i < this.carSlots.length; i++) {
            if (this.carSlots[i] == 0) {
                slotNumber = i;
                slotFound = true;
                break;
            }
        }
        if (slotFound){
            this.carSlots[slotNumber] = -1;
            this.occupiedCarSlots++;
            return slotNumber;
        }
        return -1;
    }

    public int occupyBikeSlot() {
        int slotNumber = -1;
        boolean slotFound = false;
        for (int i = 0; i < this.bikeSlots.length; i++) {
            if (this.bikeSlots[i] == 0) {
                slotNumber = i;
                slotFound = true;
                break;
            }
        }
        if (slotFound){
            this.bikeSlots[slotNumber] = -1;
            this.occupiedBikeSlots++;
            return slotNumber;
        }
        return -1;
    }

    public int occupyTruckSlot() {
        int slotNumber = -1;
        boolean slotFound = false;
        for (int i = 0; i < this.truckSlots.length; i++) {
            if (this.truckSlots[i] == 0) {
                slotNumber = i;
                slotFound = true;
                break;
            }
        }
        if (slotFound){
            this.truckSlots[slotNumber] = -1;
            this.occupiedTruckSlots++;
            return slotNumber;
        }
        return -1;
    }

    public void unOccupyCarSlot(int slotNumber) {
        this.carSlots[slotNumber] = 0;
        this.occupiedCarSlots--;
    }

    public void unOccupyBikeSlot(int slotNumber) {
        this.bikeSlots[slotNumber] = 0;
        this.occupiedBikeSlots--;
    }

    public void unOccupyTruckSlot(int slotNumber) {
        this.truckSlots[slotNumber] = 0;
        this.occupiedTruckSlots--;
    }

    public void addParkingSlot(String slotType){
       if (slotType.equals(VehicleTypes.vehicleTypeCar)){
            this.carSlots = copyArray(this.carSlots);
       }else if (slotType.equals(VehicleTypes.vehicleTypeBike)){
           this.bikeSlots = copyArray(this.bikeSlots);
       }else if (slotType.equals(VehicleTypes.vehicleTypeTruck)){
           this.truckSlots = copyArray(this.truckSlots);
       }
    }
    private int[] copyArray(int[] array){
        int[] newArray = new int[array.length+1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }
}
