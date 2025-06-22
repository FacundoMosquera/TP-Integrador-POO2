package tpintegrador.filtro;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpintegrador.muestra.Muestra;

class FiltroFechaDeCreacionTest {
	
	private Muestra muestra;
	private LocalDate fechaReferencia;
	
	@BeforeEach
	public void setUp() throws Exception {
		muestra = mock(Muestra.class);
		fechaReferencia = LocalDate.of(2025, 6, 10);
	}

	@Test
	public void testFiltroAntesCumple() {
		when(muestra.getFechaDeCreacion()).thenReturn(LocalDate.of(2025, 5, 5));
		
		Filtro filtro = new FiltroFechaDeCreacion(fechaReferencia, ComparadorFecha.ANTES);
		
		assertTrue(filtro.cumple(muestra));
		verify(muestra).getFechaDeCreacion();
	}
	
	@Test
	public void testFiltroDespuesNoCumple() {
		when(muestra.getFechaDeCreacion()).thenReturn(LocalDate.of(2025, 5, 5));
		
		Filtro filtro = new FiltroFechaDeCreacion(fechaReferencia, ComparadorFecha.DESPUES);
		
		assertFalse(filtro.cumple(muestra));
		verify(muestra).getFechaDeCreacion();
	}

	@Test
	public void testFiltroIgualCumple() {
		when(muestra.getFechaDeCreacion()).thenReturn(fechaReferencia);
		
		Filtro filtro = new FiltroFechaDeCreacion(fechaReferencia,  ComparadorFecha.IGUAL);
		
		assertTrue(filtro.cumple(muestra));
		verify(muestra).getFechaDeCreacion();	
	}
}
