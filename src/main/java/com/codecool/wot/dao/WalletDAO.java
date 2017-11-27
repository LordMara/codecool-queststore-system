package com.codecool.wot.dao;

import com.codecool.wot.model.Wallet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class WalletDAO {
    private static final WalletDAO INSTANCE = new WalletDAO();

    private List<Wallet> wallets;

    private WalletDAO() {
        this.wallets = new LinkedList<>();
        loadWalletsFromDatabase();
    }

    public static WalletDAO getInstance() {
        return INSTANCE;
    }

    public List<Wallet> read() {
        return this.wallets;
    }

    private void loadWalletsFromDatabase() {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createSelectPreparedStatement(con);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                Integer personId = result.getInt("personId");
                Double totalCoolcoinsEarn = result.getDouble("total_cc_earn");
                Double balance = result.getDouble("ballance");
                Integer levelId = result.getInt("levelId");

                this.wallets.add(new Wallet(personId, totalCoolcoinsEarn, balance, levelId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private PreparedStatement createSelectPreparedStatement(Connection con) throws SQLException {
        String query = "SELECT * FROM wallets;";
        PreparedStatement ps = con.prepareStatement(query);

        return ps;
    }
}
