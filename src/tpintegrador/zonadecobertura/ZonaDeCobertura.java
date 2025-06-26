package tpintegrador.zonadecobertura;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tpintegrador.Evento;
import tpintegrador.Interesado;
import tpintegrador.Observable;
import tpintegrador.muestra.Muestra;
import tpintegrador.ubicacion.Ubicacion;

public class ZonaDeCobertura implements Observable, Interesado {

	private String nombre;
	private Ubicacion epicentro;
	private double radio;
	private List<Muestra> muestras;
	private Map<Evento, List<Interesado>> interesados;
	
	public ZonaDeCobertura(String nombre, Ubicacion epicentro, double radio) {
		this.nombre      = nombre;
		this.epicentro   = epicentro;
		this.radio       = radio; //El radio esta representado en metros
		this.muestras    = new ArrayList<>();	
		this.interesados = new HashMap<>();
		
	}
	
	
	//Getters
	public String getNombre() {return nombre;}
	
	public double getRadio() {return radio;}
	
	public Ubicacion getUbicacion() {return epicentro;}
	
	public List<Muestra> getMuestras() {return muestras;}
	
	
	public boolean estaDentroDeLaZona(Muestra muestra) {
		boolean dentro = epicentro.distanciaA(muestra.getUbicacion()) <= radio;
		if(dentro) {this.agregarSiNoEsta(muestra);}
		
		return dentro;
	}
	
	private void agregarSiNoEsta(Muestra m) {
		if(!muestras.contains(m)) {
			muestras.add(m);
			m.addInteresado(this, Evento.VERIFICACION);
			this.notify(this, Evento.NUEVA_MUESTRA, m);
		}
	}
	
	public boolean solapaCon(ZonaDeCobertura zona) {
		boolean solapa = epicentro.distanciaA(zona.getUbicacion()) <= this.getRadio() + zona.getRadio();
		return solapa;
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
	public void notify(Observable o, Evento e, Object dato) {
		List<Interesado> aNotificar = interesados.get(e);
		if(aNotificar != null) {          //Puede ocurrir que nunca nadie se haya suscrito al evento
			for(Interesado i : aNotificar) {
				i.update(o, e, dato);
			}
		}
		
	}


	@Override
	public void update(Observable o, Evento evento, Object dato) {
		this.notify(this, evento, o);
		
	}
}

