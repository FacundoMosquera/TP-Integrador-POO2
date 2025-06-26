package tpintegrador.aplicacionWeb;

import java.util.List;
import java.util.stream.Collectors;

import tpintegrador.filtro.Filtro;
import tpintegrador.muestra.Muestra;

public class Buscador {
	
	public List<Muestra> filtrar(List<Muestra> muestras, List<Filtro>filtros){
		return muestras.stream()
				.filter(m -> filtros.stream().allMatch(f -> f.cumple(m)))
				.collect(Collectors.toList());
	}
}
