package tpintegrador.muestra;

import tpintegrador.opinion.TipoDeOpinion;
import tpintegrador.usuario.Usuario;

public abstract class EstadoMuestra {
	
	public abstract TipoDeOpinion getResultadoActual(Muestra muestra);
	
	public boolean estaVerificada() {
		return false;
	}
	
	public abstract void nuevaOpinion(Muestra muestra, TipoDeOpinion tipo, Usuario usuario) throws Exception;
}
