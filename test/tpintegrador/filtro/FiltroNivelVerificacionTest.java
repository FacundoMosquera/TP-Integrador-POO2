package tpintegrador.filtro;

import static org.junit.jupiter.api.Assertions.*; 
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpintegrador.muestra.EstadoMuestra;
import tpintegrador.muestra.Muestra;

class FiltroNivelVerificacionTest {
	private Muestra muestra;
	private EstadoMuestra estado;
	
	@BeforeEach
	public void setUp() throws Exception {
		muestra = mock(Muestra.class);
		estado  = mock(EstadoMuestra.class);
	}

	@Test
	public void testCumpleMuestraVerificada() {
		when(muestra.estaVerificada()).thenReturn(true);
		when(estado.estaVerificada()).thenReturn(true);
		
		Filtro filtro = new FiltroNivelVerificacion(estado);
		assertTrue(filtro.cumple(muestra));
		verify(muestra).estaVerificada();
		verify(estado).estaVerificada();
	}

	@Test
	public void testNoCumpleNivelDeVerificacion() {
		when(estado.estaVerificada()).thenReturn(false);
		when(muestra.estaVerificada()).thenReturn(true);
		
		Filtro filtro = new FiltroNivelVerificacion(estado);
		assertFalse(filtro.cumple(muestra));
		
		verify(muestra).estaVerificada();
		verify(estado).estaVerificada();
	}
}
