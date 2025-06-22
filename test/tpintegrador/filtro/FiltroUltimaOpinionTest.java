package tpintegrador.filtro;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpintegrador.muestra.Muestra;
import tpintegrador.opinion.Opinion;

class FiltroUltimaOpinionTest {

	private Muestra muestra;
	private Opinion opinion1;
	private Opinion opinion2;

	@BeforeEach
	public void setUp() throws Exception {
		muestra = mock(Muestra.class);
		opinion1 = mock(Opinion.class);
		opinion2 = mock(Opinion.class);
	}

	@Test
	public void testEsAntesCumple() {
		when(opinion1.getFechaDeOpinion()).thenReturn(LocalDate.of(2025, 6, 10));
		when(opinion2.getFechaDeOpinion()).thenReturn(LocalDate.of(2025, 6, 20));
		when(muestra.getOpiniones()).thenReturn(List.of(opinion1, opinion2));
		
		Filtro filtro = new FiltroUltimaOpinion(LocalDate.of(2025, 6, 22), ComparadorFecha.ANTES);
		
		assertTrue(filtro.cumple(muestra));
	}

	@Test
	public void testEsDespuesNoCumple() {
		when(opinion1.getFechaDeOpinion()).thenReturn(LocalDate.of(2025, 6, 10));
		when(opinion2.getFechaDeOpinion()).thenReturn(LocalDate.of(2025, 6, 20));
		when(muestra.getOpiniones()).thenReturn(List.of(opinion1, opinion2));
		
		Filtro filtro = new FiltroUltimaOpinion(LocalDate.of(2025, 6, 23), ComparadorFecha.DESPUES);
		
		assertFalse(filtro.cumple(muestra));
	}
}
