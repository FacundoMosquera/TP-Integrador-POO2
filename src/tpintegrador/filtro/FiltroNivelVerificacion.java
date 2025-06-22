package tpintegrador.filtro;

import tpintegrador.muestra.EstadoMuestra;
import tpintegrador.muestra.Muestra;

public class FiltroNivelVerificacion implements Filtro{
	private EstadoMuestra estado;
	
	public FiltroNivelVerificacion(EstadoMuestra estado) {
		this.estado = estado;
	}
	@Override
	public boolean cumple(Muestra m) {
		return m.estaVerificada() == this.estado.estaVerificada();
	};

}
