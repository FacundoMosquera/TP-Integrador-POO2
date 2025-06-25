package tpintegrador.usuario;

public class NivelExperto implements NivelExpertise {
    @Override
    public boolean esExperto(Usuario u) {
        return true;
    }

    @Override
    public void verificarCambioNivel(Usuario u) {
        if (!(u.enviadosEnXDias(30) > 10 && u.revisionesEnXDias(30) > 20)) {
            u.setNivel(new NivelBasico());
        }
    }
    
    
}