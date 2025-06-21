package tpintegrador.muestra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpintegrador.Evento;
import tpintegrador.opinion.Opinion;
import tpintegrador.opinion.TipoDeOpinion;
import tpintegrador.usuario.Usuario;

public class SoloExpertosTest {

	private SoloExpertos estado;
	private Muestra muestra;
	private Usuario usuario;
	
	private List<Opinion> opinionesEmpatadas;
	private List<Opinion> opinionesConMax;
	
	private Opinion opinion1;
	private Opinion opinion2;
	
	
	@BeforeEach
	public void setUp() {
		estado  = new SoloExpertos();
		muestra = mock(Muestra.class);
		usuario = mock(Usuario.class);
		
		opinionesEmpatadas = new ArrayList<>();
		opinionesConMax    = new ArrayList<>();
		
		opinion1 = mock(Opinion.class);
		opinion2 = mock(Opinion.class);
		
		opinionesEmpatadas.addAll( Arrays.asList(opinion1, opinion2) );
		opinionesConMax.add(opinion1);
		
		
		when(opinion1.esLaOpinionDeUnExperto()).thenReturn(true);
		when(opinion1.getTipoDeOpinion()).thenReturn(TipoDeOpinion.CHINCHE_FOLIADA);
		
		when(opinion2.esLaOpinionDeUnExperto()).thenReturn(true);
		when(opinion2.getTipoDeOpinion()).thenReturn(TipoDeOpinion.IMAGEN_POCO_CLARA);
	}
	
	
	@Test
	public void testEstaVerificada() {
		assertEquals(false, estado.estaVerificada());
	}
	
	
	
	@Test
	public void testResultadoActualTeniendoSoloUnaOpinionDeExperto() {	
		when(muestra.getOpiniones()).thenReturn(opinionesConMax);
		
		assertEquals(TipoDeOpinion.CHINCHE_FOLIADA, estado.getResultadoActual(muestra));
	}
	
	@Test
	public void testResultadoActualConDiversasOpinionesEmpatadas() {
		when(muestra.getOpiniones()).thenReturn(opinionesEmpatadas);
		
		assertEquals(TipoDeOpinion.NO_DEFINIDO, estado.getResultadoActual(muestra));
	}
	
	@Test
	public void testNuevaOpinionExpertoSinCoincidencia() throws Exception {
		
		when(muestra.getOpiniones()).thenReturn(opinionesEmpatadas);
		
		when(usuario.esExperto()).thenReturn(true);
		
		estado.nuevaOpinion(muestra, TipoDeOpinion.VINCHUCA_GUASAYANA, usuario); 
		
		verify(muestra, times(1)).agregarOpinion(any(Opinion.class));
	}
	
	@Test
	public void testNuevaOpinionExpertoConCoincidencia() throws Exception {
		when(muestra.getOpiniones()).thenReturn(opinionesEmpatadas);
		
		when(usuario.esExperto()).thenReturn(true);
		
		estado.nuevaOpinion(muestra, TipoDeOpinion.CHINCHE_FOLIADA, usuario);
		
		verify(muestra, times(1)).agregarOpinion(any(Opinion.class));
		verify(muestra, times(1)).setEstado(any(Verificada.class));
		verify(muestra, times(1)).notify(muestra, Evento.VERIFICACION);
	}
	
	@Test
	public void testNuevaOpinionDeUnUsuarioBasico() throws Exception {
		when(muestra.getOpiniones()).thenReturn(opinionesEmpatadas);
		
		when(usuario.esExperto()).thenReturn(false);
		
		assertThrows(Exception.class, () -> estado.nuevaOpinion(muestra, TipoDeOpinion.CHINCHE_FOLIADA, usuario));
		
		verify(muestra, times(1)).getOpiniones();
		
	}
}
