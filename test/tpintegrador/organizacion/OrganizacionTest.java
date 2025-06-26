package tpintegrador.organizacion;

import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import tpintegrador.Evento;
import tpintegrador.muestra.*;
import tpintegrador.zonadecobertura.*;
import tpintegrador.ubicacion.*;


class OrganizacionTest {

	private Organizacion org;
	private FuncionalidadExterna funcionalidadNueva;
	private FuncionalidadExterna funcionalidadVerificada;
	private ZonaDeCobertura zona;
	private Muestra muestra;
	
	@BeforeEach
	public void setUp() {
		org = new Organizacion();
		funcionalidadNueva = mock(FuncionalidadExterna.class);
		funcionalidadVerificada = mock(FuncionalidadExterna.class);
		zona = mock(ZonaDeCobertura.class);
		muestra = mock(Muestra.class);
		
		org.setFuncionalidadNuevaMuestra(funcionalidadNueva);
		org.setFuncionalidadVerificacion(funcionalidadVerificada);
	}
	
	@Test 
	public void updateConNuevaMuestraEjecutaFuncionalidadNueva() {
		org.update(zona, Evento.NUEVA_MUESTRA, muestra);
		
		verify(funcionalidadNueva, times(1)).nuevoEvento(org, zona, muestra);
		verify(funcionalidadVerificada, never()).nuevoEvento(any(), any(), any());
	}
	
	
	@Test
	public void updateConVerificacionEjecutaFuncionalidadVerificacion() {
        org.update(zona, Evento.VERIFICACION, muestra);

        verify(funcionalidadVerificada, times(1)).nuevoEvento(org, zona, muestra);
        verify(funcionalidadNueva, never()).nuevoEvento(any(), any(), any());
    }

	
	@Test 
	public void puedeActualizarFuncionalidadesDinanimicas() {
		FuncionalidadExterna nuevaFuncion = mock(FuncionalidadExterna.class);
		org.setFuncionalidadNuevaMuestra(nuevaFuncion);
		
		org.update(zona, Evento.NUEVA_MUESTRA, muestra);
		
		verify(nuevaFuncion, times(1)).nuevoEvento(org, zona, muestra);
		verify(funcionalidadNueva, never()).nuevoEvento(any(), any(), any());
	}
	
	@Test
	public void getters() {
		Ubicacion ubicacion = mock(Ubicacion.class);
		org.setUbicacion(ubicacion);
		org.tipoDeOrganizacion(TipoDeOrganizacion.SALUD);
		org.cantTrabajadores(50);
		
		assertEquals(org.getUbicacion(), ubicacion);
		assertEquals(org.getTipoOrg(), TipoDeOrganizacion.SALUD);
		assertEquals(org.getCantTrabajadores(), 50);
	}
	
}
