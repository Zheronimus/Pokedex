import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PokeData {
	
	public PokeData() {
		
	}
	
	
	
	private static Connection connect() {
		
		String connString = "jdbc:sqlite:PokemonDB.db";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(connString);
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}

		return conn;
	}
	
	
	
	public static void setPokeData(ArrayList<Pokemon> dex, String tableName) {
		
		ResultSet data;
		String sql = "SELECT * FROM " + tableName;

		try(Connection conn = connect(); PreparedStatement statement = conn.prepareStatement(sql)) {
			data = statement.executeQuery();

			while(data.next()) {
				dex.add(new Pokemon(data.getInt("EntryNum"), data.getString("Name"), data.getString("TypeOne"), data.getString("TypeTwo"),
						data.getInt("Hp"), data.getInt("Attack"), data.getInt("Defense"), data.getInt("SpAttack"), data.getInt("SpDefense"),
						data.getInt("Speed")));
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}