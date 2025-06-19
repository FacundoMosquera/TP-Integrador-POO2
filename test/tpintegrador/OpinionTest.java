package tpintegrador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpintegrador.opinion.Opinion;
import tpintegrador.opinion.TipoDeOpinion;
import tpintegrador.usuario.Usuario;

public class OpinionTest {

	private Opinion opinion1;
	private Opinion opinion2;
	
	private Usuario jorge;
	private Usuario raul;
	
	
	@BeforeEach
	public void setUp() {
		jorge = mock(Usuario.class);
		raul  = mock(Usuario.class);
		
		when(jorge.esExperto()).thenReturn(true);
		when(raul.esExperto()).thenReturn(false);
	}
	
	@Test
	public void testInicializarYVerificarInteraccionConUsuario() {
		opinion1 = new Opinion(TipoDeOpinion.VINCHUCA_INFESTAN, jorge);
		
		verify(jorge).esExperto();
		
	}
	
	@Test
	public void testGetterUsuario() {
		opinion1 = new Opinion(TipoDeOpinion.CHINCHE_FOLIADA, jorge);
		
		assertEquals(jorge, opinion1.getUsuario());
	}
	
	@Test
	public void testGetterTipoDeOpinion() {
		opinion2 = new Opinion(TipoDeOpinion.VINCHUCA_GUASAYANA, raul);
		
		assertEquals(TipoDeOpinion.VINCHUCA_GUASAYANA, opinion2.getTipoDeOpinion());
	}
	
	@Test 
	public void testGetterOpinionExperto() {
		opinion1 = new Opinion(TipoDeOpinion.CHINCHE_FOLIADA, jorge);
		opinion2 = new Opinion(TipoDeOpinion.VINCHUCA_GUASAYANA, raul);
		
		assertEquals(true, opinion1.esLaOpinionDeUnExperto());
		assertEquals(false, opinion2.esLaOpinionDeUnExperto());
	}
	
	@Test
	public void testGetterFecha() {
		opinion1 = new Opinion(TipoDeOpinion.CHINCHE_FOLIADA, jorge);
		opinion2 = new Opinion(TipoDeOpinion.VINCHUCA_GUASAYANA, raul);
		
		System.out.println(opinion1.getFechaDeOpinion());
		
		assertEquals(true, opinion1.getFechaDeOpinion().isEqual(opinion2.getFechaDeOpinion()));
		assertEquals(true, opinion2.getFechaDeOpinion().isEqual(LocalDate.now()));
	}
}
