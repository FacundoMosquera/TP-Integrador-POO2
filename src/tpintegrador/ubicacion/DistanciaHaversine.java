package tpintegrador.ubicacion;

public class DistanciaHaversine implements FormulaDeDistancia {

	 private static final double RADIO_TIERRA = 6_371_000; // está en metros
	
	@Override
	public double distanciaEntre(Ubicacion u1, Ubicacion u2) {
		double lat1 = Math.toRadians(u1.getLatitud());
        double lon1 = Math.toRadians(u1.getLongitud());
        double lat2 = Math.toRadians(u2.getLatitud());
        double lon2 = Math.toRadians(u2.getLongitud());

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                 + Math.cos(lat1) * Math.cos(lat2)
                 * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return RADIO_TIERRA * c;
	}
		
}
