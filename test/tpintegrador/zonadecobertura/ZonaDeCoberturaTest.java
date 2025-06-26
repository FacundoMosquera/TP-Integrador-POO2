package tpintegrador.zonadecobertura;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpintegrador.Evento;
import tpintegrador.Foto;
import tpintegrador.muestra.Muestra;
import tpintegrador.opinion.TipoDeOpinion;
import tpintegrador.organizacion.Organizacion;
import tpintegrador.ubicacion.Ubicacion;
import tpintegrador.ubicacion.UbicacionConcreta;
import tpintegrador.usuario.Usuario;

public class ZonaDeCoberturaTest {

	private ZonaDeCobertura zona;
	private Organizacion org1;
	private Organizacion org2;
	private Muestra muestraBernal;
	private Muestra muestraCapital;
	private Ubicacion epicentro;
	private Usuario usuario;
	private Usuario comentador;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		epicentro = new UbicacionConcreta(-34.7109d, -58.2801d);
		zona = new ZonaDeCobertura("Bernal", epicentro, 1870d);
		usuario = mock(Usuario.class);
		comentador = mock(Usuario.class);
		when(comentador.esExperto()).thenReturn(true);
		when(usuario.esExperto()).thenReturn(true);
		muestraBernal = new Muestra(mock(Foto.class),TipoDeOpinion.CHINCHE_FOLIADA, usuario, new UbicacionConcreta(-34.709822d, -58.280241)); // Bernal
		muestraCapital = new Muestra(mock(Foto.class),TipoDeOpinion.CHINCHE_FOLIADA, usuario, new UbicacionConcreta(-34.6037d, -58.3816d)); // Capital Federal
		org1 = mock(Organizacion.class);
		org2 = mock(Organizacion.class);
		
	}
	
	@Test 
	public void testGetterNombre() {
		assertEquals(zona.getNombre(), "Bernal");
	}
	
	@Test
	public void testGetterRadio() {
		assertEquals(zona.getRadio(), 1870d);
	}
	
	@Test 
	public void testGetterEpicentro() {
		assertEquals(zona.getUbicacion(), epicentro);
	}
	
	@Test
	public void testLaMuestraEstaDentroDeLaZonaYEsAgregadaALaLista() throws Exception {
		assertEquals(0, zona.getMuestras().size());
		zona.estaDentroDeLaZona(muestraBernal);
		
		
		assertEquals(1, zona.getMuestras().size());
		assertEquals(muestraBernal, zona.getMuestras().get(0));
	}
	
	@Test
	public void testLaMuestraEstaFueraDeLaZonaDeCoberturaYNoEsAgregadaALaLista() throws Exception {
		assertEquals(0, zona.getMuestras().size());
		zona.estaDentroDeLaZona(muestraCapital);
		
	
		assertEquals(0, zona.getMuestras().size());
	}
	
	@Test
	public void testLaMuestraEstaDentroDeLaZonaPeroYaFueAgregadaPreviamentePorLoQueNoSeVolveraAAgregarALaLista() {
		zona.estaDentroDeLaZona(muestraBernal); // Es agregada
		zona.estaDentroDeLaZona(muestraBernal); // Se rechaza volver a agregarla
		
		assertEquals(1, zona.getMuestras().size());
	}
	
	@Test
	public void testSolapaConOtraZona() throws Exception {
		ZonaDeCobertura otraZona = new ZonaDeCobertura("Partido de Quilmes", new UbicacionConcreta(-34.7167d, -58.2667d), 6300d); 
		assertTrue(zona.solapaCon(otraZona));
	}
	
	@Test
	public void testNoSolapaConLaZonaDada() throws Exception {
		ZonaDeCobertura otraZona = new ZonaDeCobertura("Capital Federal", new UbicacionConcreta(-34.6037, -58.3816d), 8000d);
		assertFalse(zona.solapaCon(otraZona));
	}
	
	@Test
	public void testNotificaCuandoAgregaUnaNuevaMuestra() {
		zona.addInteresado(org1, Evento.NUEVA_MUESTRA);
		zona.addInteresado(org2, Evento.NUEVA_MUESTRA);
		
		zona.estaDentroDeLaZona(muestraBernal); // Dispara la notificacion
		
		verify(org1, times(1)).update(zona, Evento.NUEVA_MUESTRA, muestraBernal);
		verify(org2, times(1)).update(zona, Evento.NUEVA_MUESTRA, muestraBernal);
	}
	
	@Test 
	public void testNotificaCuandoUnaMuestraEsVerificada() throws Exception {
		zona.addInteresado(org1, Evento.VERIFICACION);
		zona.addInteresado(org2, Evento.VERIFICACION);
		
		zona.estaDentroDeLaZona(muestraBernal); //No deberia notificar a las dos organizaciones porque no se suscribieron a este evento
		verify(org1, never()).update(zona, Evento.NUEVA_MUESTRA, muestraBernal);
		verify(org2, never()).update(zona, Evento.NUEVA_MUESTRA, muestraBernal);
		
		muestraBernal.nuevaOpinion(TipoDeOpinion.CHINCHE_FOLIADA, comentador); // Esto debería disparar la notificacion de la muestra a Zona y esta debería notificar a las org
		verify(org1, times(1)).update(zona, Evento.VERIFICACION, muestraBernal);
		verify(org2, times(1)).update(zona, Evento.VERIFICACION, muestraBernal);
	}
	
	@Test 
	public void testLaOrganizacionSeDesuscribeDeUnEventoYNoEsNotificadoCuandoEsteSiDispara() throws Exception {
		zona.addInteresado(org1, Evento.NUEVA_MUESTRA);
		zona.addInteresado(org2, Evento.VERIFICACION);
		
		zona.removeInteresado(org1, Evento.NUEVA_MUESTRA);
		zona.estaDentroDeLaZona(muestraBernal);
		zona.removeInteresado(org2, Evento.VERIFICACION);
		muestraBernal.nuevaOpinion(TipoDeOpinion.CHINCHE_FOLIADA, comentador);
		
		verifyNoInteractions(org1);
		verifyNoInteractions(org2);
		
	}
}
