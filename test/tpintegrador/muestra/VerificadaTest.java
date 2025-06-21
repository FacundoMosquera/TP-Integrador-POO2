package tpintegrador.muestra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpintegrador.opinion.TipoDeOpinion;
import tpintegrador.usuario.Usuario;

public class VerificadaTest {

	private Verificada estado;
	private Muestra muestra;
	private Usuario usuario;
	
	
	@BeforeEach 
	public void setUp() {
		estado  = new Verificada(TipoDeOpinion.VINCHUCA_GUASAYANA);
		muestra = mock(Muestra.class);
		usuario = mock(Usuario.class);
	}
	
	
	@Test
	public void testResultadoFinal() {
		assertEquals(TipoDeOpinion.VINCHUCA_GUASAYANA, estado.getResultadoActual(muestra));
		verifyNoInteractions(muestra);
	}
	
	@Test
	public void testEstaVerificada() {
		assertEquals(true, estado.estaVerificada());
	}
	
	@Test
	public void testNuevaOpinion() {
		Exception ex = assertThrows(Exception.class, () -> estado.nuevaOpinion(muestra, TipoDeOpinion.PHTIA_CHINCHE, usuario) );
		assertEquals("La muestra ya está verificada. No se permiten nuevas opiniones", ex.getMessage());
		verifyNoInteractions(usuario);
		verifyNoInteractions(muestra);
	}
}
