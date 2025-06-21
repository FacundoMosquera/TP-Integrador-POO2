package tpintegrador.muestra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tpintegrador.Evento;
import tpintegrador.Foto;
import tpintegrador.Interesado;
import tpintegrador.Observable;
import tpintegrador.opinion.Opinion;
import tpintegrador.opinion.TipoDeOpinion;
import tpintegrador.ubicacion.Ubicacion;
import tpintegrador.usuario.Usuario;


public class Muestra  implements Observable{

	private Foto foto;
	private EstadoMuestra estado;
	private Usuario autor;
	private Ubicacion ubicacion;
	private LocalDate fechaDeCreacion;
	private Map<Evento, List<Interesado>> interesados;
	private List<Opinion> opiniones;
	
	
	
	public Muestra(Foto f, TipoDeOpinion tipo, Usuario usuario, Ubicacion ubicacion) throws Exception {
		this.foto            = f;
		this.estado          = (usuario.esExperto()) ? new SoloExpertos() : new Abierta();
		this.autor           = usuario;
		this.ubicacion       = ubicacion;
		this.fechaDeCreacion = LocalDate.now();
		this.interesados     = new HashMap<>();
		this.opiniones       = new ArrayList<>();
		this.estado.nuevaOpinion(this, tipo, usuario);
		
	}

	
	// Getters simples
	public Foto getFoto() {return foto;}
	public LocalDate getFechaDeCreacion() {return fechaDeCreacion;}
	public Ubicacion getUbicacion() {return ubicacion;}
	public Usuario getAutor() {return autor;}
	public List<Opinion> getOpiniones() {return opiniones;}
	public boolean estaVerificada() {return estado.estaVerificada();}

	
	
	public void nuevaOpinion(TipoDeOpinion tOpinion, Usuario usuario) throws Exception {
		if(opiniones.stream().map(o -> o.getUsuario()).anyMatch( u -> u == usuario)) {  //Como la opinion del autor ya está contada en opiniones, también le prohibe a este volver a comentar
			throw new Exception("El usuario ya ha opinado previamente");
		} 
		else {this.estado.nuevaOpinion(this, tOpinion, usuario);}
	}
	
	

	public TipoDeOpinion getResultadoActual() {
		return this.estado.getResultadoActual(this);
	}
	
	protected void setEstado(EstadoMuestra nuevoEstado) {
		this.estado = nuevoEstado;
	}
	
	protected void agregarOpinion(Opinion o) {
		opiniones.add(o);
	}
	
	
	@Override
	public void addInteresado(Interesado i, Evento e) {
		interesados.computeIfAbsent(e, k -> new ArrayList<>()).add(i);
	}

	@Override
	public void removeInteresado(Interesado i, Evento e) {
		List<Interesado> lista = interesados.get(e);
		if(lista != null) {
			lista.remove(i);
		}
		
		
	}

	@Override
	public void notify(Observable o, Evento e) {
		List<Interesado> aNotificar = interesados.get(e); 
		if(aNotificar != null) {         //Puede ocurrir que nadie se haya registrado al evento 
			for(Interesado i : aNotificar) {
				i.update(o, e);
			}
		}
	}

}
