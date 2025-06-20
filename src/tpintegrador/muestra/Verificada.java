package tpintegrador.muestra;


import tpintegrador.opinion.TipoDeOpinion;
import tpintegrador.usuario.Usuario;

public class Verificada extends EstadoMuestra {
	
	private TipoDeOpinion resultadoFinal;

	
	public Verificada(TipoDeOpinion tipo) {
		this.resultadoFinal = tipo;
	}

	@Override 
	public boolean estaVerificada() {
		return true;
	}

	@Override
	public void nuevaOpinion(Muestra muestra, TipoDeOpinion tipo, Usuario usuario) throws Exception {
		 throw new Exception("La muestra ya está verificada. No se permiten nuevas opiniones");
		
	}

	@Override
	public TipoDeOpinion getResultadoActual(Muestra muestra) {	
		return resultadoFinal;
	}
	
}
