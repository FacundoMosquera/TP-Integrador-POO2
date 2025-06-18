package tpintegrador;

public interface Observable {

	public void addInteresado(Interesado i, Evento e);
	public void removeInteresado(Interesado i, Evento e);
	public void notify(Observable o, Evento e);
}
