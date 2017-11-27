package com.codecool.wot.dao;

import com.codecool.wot.model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

public class BillDAO {
    private static final BillDAO INSTANCE = new BillDAO();

    private List<Bill> bills;

    private BillDAO() {
        this.bills = new LinkedList<>();
        loadBillsFromDatabase();
    }

    public static BillDAO getInstance() {
        return INSTANCE;
    }

    public List<Bill> read() {
        return this.bills;
    }

    public void add(Bill bill) {
        try {
            addBillToDatabase(bill);
            this.bills.add(bill);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void loadBillsFromDatabase() {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createSelectPreparedStatement(con);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                Integer personId = result.getInt("personId");
                String status = result.getString("status");
                Integer questId = result.getInt("questId");
                String achieveDate = result.getString("achieveDate");

                this.bills.add(new Bill(personId, questId, status, achieveDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void addBillToDatabase(Bill bill) throws SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createAddPreparedStatement(con, bill)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }

    private PreparedStatement createSelectPreparedStatement(Connection con) throws SQLException {
        String query = "SELECT * FROM bills;";
        PreparedStatement ps = con.prepareStatement(query);

        return ps;
    }

    private PreparedStatement createAddPreparedStatement(Connection con, Bill bill) throws SQLException {
        String query = "INSERT INTO bills (personId, status, questId,  achieveDate)" +
                " VALUES (?, ?, ?, ?);";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, bill.getPerson().getId());
        ps.setString(2, bill.parseStatus());
        ps.setInt(3, bill.getQuest().getId());
        ps.setString(4, bill.parseDate());

        return ps;
    }
}
