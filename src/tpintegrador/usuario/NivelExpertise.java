package tpintegrador.usuario;

public interface NivelExpertise {
	
	public boolean esExperto(Usuario u);
	
	public void verificarCambioNivel(Usuario u);
}
