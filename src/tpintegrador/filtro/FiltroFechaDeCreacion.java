package tpintegrador.filtro;

import java.time.LocalDate;

import tpintegrador.muestra.Muestra;

public class FiltroFechaDeCreacion implements Filtro{
	private LocalDate fecha;
	private ComparadorFecha comparador;
	
	public FiltroFechaDeCreacion(LocalDate fecha, ComparadorFecha comparador) {
		this.fecha = fecha;
		this.comparador = comparador;
	}
	
	@Override
	public boolean cumple(Muestra m){
		return comparador.comparar(fecha, m.getFechaDeCreacion());
	};

}
