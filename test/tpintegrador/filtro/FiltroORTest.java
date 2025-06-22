package tpintegrador.filtro;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpintegrador.muestra.Muestra;

class FiltroORTest {
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
	public void testFiltroORTrueSiCumpleUna() {
		when(filtro1.cumple(muestra)).thenReturn(false);
		when(filtro2.cumple(muestra)).thenReturn(true);
		
		Filtro filtroOR = new FiltroOR(filtro1, filtro2);
		assertTrue(filtroOR.cumple(muestra));

	}

	@Test
	public void testFiltroORRetornaFalseNoSeCumpleNinguna() {
		when(filtro1.cumple(muestra)).thenReturn(false);
		when(filtro2.cumple(muestra)).thenReturn(false);
		
		Filtro filtroOR = new FiltroOR(filtro1, filtro2);
		assertFalse(filtroOR.cumple(muestra));
	}
	
	@Test
	public void testFiltroORConUnSoloFiltroTrue() {
		when(filtro1.cumple(muestra)).thenReturn(true);
		
		Filtro filtroOR = new FiltroOR(filtro1);
		assertTrue(filtroOR.cumple(muestra));
	}
	
	@Test
	public void testFiltroORConUnSoloFiltroFalse() {
		when(filtro1.cumple(muestra)).thenReturn(false);
		
		Filtro filtroOR = new FiltroOR(filtro1);
		assertFalse(filtroOR.cumple(muestra));
	
	}
}
