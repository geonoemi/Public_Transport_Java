import java.io.BufferedReader;
	import java.io.FileNotFoundException;
	import java.io.FileReader;
	import java.io.IOException;
	import java.util.ArrayList;
import java.util.InputMismatchException;
	
public class Vehicle {
	//TODO make it abstract
		
		protected String lineNum;
		protected boolean articulated;
		protected boolean lowFloor;
		protected double operationCost;
		protected int numOfSeats;
		protected boolean bicycleTransportOpp;
		protected int numOfDisabledPlaces;
		protected boolean needToRepair;
		protected String typeOfFuel;
		protected boolean hasWheel;
		protected String typeOfVehicle;
		
		protected static ArrayList<String> lineNums=new ArrayList<>();
		protected static ArrayList<String> busesLineNums=new ArrayList<>();
		protected static ArrayList<String> tramsLineNums=new ArrayList<>();
		protected static ArrayList<String> trolleysLineNums=new ArrayList<>();

		protected static ArrayList<Boolean> isArticulate=new ArrayList<>();
		protected static ArrayList<Boolean> isLowFloor =new ArrayList<>();
		protected static ArrayList<Double> operationCosts=new ArrayList<>();
		protected static ArrayList<Integer> numberOfSeats =new ArrayList<>();
		protected static ArrayList<Boolean> hasBicycleTransportOpp =new ArrayList<>();
		protected static ArrayList<Integer> disabledPlaces =new ArrayList<>();
		protected static ArrayList<Boolean> needsToRepair =new ArrayList<>();
		protected static ArrayList<String> fuelTypes =new ArrayList<>();
		protected static ArrayList<Boolean> hasWheels =new ArrayList<>();
		protected static ArrayList<String> typeOfVehicles =new ArrayList<>();


		protected static ArrayList<Vehicle> vehicles=new ArrayList<>();
		protected static ArrayList<Vehicle> trolleys=new ArrayList<>();
		protected static ArrayList<Vehicle> trams=new ArrayList<>();
		protected static ArrayList<Vehicle> buses=new ArrayList<>();
		protected static ArrayList<Vehicle> wheelChairAccessibleVehicles = new ArrayList<>();
		protected static ArrayList<Vehicle> toService = new ArrayList<>();
		protected static ArrayList<Vehicle> bicycleVehicles=new ArrayList<>();
		protected static ArrayList<Vehicle> fossilVehicles=new ArrayList<>();
		protected static ArrayList<Vehicle> electricVehicles=new ArrayList<>();
		
		
		public Vehicle(String lineNum, boolean articulated, boolean lowFloor, double operationCost, int numOfSeats,
						boolean bicycleTransportOpp, int numOfDisabledPlaces, boolean needToRepair,String typeOfFuel,
						boolean hasWheel, String typeOfVehicle) {
		

			this.lineNum=lineNum;
			this.articulated=articulated;
			this.lowFloor=lowFloor;
			this.operationCost=operationCost;
			this.numOfSeats=numOfSeats;
			this.bicycleTransportOpp=bicycleTransportOpp;
			this.numOfDisabledPlaces=numOfDisabledPlaces;
			this.needToRepair=needToRepair;
			this.typeOfFuel=typeOfFuel;
			this.hasWheel=hasWheel;
			this.typeOfVehicle=typeOfVehicle;
		
		}
		
		public static void readIn(String fileName){ 
			
			try {
				
				FileReader reader=new FileReader(fileName);
				BufferedReader buffer=new BufferedReader(reader);
				String line=null;
				int i=0;
				
				while((line=buffer.readLine())!=null) {
					
						String parts[] = line.split(",");
						
						lineNums.add(parts[0]);
						isArticulate.add(Boolean.valueOf(parts[1]));
						isLowFloor.add(Boolean.parseBoolean(parts[2]));
						operationCosts.add(Double.parseDouble(parts[3]));
						numberOfSeats.add(Integer.parseInt(parts[4]));
						hasBicycleTransportOpp.add(Boolean.parseBoolean(parts[5]));
						disabledPlaces.add(Integer.parseInt(parts[6]));
						needsToRepair.add(Boolean.parseBoolean(parts[7]));
						fuelTypes.add(parts[8]);
						hasWheels.add(Boolean.parseBoolean(parts[9]));
						typeOfVehicles.add(parts[10]);
										
						Vehicle vehicle=new Vehicle(lineNums.get(i), isArticulate.get(i), isLowFloor.get(i), operationCosts.get(i),  numberOfSeats.get(i),
													hasBicycleTransportOpp.get(i),  disabledPlaces.get(i), 	needsToRepair.get(i), fuelTypes.get(i), 
													hasWheels.get(i), typeOfVehicles.get(i));
						vehicles.add(vehicle);				
				
					i++;
				}
				
				buffer.close();
				
			}catch(FileNotFoundException e) {
				System.out.println("File not found.");
			}catch(IOException e) {
				System.out.println("e.getMessage()");
			}catch (InputMismatchException exception) {
				System.out.println("Not appropriate input type.");
			}		
		}
		
