import java.util.Objects;
import java.util.Scanner;
import constants.VehicleTypes;
import constants.Commands;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to parking lot");
        // Creating parking lot
        System.out.println("Enter the number of floor, bike slots, car slots and truck slots per floor");
        Scanner scanner = new Scanner(System.in);
        int floor = scanner.nextInt();
        int bikeSlots = scanner.nextInt();
        int carSlots = scanner.nextInt();
        int truckSlots = scanner.nextInt();
        ParkingLot parkingLot = new ParkingLot(floor, bikeSlots, carSlots, truckSlots);
        System.out.println("Parking lot created");

        while (true) {
            System.out.println("Enter the command");
            String command = scanner.next();
            if (Objects.equals(command, Commands.addFloor)) {
                addFloorToParkingLot(scanner, parkingLot);
            } else if (Objects.equals(command, Commands.parkVehicle)) {
                parkVehicle(scanner, parkingLot);
            } else if (Objects.equals(command, Commands.freeSlots)) {
                getFreeSlots(scanner, parkingLot);
            } else if (Objects.equals(command, Commands.showParkVehicles)) {
                showParkVehicle(parkingLot);
            } else if (Objects.equals(command, Commands.addSlot)) {
                addSlotOnFloor(scanner, parkingLot);
            } else if (Objects.equals(command, Commands.unparkVehicle)) {
                unparkVehicle(scanner, parkingLot);
            } else if (Objects.equals(command, Commands.exit)) {
                break;
            }
        }
        System.out.println("Terminating Parking lot program");
    }

    // addFloorToParkingLot : method to execute add floor command par
    static void addFloorToParkingLot(Scanner scanner, ParkingLot parkingLot) {
        System.out.println("Enter the number of bike slots, car slots and truck slots");
        int bikeSlots = scanner.nextInt();
        int carSlots = scanner.nextInt();
        int truckSlots = scanner.nextInt();
        parkingLot.addFloorToParkingLot(bikeSlots, carSlots, truckSlots);
    }


    // parkVehicle : method to execute park vehicle command
    static void parkVehicle(Scanner scanner, ParkingLot parkingLot) {
        System.out.println("Enter the vehicle type(CAR, BIKE, TRUCK), number, color");
        String vehicleType = scanner.next();
        if (!(vehicleType.equals(VehicleTypes.vehicleTypeCar) || vehicleType.equals(VehicleTypes.vehicleTypeBike) || vehicleType.equals(VehicleTypes.vehicleTypeTruck))) {
            System.out.println("Invalid vehicle type");
            return;
        }

        String color = scanner.next();
        String vehicleNumber = scanner.next();
        int floorNumber = parkingLot.getFreeFloorForVehicle(vehicleType);
        if (floorNumber == -1) {
            System.out.println("No free slot");
            return;
        }
        int slotNumber = parkingLot.occupySlotInParkingLot(vehicleType, floorNumber);
        Vehicle vehicle = new Vehicle(vehicleType, color, vehicleNumber);
        ParkingLotTicket parkingLotTicket = new ParkingLotTicket(floorNumber, slotNumber, vehicle.getRegistrationNumber(), vehicleType);
        parkingLot.updateParkingLotTicketMapping(vehicle, parkingLotTicket);
    }

    // getFreeSlots: method to execute free slots command
    static void getFreeSlots(Scanner scanner, ParkingLot parkingLot) {
        System.out.println("Enter the vehicle type - CAR, BIKE, TRUCK");
        String vehicleType = scanner.next();
        parkingLot.getFreeSlotsForVehicle(vehicleType);
    }

    static void showParkVehicle(ParkingLot parkingLot) {
        parkingLot.printParkingLotTicketMapping();
    }

    static void addSlotOnFloor(Scanner scanner, ParkingLot parkingLot) {
        System.out.println("Enter the floor number and new slot type(CAR, BIKE, TRUCK)");
        int floorNumber = scanner.nextInt();
        String vehicleType = scanner.next();
        parkingLot.addSlotOnFloor(floorNumber, vehicleType);
    }

    static void unparkVehicle(Scanner scanner, ParkingLot parkingLot) {
        System.out.println("Enter the vehicle number");
        String vehicleNumber = scanner.next();
        if (!parkingLot.checkIfVehicleParked(vehicleNumber)) {
            System.out.println("Vehicle is not parked");
            return;
        }
        parkingLot.unOccupySlotInParkingLot(vehicleNumber);
        System.out.println("Vehicle Unoccupied");

    }
}