package tpintegrador.muestra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tpintegrador.opinion.Opinion;
import tpintegrador.opinion.TipoDeOpinion;
import tpintegrador.usuario.Usuario;

public class Abierta extends EstadoMuestra {

	
	
	public Abierta() {

	}
	
	
	@Override
	public TipoDeOpinion getResultadoActual(Muestra muestra) {
		Map<TipoDeOpinion, Integer> contadorTipo = new HashMap<>();
		
		for(Opinion o : muestra.getOpiniones()) {
			TipoDeOpinion tipo = o.getTipoDeOpinion();
			contadorTipo.put(tipo, contadorTipo.getOrDefault(tipo, 0) + 1);
		}
		
		//Consigo la opinion con la mayor cantidad de apariciones
		int max = Collections.max(contadorTipo.values());
		
		//Chequeo que no se repita la cantidad de aparicones
		List<TipoDeOpinion> tiposMax = new ArrayList<>();
		for(Map.Entry<TipoDeOpinion, Integer> entry : contadorTipo.entrySet()) {
			if(entry.getValue() == max) {
				tiposMax.add(entry.getKey());
			}
		}
		
		if(tiposMax.size() != 1) {
			return TipoDeOpinion.NO_DEFINIDO;
		}
		else {return tiposMax.get(0);}  //Al requerir la opinion del autor para crear la muestra, esta siempre tendrá una opinión.
		
		
	}


	@Override
	public void nuevaOpinion(Muestra muestra, TipoDeOpinion tipo, Usuario usuario) {
		Opinion nuevaOpinion = new Opinion(tipo, usuario);
		muestra.agregarOpinion(nuevaOpinion);
		
		if(usuario.esExperto()) {muestra.setEstado(new SoloExpertos());}
	}


	
	

}
