package tpintegrador.usuario;

public class NivelExpertoExterno extends NivelExpertise {

	@Override
	public boolean esExperto(Usuario u) {
		return true;
	}

	@Override
	public void verificarCambioNivel(Usuario u) {
		//no hace nada es experto siempre 
	}
	

}
