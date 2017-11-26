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

    private PreparedStatement createSelectPreparedStatement(Connection con) throws SQLException {
        String query = "SELECT * FROM bills;";
        PreparedStatement ps = con.prepareStatement(query);

        return ps;
    }
}
