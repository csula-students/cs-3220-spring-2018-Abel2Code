package edu.csula.storage.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.csula.storage.GeneratorsDAO;
import edu.csula.storage.Database;
import edu.csula.models.Generator;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class GeneratorsDAOImpl implements GeneratorsDAO {
	private final Database context;

	// TODO: fill the Strings with the SQL queries as "prepated statements" and
	//       use these queries variable accordingly in the method below
	protected static final String getAllQuery = "SELECT * FROM generators;";
	protected static final String getByIdQuery = "SELECT * FROM generators WHERE id = ?";
	protected static final String setQuery = "UPDATE generators SET name=?, description=?, rate=?, base_cost=?, unlock_at=? WHERE id=?;";
	protected static final String addQuery = "INSERT INTO generators VALUES (?, ?, ?, ?, ?, ?)";
	protected static final String removeQuery = "DELETE FROM generators WHERE id=?;";

	public GeneratorsDAOImpl(Database context) {
		this.context = context;
	}

	@Override
	public List<Generator> getAll() {
		// TODO: get all generators from jdbc
		List<Generator> result = new ArrayList<>();
		try (Connection c = context.getConnection(); Statement stmt = c.createStatement()) {
			ResultSet rs = stmt.executeQuery(getAllQuery);
			while (rs.next()) {
				Generator generator = new Generator(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4), rs.getInt(5), rs.getInt(6));
				result.add(generator);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		return result;
	}

	@Override
	public Optional<Generator> getById(int id) {
		// TODO: get specific generator by id
		try(Connection c = context.getConnection();) {
			PreparedStatement pstmt = c.prepareStatement(getByIdQuery);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			return Optional.of(new Generator(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4), rs.getInt(5), rs.getInt(6)));
		} catch (SQLException e) {
			e.printStackTrace();
			return Optional.empty();
		} catch (Exception e){
			e.printStackTrace();
			return Optional.empty();
		}
	}

	@Override
	public void set(int id, Generator generator) {
		// TODO: update specific generator by id
		try(Connection c = context.getConnection();) {
			PreparedStatement pstmt = c.prepareStatement(setQuery);
			pstmt.setString(1, generator.getName());
			pstmt.setString(2, generator.getDescription());
			pstmt.setInt(3, generator.getRate());
			pstmt.setInt(4, generator.getBaseCost());
			pstmt.setInt(5, generator.getUnlockAt());
			pstmt.setInt(6, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void add(Generator generator) {
		// TODO: implement jdbc logic to add a new generator
		try(Connection c = context.getConnection();) {
			PreparedStatement pstmt = c.prepareStatement(addQuery);
			pstmt.setInt(1, generator.getId());
			pstmt.setString(2, generator.getName());
			pstmt.setString(3, generator.getDescription());
			pstmt.setInt(4, generator.getRate());
			pstmt.setInt(5, generator.getBaseCost());
			pstmt.setInt(6, generator.getUnlockAt());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void remove(int id) {
		// TODO: implement jdbc logic to remove generator by id
		try(Connection c = context.getConnection();) {
			PreparedStatement pstmt = c.prepareStatement(removeQuery);
			pstmt.setInt(1, id);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
