package tpintegrador.filtro;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class ComparadorFechaTest {
	
	private LocalDate fecha1 = LocalDate.of(2025, 6, 20);
	private LocalDate fecha2 = LocalDate.of(2025, 6, 22);

	@Test
	public void testAntes() {
		assertTrue(ComparadorFecha.ANTES.comparar(fecha2, fecha1));
		assertFalse(ComparadorFecha.ANTES.comparar(fecha1, fecha2));
	}
	
	@Test
	public void testDespues() {
		assertTrue(ComparadorFecha.DESPUES.comparar(fecha1, fecha2));
		assertFalse(ComparadorFecha.DESPUES.comparar(fecha2, fecha1));
	}

	@Test
	public void testIgual() {
		assertTrue(ComparadorFecha.IGUAL.comparar(fecha1, fecha1));
		assertFalse(ComparadorFecha.IGUAL.comparar(fecha1, fecha2));
	}
	
	@Test
	public void testAntesOIgual() {
		 assertTrue(ComparadorFecha.ANTES_O_IGUAL.comparar(fecha2, fecha1));  
		 assertTrue(ComparadorFecha.ANTES_O_IGUAL.comparar(fecha1, fecha1));  
		 assertFalse(ComparadorFecha.ANTES_O_IGUAL.comparar(fecha1, fecha2)); 
	}
	 
	@Test
	public void testDespuesOIgual() {
		assertTrue(ComparadorFecha.DESPUES_O_IGUAL.comparar(fecha1, fecha2));  
		assertTrue(ComparadorFecha.DESPUES_O_IGUAL.comparar(fecha1, fecha1));  
		assertFalse(ComparadorFecha.DESPUES_O_IGUAL.comparar(fecha2, fecha1)); 
	}
	
	@Test
	public void testDiferente() {
		assertTrue(ComparadorFecha.DIFERENTE.comparar(fecha1, fecha2));
		assertFalse(ComparadorFecha.DIFERENTE.comparar(fecha2, fecha2));
	}
}
