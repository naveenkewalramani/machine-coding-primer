# Requirements
Create a command-line application for the parking lot system with the following requirements.

The functions that the parking lot system can do:
* Create the parking lot.
* Add floors to the parking lot.
* Add a parking lot slot to any of the floors.
* Given a vehicle, it finds the first available slot, books it, creates a ticket, parks the vehicle, and finally returns the ticket.
* Unparks a vehicle given the ticket id.
* Displays the number of free slots per floor for a specific vehicle type.
* Displays all the free slots per floor for a specific vehicle type.
* Details about the Vehicles:
* Every vehicle will have a type, registration number, and color.
* Different Types of Vehicles:
  * Car
  * Bike
  * Truck
  
* Details about the Parking Slots:
  * Each type of slot can park a specific type of vehicle.
  * No other vehicle should be allowed by the system.
  * Finding the first available slot should be based on:
    * The slot should be of the same type as the vehicle.
    * The slot should be on the lowest possible floor in the parking lot.
    * The slot should have the lowest possible slot number on the floor.
    * Numbered serially from 1 to n for each floor where n is the number of parking slots on that floor.
* Details about the Parking Lot Floors:
  * Numbered serially from 1 to n where n is the number of floors.
  * Might contain one or more parking lot slots of different types.
  * We will assume that the first slot on each floor will be for a truck, the next 2 for bikes, and all the other slots for cars.
* Details about the Tickets:
  * The ticket id would be of the following format:
  <parking_lot_id>_<floor_no>_<slot_no>
  Example: PR1234_2_5 (denotes 5th slot of 2nd floor of parking lot PR1234)
* We can assume that there will only be 1 parking lot. The ID of that parking lot is PR1234.

# Expectations
* Make sure that you have a working and demonstrable code
* Make sure that the code is functionally correct
* Code should be modular and readable
* Separation of concern should be addressed
* Code should easily accommodate new requirements and minimal changes
* There should be a main method from where the code could be easily testable
* [Optional] Write unit tests, if possible
* No need to create a GUI

# Optional Requirements
Please do these only if you’ve time left. You can write your code such that these could be accommodated without changing your code much.

* Keep the code extensible to add additional types of vehicles and slot types.
* Keep the code extensible to allow a different strategy of finding the first available slot.
* Keep the code extensible to allow any other type of command.
* Keep the code extensible to allow multiple parking lots. You can assume that the input/output format can be changed for that.
* Keep the system thread-safe to allow concurrent requests.
