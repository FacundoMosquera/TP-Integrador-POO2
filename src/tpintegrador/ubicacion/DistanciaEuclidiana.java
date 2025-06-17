package tpintegrador.ubicacion;


public class DistanciaEuclidiana implements FormulaDeDistancia {

	@Override
	public double distanciaEntre(Ubicacion u1, Ubicacion u2) {
		// Para calcular la distancia de forma simple se usará el teorema de pitágoras
		
		double distanciaX = u1.getLatitud() - u2.getLatitud(); // consigo el tamañao del cateto x
		double distanciaY = u1.getLongitud() - u2.getLongitud(); // consigo el tamaño del cateto y
		
		return Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY) * 111_000; // la paso de grados a metros
	}
}
