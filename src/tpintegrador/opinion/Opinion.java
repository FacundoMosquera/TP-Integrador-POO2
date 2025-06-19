package tpintegrador.opinion;

import java.time.LocalDate;

import tpintegrador.usuario.Usuario;

public class Opinion {
	
	private TipoDeOpinion tipoOpinion;
	private Usuario usuario;
	private LocalDate fecha;
	private boolean comentadoPorExperto;
	
	public Opinion(TipoDeOpinion tOpinion, Usuario autor) {
		this.tipoOpinion         = tOpinion;
		this.usuario             = autor;
		this.fecha               = LocalDate.now();	
		this.comentadoPorExperto = autor.esExperto();
	}
	
	public Usuario getUsuario() {return usuario;}
	
	public TipoDeOpinion getTipoDeOpinion() {return tipoOpinion;}
	
	public LocalDate getFechaDeOpinion() {return fecha;}
	
	public boolean esLaOpinionDeUnExperto() {return comentadoPorExperto;}
}
