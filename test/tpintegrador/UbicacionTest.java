package tpintegrador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	public void testDistanciaDiferentesConDistanciaEuclidiana() throws Exception {
		UbicacionConcreta u1 = new UbicacionConcreta(-34.6, -44.4);
		UbicacionConcreta u2 = new UbicacionConcreta(69.3, -12.2);
		u2.setFormula(new DistanciaEuclidiana());
		assertEquals(12074050.192458204287701293366908, u2.distanciaA(u1));
	}
	
	@Test 
	public void testSetearNuevaDistancia() throws Exception {
		UbicacionConcreta u = new UbicacionConcreta(3.3, -23.1);
		FormulaDeDistancia f = new DistanciaEuclidiana();
		u.setFormula(f);
		
	}
	
	@Test
	public void testIniciarConLatitudInvalidaSueltaError() {
		assertThrows(Exception.class, () -> new UbicacionConcreta(-99, 20.3));
	}
	
	@Test
	public void testInicarConLongitudInvalidaSueltaError() {
		assertThrows(Exception.class, () -> new UbicacionConcreta(21.44, 220));
	}
	
	 @Test
	 public void distanciaEntreBuenosAiresYSantiago() throws Exception {
		 Ubicacion buenosAires = new UbicacionConcreta(-34.6037, -58.3816);
	     Ubicacion santiago = new UbicacionConcreta(-33.4489, -70.6693);
	        
	     double distancia = buenosAires.distanciaA(santiago) / 1000; // paso la distancia a km
	     assertTrue(distancia > 1100 && distancia < 1200);
	     
	     System.out.println(distancia);
	     System.out.println(distancia * 1000);
	 }
	 
	 @Test
	 public void distanciaEntreMismoPuntoDebeSerCero() throws Exception {
		 Ubicacion punto = new UbicacionConcreta(40.7128, -74.0060);

	     double distancia = punto.distanciaA(punto);

	     assertEquals(0.0, distancia, 0.01, "Distancia entre el mismo punto debe ser 0");
	    }
	 
	 
	 
}
