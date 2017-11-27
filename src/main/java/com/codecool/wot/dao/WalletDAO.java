package com.codecool.wot.dao;

import com.codecool.wot.model.Account;
import com.codecool.wot.model.Level;
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

    public void add(Wallet wallet) {
        try {
            addWalletToDatabase(wallet);
            this.wallets.add(wallet);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void update(Wallet wallet) {
        try {
            updateWalletInDatabase(wallet);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void remove(Wallet wallet) {
        try {
            deleteWalletFromDatabase(wallet);
            this.wallets.remove(wallet);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void setAllLevelsToNull(Level level) {
        try {
            changeLevelToNullInMemory(level);
            changeLevelToNullInDatabase(level);

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public Wallet getWallet(Account person) {
        Wallet wallet = null;
        for (Wallet candidate : this.wallets) {
            if (candidate.getPerson().equals(person)) {
                wallet = candidate;
            }
        }
        return wallet;
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

    private void addWalletToDatabase(Wallet wallet) throws SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createAddPreparedStatement(con, wallet)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }

    private void updateWalletInDatabase(Wallet wallet) throws SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createUpdatePreparedStatement(con, wallet)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }

    private void deleteWalletFromDatabase(Wallet wallet) throws SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createDeletePreparedStatement(con, wallet)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }

    private void changeLevelToNullInMemory(Level level) {
        for (Wallet wallet: this.wallets) {
            if (wallet.getLevel().equals(wallet)) {
                wallet.setLevel();
            }
        }
    }

    private void changeLevelToNullInDatabase(Level level) throws  SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createNullAllPreparedStatement(con, level)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }

    private PreparedStatement createSelectPreparedStatement(Connection con) throws SQLException {
        String query = "SELECT * FROM wallets;";
        PreparedStatement ps = con.prepareStatement(query);

        return ps;
    }

    private PreparedStatement createAddPreparedStatement(Connection con, Wallet wallet) throws SQLException {
        String query = "INSERT INTO wallets (personId, total_cc_earn, ballance, levelId) VALUES (?, ?, ?, ?);";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, wallet.getPerson().getId());
        ps.setDouble(2, wallet.getTotalCoolcoinsEarn());
        ps.setDouble(3, wallet.getBalance());
        ps.setInt(1, wallet.getLevel().getId());

        return ps;
    }

    private PreparedStatement createUpdatePreparedStatement(Connection con, Wallet wallet) throws SQLException {
        String query = "UPDATE wallets SET total_cc_earn = ?, ballance = ?, levelId = ? WHERE personId = ?;";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setDouble(1, wallet.getTotalCoolcoinsEarn());
        ps.setDouble(2, wallet.getBalance());
        ps.setInt(3, wallet.getLevel().getId());
        ps.setInt(4, wallet.getPerson().getId());

        return ps;
    }

    private PreparedStatement createDeletePreparedStatement(Connection con, Wallet wallet) throws SQLException {
        String query = "DELETE FROM wallets WHERE personId = ?;";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, wallet.getPerson().getId());

        return ps;
    }

    private PreparedStatement createNullAllPreparedStatement(Connection con, Level level) throws SQLException {
        String query = "UPDATE wallets SET levelId = NULL WHERE levelId = ?;";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, level.getId());

        return ps;
    }
}
