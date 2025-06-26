package tpintegrador.usuario;

public class NivelExperto extends NivelExpertise {
    @Override
    public boolean esExperto(Usuario u) {
        return true;
    }

    @Override
    public void verificarCambioNivel(Usuario u) {
        if (!this.condicionEsExperto(u)) {
            u.setNivel(new NivelBasico());
        }
    }
    
    
}