package tpintegrador.usuario;

import static org.junit.jupiter.api.Assertions.*; 
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import tpintegrador.muestra.Muestra;
import tpintegrador.opinion.Opinion;

class UsuarioTest {
	private Usuario usuario;
	
	
	@BeforeEach
	void setUp() throws Exception {
		usuario = new Usuario("Ian Gardella");
	
		usuario.getOpiniones().clear();
		usuario.getMuestrasEnviadas().clear();
	}

	@Test
	void testUsuarioBasicoNoEsExperto() {
		assertFalse(usuario.esExperto());
	}
	

	@Test
	void testGetterNombre() {
		assertEquals(usuario.getNombreUsuario(),"Ian Gardella");
	}
	
	@Test
	void testAgregarMuestra() {
		Muestra muestra = mock(Muestra.class);
		usuario.agregarMuestraEnviada(muestra);
		assertTrue(usuario.getMuestrasEnviadas().contains(muestra));
	}
	
	@Test
	void testAgregarOpinion() {
		Opinion opinion = mock(Opinion.class);
		usuario.agregarOpiniones(opinion);
		assertTrue(usuario.getOpiniones().contains(opinion));
	}
	
	@Test
	void testCantidadDeMuestrasEnviadasEnXDias() {
		Muestra muestra1 = mock(Muestra.class);
		when(muestra1.getFechaDeCreacion()).thenReturn(LocalDate.now());
		
		Muestra muestra2 = mock(Muestra.class);
		when(muestra2.getFechaDeCreacion()).thenReturn(LocalDate.now().minusDays(40));
	
		usuario.agregarMuestraEnviada(muestra1);
		usuario.agregarMuestraEnviada(muestra2);
	
		assertEquals(1, usuario.enviadosEnXDias(30));
	}
	
	@Test
	void testCantidadDeOpinionesEnXDias() {
		Opinion op1 = mock(Opinion.class);
		when(op1.getFechaDeOpinion()).thenReturn(LocalDate.now().minusDays(15));
		

		Opinion op2 = mock(Opinion.class);
		when(op2.getFechaDeOpinion()).thenReturn(LocalDate.now().minusDays(45));
		
		usuario.agregarOpiniones(op1);
		usuario.agregarOpiniones(op2);
		
		assertEquals(1, usuario.revisionesEnXDias(30));
	}

	@Test
	void testCambioDeNivelBasicoAExperto() {
		for (int i = 0; i < 11; i++) {
			Muestra muestra = mock(Muestra.class);
			when(muestra.getFechaDeCreacion()).thenReturn(LocalDate.now().minusDays(1));
			usuario.agregarMuestraEnviada(muestra);
		}
		
		for (int i = 0; i < 21; i++) {
			Opinion op = mock(Opinion.class);
			when(op.getFechaDeOpinion()).thenReturn(LocalDate.now().minusDays(1));
			usuario.agregarOpiniones(op);
		}
		
		assertTrue(usuario.esExperto());
	}
	
	
	@Test
	void testCambioDeNivelExpertoABasico() {
		Usuario usuario = new Usuario("Ian");

		// Primero lo promovemos a experto
		for (int i = 0; i < 11; i++) {
			Muestra m = mock(Muestra.class);
			when(m.getFechaDeCreacion()).thenReturn(LocalDate.now().minusDays(1));
			usuario.agregarMuestraEnviada(m);
		}
		for (int i = 0; i < 21; i++) {
			Opinion o = mock(Opinion.class);
			when(o.getFechaDeOpinion()).thenReturn(LocalDate.now().minusDays(1));
			usuario.agregarOpiniones(o);
		}

		assertTrue(usuario.esExperto());
		assertInstanceOf(NivelExperto.class, usuario.getNivel());

		// Simula paso del tiempo y elimina actividad reciente
		usuario.getMuestrasEnviadas().clear();
		usuario.getOpiniones().clear();

		Muestra vieja = mock(Muestra.class);
		when(vieja.getFechaDeCreacion()).thenReturn(LocalDate.now().minusDays(40));
		usuario.agregarMuestraEnviada(vieja);

		Opinion viejaOp = mock(Opinion.class);
		when(viejaOp.getFechaDeOpinion()).thenReturn(LocalDate.now().minusDays(40));
		usuario.agregarOpiniones(viejaOp);

		assertFalse(usuario.esExperto());
		assertInstanceOf(NivelBasico.class, usuario.getNivel());
	}
	
	@Test
	void testUsuarioExpertoExterno() {
		Usuario usuario = new Usuario("Ian Profesional"); 
		usuario.setNivel(new NivelExpertoExterno());
		
		assertTrue(usuario.esExperto());
	}
}


