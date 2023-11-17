package uaspbo.Contoller;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import uaspbo.Model.*;

public class Controller {

    static DatabaseHandler conn = new DatabaseHandler();

    public boolean inputDataToDB(int user_id, int game_id) {
        conn.connect();
        String query = "INSERT INTO transactions (user_id, game_id) VALUES (?, ?)";
        PreparedStatement stmt;
        try {
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, user_id);
            stmt.setInt(2, game_id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Transactions> getTransactions(int userID) {
        conn.connect();
        String query = "SELECT transactions.id, transactions.user_id, users.name, transactions.game_id, games.name, games.price\n"
                + "FROM transactions\n"
                + "JOIN users ON users.id = transactions.user_id\n"
                + "JOIN games ON games.id = transactions.game_id\n"
                + "WHERE transactions.user_id = '" + userID + "'";
        ArrayList<Transactions> listTransactions = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Transactions tr = new Transactions();
                tr.setId(rs.getInt("transactions.id"));
                tr.setUser_id(rs.getInt("transactions.user_id"));
                tr.setUsername(rs.getString("users.name"));
                tr.setGame_id(rs.getInt("transactions.game_id"));
                tr.setGameName(rs.getString("games.name"));
                tr.setPrice(rs.getString("games.price"));

                listTransactions.add(tr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (listTransactions);
    }

    public ArrayList<Games> getAllGames() {
        conn.connect();
        String query = "SELECT * FROM games";
        ArrayList<Games> listGames = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Games gms = new Games();
                gms.setName(rs.getString("name"));
                gms.setGenre(rs.getString("genre"));
                gms.setPrice(rs.getString("price"));
                listGames.add(gms);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (listGames);
    }

    public int getID(String email) {
        conn.connect();
        String query = "SELECT id FROM users WHERE email = '" + email + "'";
        int id = 0;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                id = (rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (id);
    }

    public int getIDGames(String name) {
        conn.connect();
        String query = "SELECT id FROM games WHERE name = '" + name + "'";
        int id = 0;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                id = (rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (id);
    }

    public boolean getGame(String name, String genre, String price) {
        conn.connect();
        String query = "SELECT * FROM games \n"
                + "WHERE name = '" + name + "' AND genre = '" + genre + "' AND price = '" + price + "'";
        boolean exists = false;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                exists = true;
            }
            return exists;
        } catch (SQLException e) {
            e.printStackTrace();
            return exists;
        }
    }

    public boolean findTR(int user_id, int game_id) {
        conn.connect();
        String query = "SELECT * FROM transactions "
                + "WHERE user_id = '" + user_id + "' AND game_id = '" + game_id + "'";
        boolean exists = false;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                exists = true;
            }
            return exists;
        } catch (SQLException e) {
            e.printStackTrace();
            return exists;
        }
    }

    public boolean logIn(String email, String password) {
        conn.connect();
        String query = "SELECT * FROM users WHERE email = '" + email + "' AND password = '" + password + "'";
        boolean exists = false;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return exists;
    }
}
