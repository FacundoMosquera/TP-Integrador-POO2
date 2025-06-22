package tpintegrador.filtro;

import java.util.Arrays;
import java.util.List;

import tpintegrador.muestra.Muestra;

public class FiltroAND implements Filtro{
	private List<Filtro> filtros;
	
	public FiltroAND(Filtro...filtros) {
		this.filtros = Arrays.asList(filtros);
	}
	
	@Override
	public boolean cumple(Muestra m) {
		return filtros.stream().allMatch(f -> f.cumple(m));
	}

}
