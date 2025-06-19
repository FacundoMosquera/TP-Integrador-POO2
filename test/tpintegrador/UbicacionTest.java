package tpintegrador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import tpintegrador.ubicacion.*;

public class UbicacionTest {
	
	//Latitud válida es entre -90 y 90
	//Longitud válida es entre -180 y 180

	@Test
	public void testConstuctorYGetters() throws Exception {
		UbicacionConcreta u = new UbicacionConcreta(-34.6, -44.4);
		assertEquals(u.getLatitud(), -34.6);
		assertEquals(u.getLongitud(), -44.4);
	}
	
	@Test
	public void testDistanciaMismaUbicacion() throws Exception {
		UbicacionConcreta u1 = new UbicacionConcreta(-34.6, -44.4);
		UbicacionConcreta u2 = new UbicacionConcreta(-34.6, -44.4);
		assertEquals(0.0, u1.distanciaA(u2));
	}
	
	@Test
	public void testDistanciaDiferentes() throws Exception {
		UbicacionConcreta u1 = new UbicacionConcreta(-34.6, -44.4);
		UbicacionConcreta u2 = new UbicacionConcreta(69.3, -12.2);
		assertEquals(12074050.192458204287701293366908, u2.distanciaA(u1));
	}
	
	@Test 
	public void testSetearNuevaDistancia() throws Exception {
		UbicacionConcreta u = new UbicacionConcreta(3.3, -23.1);
		FormulaDeDistancia f = new DistanciaEuclidiana();
		u.setFormula(f);
		//test
	}
	
	@Test
	public void testIniciarConLatitudInvalidaSueltaError() {
		assertThrows(Exception.class, () -> new UbicacionConcreta(-99, 20.3));
	}
	
	@Test
	public void testInicarConLongitudInvalidaSueltaError() {
		assertThrows(Exception.class, () -> new UbicacionConcreta(21.44, 220));
	}
}
