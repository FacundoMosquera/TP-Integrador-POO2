package tpintegrador.muestra;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpintegrador.Evento;
import tpintegrador.Foto;
import tpintegrador.Interesado;
import tpintegrador.opinion.TipoDeOpinion;
import tpintegrador.ubicacion.Ubicacion;
import tpintegrador.ubicacion.UbicacionConcreta;
import tpintegrador.usuario.Usuario;

public class MuestraTest {

	private Muestra muestraInicializacion;
	private Muestra muestraBasica;
	private Foto foto;
	private Ubicacion ubicacion;
	private Usuario autor;
	private Usuario userBasico;
	private Usuario userExperto1;
	private Usuario userExperto2;
	
	@BeforeEach
	public void setUp() throws Exception {
		autor        = mock(Usuario.class);
		foto         = mock(Foto.class);
		ubicacion    = new UbicacionConcreta(-40d, 3d);
		userBasico   = mock(Usuario.class);
		userExperto1 = mock(Usuario.class);
		userExperto2 = mock(Usuario.class);
		
		when(userExperto1.esExperto()).thenReturn(true);
		when(userExperto2.esExperto()).thenReturn(true);
		when(userBasico.esExperto()).thenReturn(false);
		
		muestraBasica = new Muestra(foto, TipoDeOpinion.VINCHUCA_GUASAYANA, userBasico, ubicacion);
	}
	
	@Test
	public void testInicializacionUsuarioBasico() throws Exception {
		when(autor.esExperto()).thenReturn(false);
		
		muestraInicializacion = new Muestra(foto, TipoDeOpinion.CHINCHE_FOLIADA, autor, ubicacion);
		
		verify(autor, times(1)).agregarMuestraEnviada(muestraInicializacion);
		assertEquals(1, muestraInicializacion.getOpiniones().size()); //Arranca con una opinión
		assertEquals(autor, muestraInicializacion.getOpiniones().get(0).getUsuario()); // Esa opinión es la del autor
	}
	
	@Test
	public void testInicializacionUsuarioExperto() throws Exception {
		when(autor.esExperto()).thenReturn(true);
		
		muestraInicializacion = new Muestra(foto, TipoDeOpinion.CHINCHE_FOLIADA, autor, ubicacion);
		
		assertEquals(1, muestraInicializacion.getOpiniones().size()); //Arranca con una opinión
		assertEquals(autor, muestraInicializacion.getOpiniones().get(0).getUsuario()); // Esa opinión es la del autor
		
	}
	
	@Test
	public void testInicializacionUsuarioExpertoYNoDejaComentarAUsuarioBasico() throws Exception {
		when(autor.esExperto()).thenReturn(true);
		
		muestraInicializacion = new Muestra(foto, TipoDeOpinion.CHINCHE_FOLIADA, autor, ubicacion);
		
		assertThrows(Exception.class, () -> muestraInicializacion.nuevaOpinion(TipoDeOpinion.PHTIA_CHINCHE, userBasico));
	}
	
	@Test
	public void testInicializacionUsuarioExpertoYOtroUsuarioExpertoPuedeComentar() throws Exception {
		when(autor.esExperto()).thenReturn(true);
		muestraInicializacion = new Muestra(foto, TipoDeOpinion.CHINCHE_FOLIADA, autor, ubicacion);
		
		muestraInicializacion.nuevaOpinion(TipoDeOpinion.PHTIA_CHINCHE, userExperto1); 
		
		assertEquals(2, muestraInicializacion.getOpiniones().size());
		
	}
	
	@Test
	public void testLaMuestraNoDejaComentarAlPropioAutorNiComentarDosVecesALosUsuarios () throws Exception {
		Usuario userB = mock(Usuario.class);
		when(userB.esExperto()).thenReturn(false);
		muestraBasica.nuevaOpinion(TipoDeOpinion.VINCHUCA_SORDIDA, userB);
		
		assertThrows(Exception.class, () -> muestraBasica.nuevaOpinion(TipoDeOpinion.CHINCHE_FOLIADA, userBasico));
		
		assertThrows(Exception.class, () -> muestraBasica.nuevaOpinion(TipoDeOpinion.VINCHUCA_GUASAYANA, userB));
	}
	
	@Test
	public void testGetterFoto() {
		assertEquals(foto, muestraBasica.getFoto());
	}
	
	@Test
	public void testGetterLocalDate() {
		assertEquals(LocalDate.now(), muestraBasica.getFechaDeCreacion());
	}
	
	@Test
	public void testGetterUbicacion() {
		assertEquals(ubicacion, muestraBasica.getUbicacion());
	}
	
	@Test
	public void testGetterUsuario() {
		assertEquals(userBasico, muestraBasica.getAutor());
	}
	
	@Test
	public void testGetterVerificacionAMuestraAbierta() {
		assertEquals(false, muestraBasica.estaVerificada());
	}
	
	@Test
	public void testGetterVerificacionAMuestraSoloExpertos() throws Exception {
		muestraBasica.nuevaOpinion(TipoDeOpinion.CHINCHE_FOLIADA, userExperto1);
		assertEquals(false, muestraBasica.estaVerificada());
	}
	
	@Test
	public void testGetterVerificacionAMuestraVerificada() throws Exception {
		muestraBasica.nuevaOpinion(TipoDeOpinion.CHINCHE_FOLIADA, userExperto1);
		muestraBasica.nuevaOpinion(TipoDeOpinion.CHINCHE_FOLIADA, userExperto2);
		
		assertEquals(true, muestraBasica.estaVerificada());
	}
	
