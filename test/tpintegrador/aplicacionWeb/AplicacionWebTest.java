package tpintegrador.aplicacionWeb;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpintegrador.muestra.Muestra;
import tpintegrador.zonadecobertura.ZonaDeCobertura;
import tpintegrador.filtro.*;

class AplicacionWebTest {

	private AplicacionWeb app;
	private ZonaDeCobertura zona1;
	private ZonaDeCobertura zona2;
	private Muestra muestra;
	
	
	@BeforeEach
	public void setUp(){
		app = new AplicacionWeb();
		zona1 = mock(ZonaDeCobertura.class);
		zona2 = mock(ZonaDeCobertura.class);
		muestra = mock(Muestra.class);
	}

	@Test
	public void testAgregarZona() {
		app.addZona(zona1);
		assertTrue(app.getZonasDeCobertura().contains(zona1));
	}
	
	@Test
	public void testNuevaMuestraSeAgregaYSeDistribuyeEnZonas() {
		app.addZona(zona1);
		app.addZona(zona2);
		
		app.nuevaMuestra(muestra);
		
		assertTrue(app.getMuestras().contains(muestra));
		verify(zona1).estaDentroDeLaZona(muestra);
		verify(zona2).estaDentroDeLaZona(muestra);
	}
	
	 @Test
	    void buscadorFiltraCorrectamenteConUnFiltro() {
	        Muestra m1 = mock(Muestra.class);
	        Muestra m2 = mock(Muestra.class);
	        Filtro filtro = mock(Filtro.class);

	        when(filtro.cumple(m1)).thenReturn(true);
	        when(filtro.cumple(m2)).thenReturn(false);

	        Buscador buscador = new Buscador();
	        List<Muestra> resultados = buscador.filtrar(List.of(m1, m2), List.of(filtro));

	        assertEquals(1, resultados.size());
	        assertTrue(resultados.contains(m1));
	        assertFalse(resultados.contains(m2));
	    }

	    @Test
	    void buscadorFiltraCorrectamenteConMultiplesFiltros() {
	        Muestra m1 = mock(Muestra.class);
	        Filtro filtro1 = mock(Filtro.class);
	        Filtro filtro2 = mock(Filtro.class);

	        when(filtro1.cumple(m1)).thenReturn(true);
	        when(filtro2.cumple(m1)).thenReturn(true);

	        Buscador buscador = new Buscador();
	        List<Muestra> resultados = buscador.filtrar(List.of(m1), List.of(filtro1, filtro2));

	        assertEquals(1, resultados.size());
	        assertTrue(resultados.contains(m1));

	        when(filtro2.cumple(m1)).thenReturn(false);
	        resultados = buscador.filtrar(List.of(m1), List.of(filtro1, filtro2));

	        assertEquals(0, resultados.size());
	    }
	    
}

