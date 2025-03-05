package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.entities.Voiture;

public class VoitureDaoImpl implements IVoitureDao {
		@Override
		public Voiture save(Voiture v) {
			Connection conn = SingletonConnection.getConnection();
			try {
				PreparedStatement ps = conn.prepareStatement("INSERT INTO VOITURE (MARQUE,MODELE,PRIX) VALUES(?,?,?)");
				ps.setString(1, v.getMarque());
				ps.setString(2, v.getModele());
				ps.setDouble(3, v.getPrix());
				ps.executeUpdate();
				PreparedStatement ps2 = conn.prepareStatement("SELECT MAX(ID) as MAX_ID FROM VOITURE");
				ResultSet rs = ps2.executeQuery();
				if (rs.next()) {
					v.setId(rs.getLong("MAX_ID"));
				}
				ps.close();
				ps2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return v;
		}

		@Override
		public List<Voiture> voituresParMC(String mc) {
			List<Voiture> voits = new ArrayList<Voiture>();
			Connection conn = SingletonConnection.getConnection();
			try {
				PreparedStatement ps = conn.prepareStatement("select * from VOITURE where MARQUE LIKE ?");
				ps.setString(1, "%" + mc + "%");
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Voiture v = new Voiture();
					v.setId(rs.getLong("ID"));
					v.setMarque(rs.getString("MARQUE"));
					v.setModele(rs.getString("MODELE"));
					v.setPrix(rs.getDouble("PRIX"));
					voits.add(v);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return voits;
		}

		@Override
		public Voiture getVoiture(Long id) {

		 Connection conn=SingletonConnection.getConnection();
		 Voiture v = new Voiture();
		 try {
		PreparedStatement ps= conn.prepareStatement("select * from VOITURE where ID = ?");
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
		v.setId(rs.getLong("ID"));
		v.setMarque(rs.getString("MARQUE"));
		v.setModele(rs.getString("MODELE"));
		v.setPrix(rs.getDouble("PRIX"));
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return v;
		}

		@Override
		public Voiture updateVoiture(Voiture v) {
			Connection conn = SingletonConnection.getConnection();
			try {
				PreparedStatement ps = conn.prepareStatement("UPDATE VOITURE SET MARQUE=?,MODELE=?,PRIX=? WHERE ID=?");
				ps.setString(1, v.getMarque());
				ps.setString(2, v.getModele());
				ps.setDouble(2, v.getPrix());
				ps.setLong(3, v.getId());
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return v;
		}

		@Override
		public void deleteVoiture(Long id) {
			Connection conn = SingletonConnection.getConnection();
			try {
				PreparedStatement ps = conn.prepareStatement("DELETE FROM VOITURE WHERE ID = ?");
				ps.setLong(1, id);
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
