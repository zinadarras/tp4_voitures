package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import metier.entities.Voiture;
import util.JPAutil;

public class VoitureDaoImpl implements IVoitureDao {
	private EntityManager entityManager=JPAutil.getEntityManager("TP5_Voitures");
	@Override
	public Voiture save(Voiture v) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(v);
		tx.commit();
		return v;
		}
		@Override
		public List<Voiture> voituresParMC(String mc) {
		List<Voiture> voits =
		 entityManager.createQuery("select v from Voiture v where v.marque like :mc").setParameter("mc", "%"+mc+"%").getResultList();
		 return voits;
		}
		@Override
		public Voiture getVoiture(Long id) {
		 return entityManager.find(Voiture.class, id);
		}
		@Override
		public Voiture updateVoiture(Voiture v) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(v);
		tx.commit();
		return v;
		}
		@Override
		public void deleteVoiture(Long id) {
		Voiture voiture = entityManager.find(Voiture.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(voiture);
		entityManager.getTransaction().commit();
		}
	}
