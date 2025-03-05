package dao;

import java.util.List;

import metier.entities.Voiture;

public interface IVoitureDao {
	public Voiture save(Voiture v);

	public List<Voiture> voituresParMC(String mc);

	public Voiture getVoiture(Long id);

	public Voiture updateVoiture(Voiture v);

	public void deleteVoiture(Long id);
}
