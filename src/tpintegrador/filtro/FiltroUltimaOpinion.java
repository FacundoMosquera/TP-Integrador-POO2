package tpintegrador.filtro;

import java.time.LocalDate;

import tpintegrador.muestra.Muestra;
import tpintegrador.opinion.Opinion;

public class FiltroUltimaOpinion implements Filtro{
	private LocalDate fechaComparar;
	private ComparadorFecha comparador;
	
	public FiltroUltimaOpinion(LocalDate fechaComparar, ComparadorFecha f) {
		this.fechaComparar = fechaComparar;
		this.comparador = f;
	}

	@Override
	public boolean cumple(Muestra m) {
		return m.getOpiniones().stream()
				.map(Opinion::getFechaDeOpinion)
				.max(LocalDate::compareTo)
				.map(f -> comparador.comparar(fechaComparar, f))
				.orElse(false);
	}
	
};
