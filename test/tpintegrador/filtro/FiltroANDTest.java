package tpintegrador.filtro;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpintegrador.muestra.Muestra;

class FiltroANDTest {
	private Filtro filtro1;
	private Filtro filtro2;
	private Muestra muestra;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		filtro1 = mock(Filtro.class);
		filtro2 = mock(Filtro.class);
		muestra = mock(Muestra.class);
	}

	@Test
	public void testFiltroANDTrueSiAmbasCumplen() {
		when(filtro1.cumple(muestra)).thenReturn(true);
		when(filtro2.cumple(muestra)).thenReturn(true);
		
		Filtro FiltroAND = new FiltroAND(filtro1, filtro2);
		assertTrue(FiltroAND.cumple(muestra));

	}

	@Test
	public void testFiltroANDRetornaFalseNoSeCumpleNinguna() {
		when(filtro1.cumple(muestra)).thenReturn(false);
		when(filtro2.cumple(muestra)).thenReturn(false);
		
		Filtro FiltroAND = new FiltroAND(filtro1, filtro2);
		assertFalse(FiltroAND.cumple(muestra));
	}
	
	@Test
	public void testFiltroANDRetornaFalseSeCumpleUnaYOtroNo() {
		when(filtro1.cumple(muestra)).thenReturn(true);
		when(filtro2.cumple(muestra)).thenReturn(false);
		
		Filtro FiltroAND = new FiltroAND(filtro1, filtro2);
		assertFalse(FiltroAND.cumple(muestra));
	}
	@Test
	public void testFiltroANDConUnSoloFiltroTrue() {
		when(filtro1.cumple(muestra)).thenReturn(true);
		
		Filtro FiltroAND = new FiltroAND(filtro1);
		assertTrue(FiltroAND.cumple(muestra));
	}
	
	@Test
	public void testFiltroANDConUnSoloFiltroFalse() {
		when(filtro1.cumple(muestra)).thenReturn(false);
		
		Filtro FiltroAND = new FiltroAND(filtro1);
		assertFalse(FiltroAND.cumple(muestra));
	
	}
}