		public static void fillWheelChairAccessibleVehicles() {
			
			for (Vehicle vehicle : vehicles) {
				if(vehicle.lowFloor && vehicle.numOfDisabledPlaces>=1) {
				 	
					Vehicle wheelchairAccessibleVehicle=new WheelChairAccessible(vehicle.lineNum,  vehicle.articulated, vehicle.lowFloor,
				 			vehicle.operationCost, vehicle.numOfSeats, vehicle.bicycleTransportOpp, vehicle.numOfDisabledPlaces,
				 			vehicle.needToRepair, vehicle.typeOfFuel, vehicle.hasWheel, vehicle.typeOfVehicle);

							wheelChairAccessibleVehicles.add((Vehicle) wheelchairAccessibleVehicle);
				}
			}
		}
		
		public static void fillNeedToRepairVehicles() {
				
				for (Vehicle vehicle : vehicles) {
					if(vehicle.needToRepair) {
						toService.add(vehicle);
					}	
				}
		}	
		
		public static void fillBicycleVehicles() {
			
			for (Vehicle vehicle : vehicles) {
				if(vehicle.bicycleTransportOpp) {
						bicycleVehicles.add(vehicle);
				}
			}
		}
		
		public static void fillFossilVehicles() {
			
			for (Vehicle vehicle : vehicles) {
				if(vehicle.typeOfFuel.equals("benzin") || vehicle.typeOfFuel.equals("gázolaj")) {
					fossilVehicles.add(vehicle);
				}
			}
		}
		
		public static void fillElectricVehicles() {
			
			for (Vehicle vehicle : vehicles) {
				if(vehicle.typeOfFuel.equals("electric energy")){
					electricVehicles.add(vehicle);
				}
			}
		}

		
		
		public static void fillVehicles() { //used in Route.getARoute() - feltölti a különböző járműveket tartalmazó tömblistákat
			
			for(Vehicle vehicle: vehicles) {
				
				if(vehicle.typeOfFuel.equals("electrical energy") && vehicle.hasWheel) {
					trolleys.add(vehicle);
				}

				if(vehicle.typeOfFuel.equals("electrical energy") && !vehicle.hasWheel) {
					trams.add(vehicle);
				}
			
				if(vehicle.typeOfFuel.equals("petrol") || vehicle.typeOfFuel.equals("diesel oil") && vehicle.hasWheel) {
					buses.add( vehicle);
				}	
			}
		}
		
		public static void fillBusLineNums() { //used in printBusLineNums()
			//fillVehicles();
			for(Vehicle buses:buses) {
				busesLineNums.add(buses.lineNum);
			}
		}
		
		public static void printBusLineNums() { //used in Route.getARoute
			
			busesLineNums.clear();
			fillBusLineNums();
			for(String ln: busesLineNums) {
				System.out.println(ln);
			}
		}
		
		public static void fillTramLineNums() { //used in printTramLineNums()
			//fillVehicles();
			for(Vehicle tram:trams) {
				tramsLineNums.add(tram.lineNum);
			}
		}
		
		public static void printTramLineNums() { //used in Route.getARoute()
			
			tramsLineNums.clear();
			fillTramLineNums();
			for(String ln: tramsLineNums) {
				System.out.println(ln);
			}
		}
		
		public static void fillTrolleyLineNums() { //used in printTrolleyNums
			//fillVehicles();
			for(Vehicle trolley:trolleys) {
				trolleysLineNums.add(trolley.lineNum);
			}
		}
		
		public static void printTrolleyLineNums() { //used in Route.getARoute()
			
			trolleysLineNums.clear();
			fillTrolleyLineNums();
			for(String ln: trolleysLineNums) {
				System.out.println(ln);
			}
		}		
	
		public  String toString() {
			
			return lineNum+" "+articulated+" "+ lowFloor+" "+  operationCost
					+" "+numOfSeats+" "+	bicycleTransportOpp+" "+numOfDisabledPlaces
					+" "+needToRepair+" "+typeOfFuel+" "+hasWheel+" "+typeOfVehicle;
		}
}
