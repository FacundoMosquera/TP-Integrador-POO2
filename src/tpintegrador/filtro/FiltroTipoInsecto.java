package tpintegrador.filtro;

import tpintegrador.muestra.Muestra;
import tpintegrador.opinion.TipoDeOpinion;

public class FiltroTipoInsecto implements Filtro{
	private TipoDeOpinion tipo;
	
	public FiltroTipoInsecto(TipoDeOpinion op) {
		this.tipo = op;
	}
	
	@Override
	public boolean cumple(Muestra m) {
		return m.getResultadoActual().equals(tipo);
	}

}
