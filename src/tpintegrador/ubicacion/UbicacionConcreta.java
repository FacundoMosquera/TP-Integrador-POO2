package tpintegrador.ubicacion;


public class UbicacionConcreta implements Ubicacion {

	private double latitud;
	private double longitud;
	private FormulaDeDistancia formula;
	
	public UbicacionConcreta(double latitud, double longitud) throws Exception {
		if(latitud < -90 || latitud > 90) {
			throw new Exception("La latitud debe estar entre -90° y 90°");
		}
		if (longitud < -180 || longitud > 180) {
			throw new Exception("La longitud debe estar entre -180° y 180°");
		}
		this.latitud  = latitud;
		this.longitud = longitud;
		this.formula  = new DistanciaEuclidiana(); 
	}
	
	@Override
	public double distanciaA(Ubicacion u) {
		return this.formula.distanciaEntre(this, u);
	}
	
	@Override
	public double getLatitud() {
		return latitud;
	}
	
	@Override
	public double getLongitud() {
		return longitud;
	}
	
	public void setFormula(FormulaDeDistancia f) {
		this.formula = f;
	}
}