	@Test
	public void testSetterEstado() {
		EstadoMuestra nuevoEstado = mock(SoloExpertos.class);
		when(nuevoEstado.estaVerificada()).thenReturn(false);
		muestraBasica.setEstado(nuevoEstado);
		muestraBasica.estaVerificada();
		verify(nuevoEstado).estaVerificada();
	}
	
	@Test
	public void testGetResultadoActualConSoloOpinionDelCreador() {
		assertEquals(TipoDeOpinion.VINCHUCA_GUASAYANA, muestraBasica.getResultadoActual());
	}
	
	@Test
	public void testGetResultadoActualConEmpate() throws Exception {
		when(autor.esExperto()).thenReturn(false);
		muestraBasica.nuevaOpinion(TipoDeOpinion.VINCHUCA_SORDIDA, autor);
		assertEquals(TipoDeOpinion.NO_DEFINIDO, muestraBasica.getResultadoActual());
	}
	
	@Test
	public void testGetResultadoActualConEmpateDespuesDeQueComenteUnExpertoYSoloSuOpinionSeaTomadaEnCuenta() throws Exception {
		when(autor.esExperto()).thenReturn(false);
		muestraBasica.nuevaOpinion(TipoDeOpinion.VINCHUCA_SORDIDA, autor);
		muestraBasica.nuevaOpinion(TipoDeOpinion.IMAGEN_POCO_CLARA, userExperto1);
		
		assertEquals(TipoDeOpinion.IMAGEN_POCO_CLARA, muestraBasica.getResultadoActual());
	}
	
	@Test 
	public void testGetResultadoActualPeroConEmpateDeExpertos() throws Exception {
		muestraBasica.nuevaOpinion(TipoDeOpinion.PHTIA_CHINCHE, userExperto1);
		muestraBasica.nuevaOpinion(TipoDeOpinion.VINCHUCA_SORDIDA, userExperto2);
		
		assertEquals(TipoDeOpinion.NO_DEFINIDO, muestraBasica.getResultadoActual());
	}
	
	@Test
	public void testLaMuestraEstaVerificadaYNoDejaComentarANadieMas() throws Exception {
		muestraBasica.nuevaOpinion(TipoDeOpinion.PHTIA_CHINCHE, userExperto1);
		muestraBasica.nuevaOpinion(TipoDeOpinion.PHTIA_CHINCHE, userExperto2);
		
		Usuario basico  = mock(Usuario.class);
		Usuario experto = mock(Usuario.class);
		
		when(basico.esExperto()).thenReturn(false);
		when(experto.esExperto()).thenReturn(true);
		
		assertThrows(Exception.class, () -> muestraBasica.nuevaOpinion(TipoDeOpinion.CHINCHE_FOLIADA, experto));
		assertThrows(Exception.class, () -> muestraBasica.nuevaOpinion(TipoDeOpinion.CHINCHE_FOLIADA, basico));
		
		verifyNoInteractions(basico);
		verifyNoInteractions(experto);
	}
	
	@Test
	public void testAddInteresadoYSeLeNotificaCuandoDisparaElEvento() throws Exception {
		Interesado interesado = mock(Interesado.class);
		muestraBasica.addInteresado(interesado, Evento.VERIFICACION);
		
		muestraBasica.nuevaOpinion(TipoDeOpinion.PHTIA_CHINCHE, userExperto1);
		muestraBasica.nuevaOpinion(TipoDeOpinion.PHTIA_CHINCHE, userExperto2); //La muestra queda verificada por los expertos
		
		verify(interesado, times(1)).update(muestraBasica, Evento.VERIFICACION, muestraBasica);
	}
	
	@Test 
	public void testEliminoAlInteresadoYNoRecibeNingunaNotificacionCuandoSeDisparaElEvento() throws Exception {
		Interesado interesado = mock(Interesado.class);
		muestraBasica.addInteresado(interesado, Evento.VERIFICACION);
		muestraBasica.nuevaOpinion(TipoDeOpinion.PHTIA_CHINCHE, userExperto1);
		
		muestraBasica.removeInteresado(interesado, Evento.VERIFICACION); // Se desuscribe el interesado
		muestraBasica.nuevaOpinion(TipoDeOpinion.PHTIA_CHINCHE, userExperto2); // Se dispara el evento
		
		verifyNoInteractions(interesado);
	}
	
	@Test
	public void testAgregoUnInteresadoYEnvioElMensajeDeDesuscribirlEnOtroEventoAlQueNuncaSeSuscribioYNoAfectaLasNotificacionesDelEventoAlQueSiSeSuscribio() throws Exception {
		Interesado interesado = mock(Interesado.class);
		muestraBasica.addInteresado(interesado, Evento.VERIFICACION);       //Lo agrego al evento que le interesa
		muestraBasica.nuevaOpinion(TipoDeOpinion.PHTIA_CHINCHE, userExperto1); // La muestra pasa a soloexpertos
		assertDoesNotThrow(() -> muestraBasica.removeInteresado(interesado, Evento.NUEVA_MUESTRA));  //Le envio mensaje
		muestraBasica.nuevaOpinion(TipoDeOpinion.PHTIA_CHINCHE, userExperto2); // La muestra se verifica y dispara la notificacion
		
		verify(interesado, times(1)).update(muestraBasica, Evento.VERIFICACION, muestraBasica);
		
	}
}

