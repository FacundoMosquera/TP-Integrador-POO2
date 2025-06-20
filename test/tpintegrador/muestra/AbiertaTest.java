package tpintegrador.muestra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpintegrador.opinion.Opinion;
import tpintegrador.opinion.TipoDeOpinion;
import tpintegrador.usuario.Usuario;

public class AbiertaTest {

	private Abierta estado;
	private Muestra muestra;
	private Usuario usuario;
	
	private Opinion opinion1;
	private Opinion opinion2;
	private Opinion opinion3;
	
	private List<Opinion> opiniones;
	
	@BeforeEach
	public void setUp() {
		estado  = new Abierta();
		muestra = mock(Muestra.class);
		usuario = mock(Usuario.class);
		
		opinion1 = mock(Opinion.class);
		opinion2 = mock(Opinion.class);
		opinion3 = mock(Opinion.class);
		
	}

	
	@Test
	public void testEstaVerificada() {
		assertEquals(false, estado.estaVerificada());
	}
	
	
	@Test
	public void testOpinionDeUnUsuarioBasico() {
		when(usuario.esExperto()).thenReturn(false);
		estado.nuevaOpinion(muestra, TipoDeOpinion.CHINCHE_FOLIADA, usuario);
		
		verify(muestra, times(1)).agregarOpinion(any(Opinion.class));
		
		verify(muestra, never()).setEstado(any(EstadoMuestra.class));
	}
	
	
	@Test
	public void testOpinionDeUnUsuarioExperto() {
		when(usuario.esExperto()).thenReturn(true);
		estado.nuevaOpinion(muestra, TipoDeOpinion.CHINCHE_FOLIADA, usuario);
		
		verify(muestra, times(1)).agregarOpinion(any(Opinion.class));
		
		verify(muestra, times(1)).setEstado(any(SoloExpertos.class));
	}
	
	@Test
	public void testResultadoActualConUnGanador() {
		
		
		opiniones = Arrays.asList(opinion1, opinion2, opinion3);
		when(muestra.getOpiniones()).thenReturn(opiniones);
		
		when(opinion1.getTipoDeOpinion()).thenReturn(TipoDeOpinion.CHINCHE_FOLIADA);
		when(opinion2.getTipoDeOpinion()).thenReturn(TipoDeOpinion.VINCHUCA_GUASAYANA);
		when(opinion3.getTipoDeOpinion()).thenReturn(TipoDeOpinion.VINCHUCA_GUASAYANA);
		
		
		assertEquals(TipoDeOpinion.VINCHUCA_GUASAYANA, estado.getResultadoActual(muestra));
	}
	
	@Test
	public void testResultadoActualConUnEmpate() {
		
		opiniones = Arrays.asList(opinion1, opinion2, opinion3);
		when(muestra.getOpiniones()).thenReturn(opiniones);
		
		when(opinion1.getTipoDeOpinion()).thenReturn(TipoDeOpinion.CHINCHE_FOLIADA);
		when(opinion2.getTipoDeOpinion()).thenReturn(TipoDeOpinion.PHTIA_CHINCHE);
		when(opinion3.getTipoDeOpinion()).thenReturn(TipoDeOpinion.VINCHUCA_GUASAYANA);
		
		assertEquals(TipoDeOpinion.NO_DEFINIDO, estado.getResultadoActual(muestra));
	}
	
}
