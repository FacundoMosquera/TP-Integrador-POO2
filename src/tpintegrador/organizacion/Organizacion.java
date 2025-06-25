package tpintegrador.organizacion;

import tpintegrador.ubicacion.Ubicacion;
import tpintegrador.zonadecobertura.ZonaDeCobertura;
import tpintegrador.Evento;
import tpintegrador.muestra.Muestra;

public class Organizacion implements Interesado {
	private Ubicacion ubicacionOrg;
	private TipoDeOrganizacion tipo;
	private int cantTrabajadores;
	private FuncionalidadExterna funcionalidadNuevaMuestra;
	private FuncionalidadExterna funcionalidadVerificacion;
	
	public Ubicacion getUbicacion() {
		return ubicacionOrg;
	}
	
	public TipoDeOrganizacion getTipoOrg() {
		return tipo;
	}
	
	public int getCantTrabajadores() {
		return cantTrabajadores;
	}
	
	public FuncionalidadExterna getFuncionalidadNuevaMuestra() {
		return funcionalidadNuevaMuestra;
	}
	
	public FuncionalidadExterna getFuncionalidadVerificacion() {
		return funcionalidadVerificacion;
	}
	
	protected void setUbicacion(Ubicacion u) {
		this.ubicacionOrg = u;
	}
	
	protected void tipoDeOrganizacion(TipoDeOrganizacion t) {
		this.tipo = t;
	}
	
	protected void cantTrabajadores(int n) {
		this.cantTrabajadores = n;
	}
	
	public void setFuncionalidadNuevaMuestra(FuncionalidadExterna f) {
		this.funcionalidadNuevaMuestra = f;
	}
	
	public void setFuncionalidadVerificacion(FuncionalidadExterna f) {
		this.funcionalidadVerificacion = f;
	}
	
	@Override
	public void update(ZonaDeCobertura zona, Evento evento, Muestra muestra) {
		switch(evento) {
		case NUEVA_MUESTRA -> funcionalidadNuevaMuestra.nuevoEvento(this, zona, muestra);
		case VERIFICACION -> funcionalidadVerificacion.nuevoEvento(this, zona, muestra);
		}
	}
	
}
