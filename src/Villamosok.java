
public class Villamosok extends Jarmu implements Elektromos{

	
	public Villamosok(int lineNum, String lineLetter, String way, boolean articulated, boolean lowFloor, double operationCost, int numOfSeats,
			boolean bicycleTransportOpp, int numOfDisabledPlaces, boolean needToRepair,String typeOfFuel) {
		super( lineNum,  lineLetter,  way,  articulated,  lowFloor,  operationCost,  numOfSeats,
				 bicycleTransportOpp,  numOfDisabledPlaces,  needToRepair, typeOfFuel); 
		
	}

	
	@Override
	public boolean haladhatItt(boolean vanEAramSzedoItt) {
		
		return true;
	}

	public static void main(String[] args) {
		
		beolvas("villamosok.txt");
		
	}	
}
