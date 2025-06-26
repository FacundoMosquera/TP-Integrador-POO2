package tpintegrador.aplicacionWeb;

import java.util.ArrayList;
import java.util.List;

import tpintegrador.muestra.Muestra;
import tpintegrador.zonadecobertura.ZonaDeCobertura;

public class AplicacionWeb {
	
	private List<ZonaDeCobertura> zonas;
	private List<Muestra> muestras;
	
	public AplicacionWeb() {
		zonas = new ArrayList<>();
		muestras = new ArrayList<>();
	}
	
	public void addZona(ZonaDeCobertura zona) {
		zonas.add(zona);
	}
	
	public List<ZonaDeCobertura> getZonasDeCobertura() {
		return zonas;
	}
	
	public void nuevaMuestra(Muestra m) {
		muestras.add(m);
		for (ZonaDeCobertura z : zonas) {
			z.estaDentroDeLaZona(m);
		}
	}
	
	public List<Muestra> getMuestras(){
		return muestras;
	}
	
}
