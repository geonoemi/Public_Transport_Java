import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Collator;
import java.util.*;
public class beolv {
	//trying class
	public static void getARoute() {
		//Bence tipp: app indul�skor mindent beolvasni �s majd adatszerkezeten iter�lni, nem file-on
		//in-memory kollekci�knak ut�naolvasni
		//menetrend class:teh�t egy komplett menedtrend minkdek�pp legyen egy ArrayList ami minden menetrend bejegyz�st tartalmaz ilyen adattagokkal hogy Vehicle, egy indul�s, �llom�s objektum, �rkez�s
		//mi megy a stackbe,mi a heapbe?
		//git ignore?
		
		Vehicle.readIn("classes files\\vehicles.txt");
		Scanner scanStation=new Scanner(System.in);
		String station="";
		do {
			System.out.println("Choose station: ");
			Station.readIn("classes files\\stations.txt");
			
			//Collator hu = Collator.getInstance(new Locale("hu","HU"));
			//Station.sortStationNames(hu,Station.stationNames);
			Station.printStations(Station.stationNames);
			station=scanStation.nextLine();
		}while(!Station.stationNames.contains(station));
				
		
		//stat.nextLine(); //Attila szerint 2 beolvas�s k�z� -> nem kell �j Scanner, de nem m�k�dik
			
		Scanner scanTypeOfVehicle=new Scanner(System.in);
		int typeOfVehicle;
		do{
			System.out.println("Choose from the types of vehicles: BUS=1, TRAM=2, TROLLEY=3 : ");
			typeOfVehicle=scanTypeOfVehicle.nextInt();
		}while( !(typeOfVehicle==1 || typeOfVehicle==2 || typeOfVehicle==3) );
		
		
		Scanner scanNumAndLetter=new Scanner(System.in);
		
		Vehicle.fillArrayLists();
		
		if (typeOfVehicle==1) { //buszok sz�m�t �rja ki
			System.out.println("Choose from the following buses:");
			Vehicle.printBuses();
		}
		else if (typeOfVehicle==2) { //villamosok sz�m�t �rja ki
			System.out.println("Choose from the following trams:");
			Vehicle.printTrams();
		}
		else if (typeOfVehicle==3) { //trolik sz�m�t �rja ki	
			System.out.println("Choose from the following trolleys:");			
			Vehicle.printTrolleys();
		}	
			
		String lineNumAndLetter=scanNumAndLetter.nextLine().toLowerCase();
	
		Scanner scanWay=new Scanner(System.in);
		System.out.println("Way (FORTH=1 BACK=2) : ");
		String way=" ";
		if(scanWay.nextInt()==1) {
			way="forth";
		}else {
			way="back";
		}
	
		Scanner scanDayType=new Scanner(System.in);
		System.out.println("Choose from the following daytypes: ");
		
		for (DayType dayType : DayType.values()) {
			System.out.printf( "\t%s\n", dayType.toString().replaceAll("(?<=[A-Z])\\_", " ").toLowerCase());
		}
		
		String dayType  = scanDayType.nextLine(); 
		
	   
	    Route.userChoice =dayType+" "+station +" "+lineNumAndLetter+" "+way ;
	   
		DepartureTimes.getsoff(Route.userChoice); 
		
		scanStation.close();
		scanTypeOfVehicle.close();
		scanNumAndLetter.close();
		scanWay.close();
		scanDayType.close();
	}
	
	public static void getsoff(String userChoice) {
		
		System.out.println("This route gets off at the following times from the chosen station: ");
		DepartureTimes.readInDepartureTimes("departure times");
		
	}
	public static void main(String[]args) {
		getARoute();
		//Station.printStations(Station.stationNames);
	}

}
