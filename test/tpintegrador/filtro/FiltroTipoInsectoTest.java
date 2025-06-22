package tpintegrador.filtro;

import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import tpintegrador.muestra.Muestra;
import tpintegrador.opinion.TipoDeOpinion;

class FiltroTipoInsectoTest {
	private Muestra muestra;
	
	@BeforeEach
	public void setUp() throws Exception {
		muestra = mock(Muestra.class);
	}

	@Test
	public void testCumpleCoincidentElTipo() {
		when(muestra.getResultadoActual()).thenReturn(TipoDeOpinion.VINCHUCA_INFESTAN);
		
		Filtro filtro = new FiltroTipoInsecto(TipoDeOpinion.VINCHUCA_INFESTAN);
		
		assertTrue(filtro.cumple(muestra));
		verify(muestra).getResultadoActual();
	}

	@Test
	public void testNoCumpleCoincidenciaElTipo() {
		when(muestra.getResultadoActual()).thenReturn(TipoDeOpinion.NINGUNA);
		
		Filtro filtro = new FiltroTipoInsecto(TipoDeOpinion.VINCHUCA_INFESTAN);
		
		assertFalse(filtro.cumple(muestra));
		verify(muestra).getResultadoActual();
	}
}


