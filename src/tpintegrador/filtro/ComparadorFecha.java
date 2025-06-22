package tpintegrador.filtro;

import java.time.LocalDate;

public enum ComparadorFecha {
	ANTES {
		public boolean comparar(LocalDate fechaReferencia, LocalDate fechaComparar) {
			return fechaComparar.isBefore(fechaReferencia);
		}
	},
	DESPUES {
		public boolean comparar(LocalDate fechaReferencia, LocalDate fechaComparar) {
			return fechaComparar.isAfter(fechaReferencia);
		}
	},
	IGUAL {
		public boolean comparar(LocalDate fechaReferencia, LocalDate fechaComparar) {
			return fechaComparar.isEqual(fechaReferencia);
		}
	},
	ANTES_O_IGUAL {
		public boolean comparar(LocalDate fechaReferencia, LocalDate fechaComparar) {
			return fechaComparar.isBefore(fechaReferencia) || fechaComparar.isEqual(fechaReferencia);
		}
	}, 
	DESPUES_O_IGUAL {
		public boolean comparar(LocalDate fechaReferencia, LocalDate fechaComparar) {
			return fechaComparar.isAfter(fechaReferencia) || fechaComparar.isEqual(fechaReferencia);
		}	
	}, 
	DIFERENTE {
		   public boolean comparar(LocalDate fechaReferencia, LocalDate fechaComparar) {
	            return !fechaComparar.isEqual(fechaReferencia);
	        }
	};
	public abstract boolean comparar(LocalDate fechaReferencia, LocalDate fechaComparar);
};