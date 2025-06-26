package tpintegrador.usuario;

public abstract class NivelExpertise {		
		public boolean esExperto(Usuario u) {
			return false;
		};
		
		public void verificarCambioNivel(Usuario u) {
			
		}
		
		public boolean condicionEsExperto(Usuario u) {
			return u.enviadosEnXDias(30) > 10 && u.revisionesEnXDias(30) > 20;
		}

		
}
