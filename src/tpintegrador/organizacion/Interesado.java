package tpintegrador.organizacion;

import tpintegrador.Evento;
import tpintegrador.muestra.Muestra;
import tpintegrador.zonadecobertura.ZonaDeCobertura;

public interface Interesado {
	
	public void update(ZonaDeCobertura zona, Evento evento, Muestra muestra);
}
