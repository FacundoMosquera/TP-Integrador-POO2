package tpintegrador.usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import tpintegrador.muestra.Muestra;
import tpintegrador.opinion.Opinion;

public class Usuario {
	
	private NivelExpertise nivel;
	private String nombre;
	private List<Muestra> muestrasEnviadas;
	private List<Opinion> opiniones;
	
	public Usuario(String nombreUsuario) {
		nombre = nombreUsuario;
		nivel = new NivelBasico();
		muestrasEnviadas = new ArrayList<Muestra>();
		opiniones = new ArrayList<Opinion>();
	}

	public boolean esExperto() {
	    nivel.verificarCambioNivel(this);
	    return nivel.esExperto(this);
	}
	
	public String getNombreUsuario() {
		return nombre;
	}
	
	public List<Muestra> getMuestrasEnviadas(){
		return muestrasEnviadas;
	}
	
	public List<Opinion> getOpiniones(){
		return opiniones;
	}
	
	protected NivelExpertise getNivel() {
		return nivel;
	}
	protected void setNivel(NivelExpertise e) {
		this.nivel = e;
	}
		
	public void agregarMuestraEnviada(Muestra m) {
		muestrasEnviadas.add(m);
	}
	
	public void agregarOpiniones(Opinion o) {
		opiniones.add(o);
	}
	
	public long enviadosEnXDias(Integer dias) {
		return getMuestrasEnviadas().stream()
				.filter(m -> m.getFechaDeCreacion().isAfter(LocalDate.now().minusDays(dias)))
				.count();
	}
	
	public long revisionesEnXDias(Integer dias) {
		return getOpiniones().stream()
				.filter(o -> o.getFechaDeOpinion().isAfter(LocalDate.now().minusDays(dias)))
				.count();
	}

	
}

