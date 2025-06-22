package tpintegrador.filtro;

import java.util.Arrays;
import java.util.List;

import tpintegrador.muestra.Muestra;

public class FiltroOR implements Filtro{
	private List<Filtro> filtros;
	
	public FiltroOR(Filtro...filtros) {
		this.filtros = Arrays.asList(filtros);
	}
	
	@Override
	public boolean cumple(Muestra m) {
		return filtros.stream().anyMatch(f -> f.cumple(m));
	}

}
