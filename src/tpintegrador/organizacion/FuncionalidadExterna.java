package tpintegrador.organizacion;

import tpintegrador.muestra.Muestra;
import tpintegrador.zonadecobertura.ZonaDeCobertura;

public interface FuncionalidadExterna {
	
	public void nuevoEvento(Organizacion org, ZonaDeCobertura zona, Muestra muestra);
}
