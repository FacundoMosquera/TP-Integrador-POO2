package tpintegrador.usuario;


public class NivelBasico implements NivelExpertise {

	@Override
	public boolean esExperto(Usuario u) {
		return u.enviadosEnXDias(30) > 10 && u.revisionesEnXDias(30) > 20;
	}
	
	@Override
	public void verificarCambioNivel(Usuario u) {
		if(esExperto(u)) {
			u.setNivel(new NivelExperto());
		}
	}
	
	

}
