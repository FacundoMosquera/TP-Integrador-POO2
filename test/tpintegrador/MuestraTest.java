package tpintegrador;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpintegrador.muestra.Muestra;
import tpintegrador.opinion.TipoDeOpinion;
import tpintegrador.ubicacion.Ubicacion;
import tpintegrador.ubicacion.UbicacionConcreta;
import tpintegrador.usuario.Usuario;

public class MuestraTest {

	private Muestra muestra;
	private Foto foto;
	private Ubicacion ubicacion;
	
	@BeforeEach
	public void setUp() throws Exception {
		foto      = mock(Foto.class);
		ubicacion = new UbicacionConcreta(-40d, 3d);
	}
	
	@Test
	public void testInicializacion() {
		Usuario user = mock(Usuario.class);
		muestra = new Muestra(foto, TipoDeOpinion.CHINCHE_FOLIADA, user, ubicacion);
	}
	
}
