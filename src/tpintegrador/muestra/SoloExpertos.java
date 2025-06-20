package tpintegrador.muestra;

import java.util.List;

import tpintegrador.opinion.Opinion;
import tpintegrador.opinion.TipoDeOpinion;
import tpintegrador.usuario.Usuario;

public class SoloExpertos extends EstadoMuestra {

	public SoloExpertos() {
		
	}
	

	@Override
	public void nuevaOpinion(Muestra muestra, TipoDeOpinion tipo, Usuario usuario) throws Exception {
		List<TipoDeOpinion> tipos = muestra.getOpiniones().stream()
				.filter(o -> o.esLaOpinionDeUnExperto())
				.map(o -> o.getTipoDeOpinion())
				.toList();
		
		if(usuario.esExperto() && tipos.contains(tipo)) {
			muestra.agregarOpinion(new Opinion(tipo, usuario));
			muestra.setEstado(new Verificada(tipo));
		}
		else if(usuario.esExperto()) { 
			muestra.agregarOpinion(new Opinion(tipo, usuario)); //Simplemente agrego a la lista porque no es una opinion repetida
		}
		else {throw new Exception("Solo pueden opinar en esta muestra los usuarios expertos");}
		
	}

	@Override
	public TipoDeOpinion getResultadoActual(Muestra muestra) {
		List<Opinion> opinionesExpertos = muestra.getOpiniones().stream()
				.filter(o -> o.esLaOpinionDeUnExperto())
				.toList();
		//Como solo se toman en cuenta las opiniones de expertos, existen únicamente dos opciones: 
		//1) Existe sólo 1 opinión de experto, la cual será el resultado actual
		//2) Hay más de una opinión de experto y ninguna coincide (ya que sino el estado sería Verificada), por lo que el resultado actual es NO_DEFINIDO
		return (opinionesExpertos.size() > 1 ? TipoDeOpinion.NO_DEFINIDO : opinionesExpertos.getFirst().getTipoDeOpinion());
	}

}
