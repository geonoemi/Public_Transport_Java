
public class Villamosok extends Jarmu implements Elektromos{

	public Villamosok(int vonalSzam, String vonalBetu, String vonalTipus, double vonalHossz, String indulasiIdo,
			boolean csuklos, boolean alacsonyPadlos, double uzemeltetesiKoltseg, int uloHelyekSzama,
			boolean bicikliSzallitasiLehetoseg, int rokkantHelyekSzama, boolean javitasSzukseges,
			String uzemanyagTipus) {
		super(vonalSzam, vonalBetu, vonalTipus, vonalHossz, indulasiIdo, csuklos, alacsonyPadlos, uzemeltetesiKoltseg,
				uloHelyekSzama, bicikliSzallitasiLehetoseg, rokkantHelyekSzama, javitasSzukseges, uzemanyagTipus);


	}

	@Override
	public boolean haladhatItt(boolean vanEAramSzedoItt) {
		
		return true;
	}

	
}