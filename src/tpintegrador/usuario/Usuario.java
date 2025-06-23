package tpintegrador.usuario;

import java.time.LocalDate;
import java.util.List;

import tpintegrador.muestra.Muestra;

public class Usuario {
	
	private NivelExpertise nivel;
	private String nombre;
	private List<Muestra> muestrasEnviadas;
	private List<Muestra> muestrasOpinadas;
	
	public Usuario(String nombreUsuario) {
		nombre = nombreUsuario;
		nivel = new NivelBasico();
	}

	public boolean esExperto() {
		return nivel.esExperto(this);
	}
	
	public String nombreUsuario() {
		return nombre;
	}
	
	protected void setNivel(NivelExpertise e) {
		this.nivel = e;
	}
	
	public List<Muestra> getMuestrasEnviadas(){
		return muestrasEnviadas;
	}
	
	public List<Muestra> getMuestraOpinadas(){
		return muestrasOpinadas;
	}
	
	public void agregarMuestraEnviada(Muestra m) {
		muestrasEnviadas.add(m);
	}
	
	public void agregarMuestrasOpinadas(Muestra m) {
		muestrasOpinadas.add(m);
	}
	
	public long enviadosEnXDias(Integer dias) {
		return getMuestrasEnviadas().stream()
				.filter(m -> m.getFechaDeCreacion().isAfter(LocalDate.now().minusDays(dias)))
				.count();
	}
	
	public long revisionesEnXDias(Integer dias) {
		return getMuestrasEnviadas().stream()
				.filter(m -> m.getFechaDeCreacion().isAfter(LocalDate.now().minusDays(dias)))
				.count();
	}

	
}

