package net.stockholm.clash.game.player.sql;

import net.stockholm.clash.game.item.weapon.Weapon;
import net.stockholm.clash.game.player.Handle;
import net.stockholm.clash.game.rebirth.Rebirth;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database implements DatabaseManager {

    String url = "";
    String user = "";
    String password = "";

    Connection connection;

    public Database() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        checkAvailability();
    }

    private void checkAvailability() {
        try {
            String query = "CREATE TABLE IF NOT EXISTS player_data (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "username VARCHAR(255) NOT NULL UNIQUE," +
                    "money DOUBLE DEFAULT 0," +
                    "strength DOUBLE DEFAULT 0," +
                    "weapon VARCHAR(255) NOT NULL," +
                    "rebirthAmount DOUBLE DEFAULT 0," +
                    "rebirthCost DOUBLE DEFAULT 0)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Player player) {
        try {
            String query = "REPLACE INTO player_data (username, money, strength, weapon, rebirthAmount, rebirthCost) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, player.getName());
                preparedStatement.setDouble(2, Handle.money().get(player));
                preparedStatement.setDouble(3, Handle.strength().get(player));
                preparedStatement.setString(4, Handle.weapon().get(player).getName());
                preparedStatement.setDouble(5, Handle.rebirth().get(player).getAmount());
                preparedStatement.setDouble(6, Handle.rebirth().get(player).getCost());
                preparedStatement.executeUpdate();
            }

            query = "UPDATE player_data SET " +
                    "money = ?, " +
                    "strength = ?, " +
                    "weapon = ?, " +
                    "rebirthAmount = ?, " +
                    "rebirthCost = ? " +
                    "WHERE username = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setDouble(1, Handle.money().get(player));
                preparedStatement.setDouble(2, Handle.strength().get(player));
                preparedStatement.setString(3, Handle.weapon().get(player).getName());
                preparedStatement.setDouble(4, Handle.rebirth().get(player).getAmount());
                preparedStatement.setDouble(5, Handle.rebirth().get(player).getCost());
                preparedStatement.setString(6, player.getName());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load(Player player) {
        try {
            String query = "SELECT money, strength, weapon, rebirthAmount, rebirthCost FROM player_data WHERE username = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, player.getName());
                var resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    double money = resultSet.getDouble("money");
                    double strength = resultSet.getDouble("strength");
                    String weapon = resultSet.getString("weapon");
                    double rebirthAmount = resultSet.getDouble("rebirthAmount");
                    double rebirthCost = resultSet.getDouble("rebirthCost");

                    Handle.money().set(player, money);
                    Handle.strength().set(player, strength);
                    for (Weapon weapons : Weapon.weapons) {
                        if (weapons.getName().equals(weapon)) {
                            Handle.weapon().set(player, weapons);
                        }
                    }
                    Handle.rebirth().set(player, new Rebirth(rebirthAmount, rebirthCost));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}