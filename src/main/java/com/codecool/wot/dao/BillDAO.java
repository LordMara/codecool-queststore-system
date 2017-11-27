package com.codecool.wot.dao;

import com.codecool.wot.model.Account;
import com.codecool.wot.model.Bill;
import com.codecool.wot.model.Quest;

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

    public void update(Bill bill) {
        try {
            updateBillInDatabase(bill);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void remove(Bill bill) {
        try {
            deleteBillFromDatabase(bill);
            this.bills.remove(bill);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void setAllQuestToNull(Quest quest) {
        try {
            changeQuestToNullInMemory(quest);
            changeQuestToNullInDatabase(quest);

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void removeAllBills(Account person) {
        try {
            deleteBillsInMemory(person);
            deleteBillsInInDatabase(person);

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public Bill getBill(Integer id) {
        Bill bill = null;
        for (Bill candidate : this.bills) {
            if (candidate.getId().equals(id)) {
                bill = candidate;
            }
        }
        return bill;
    }

    public List<Bill> getBills(Account person) {
        List<Bill> personalBillsList = new LinkedList<>();
        for (Bill candidate : this.bills) {
            if (candidate.getPerson().equals(person)) {
                personalBillsList.add(candidate);
            }
        }
        return personalBillsList;
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
                Integer billId = result.getInt("billId");

                this.bills.add(new Bill(billId, personId, questId, status, achieveDate));
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

    private void updateBillInDatabase(Bill bill) throws SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createUpdatePreparedStatement(con, bill)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }

    private void deleteBillFromDatabase(Bill bill) throws SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createDeletePreparedStatement(con, bill)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }


    private void changeQuestToNullInMemory(Quest quest) {
        for (Bill bill: this.bills) {
            if (bill.getQuest().equals(quest)) {
                bill.setQuest();
            }
        }
    }

    private void deleteBillsInMemory(Account person){
        List<Bill> temp = new LinkedList<>();
        for (Bill bill: this.bills) {
            if (bill.getPerson().equals(person)) {
                temp.add(bill);
            }
        }

        this.bills.removeAll(temp);
    }

    private void changeQuestToNullInDatabase(Quest quest) throws  SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createNullAllPreparedStatement(con, quest)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }

    private void deleteBillsInInDatabase(Account person) throws  SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createDeleteAllPreparedStatement(con, person)) {
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
        String query = "INSERT INTO bills (personId, status, questId,  achieve_date)" +
                " VALUES (?, ?, ?, ?, ?);";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, bill.getPerson().getId());
        ps.setString(2, bill.parseStatus());
        ps.setInt(3, bill.getQuest().getId());
        ps.setString(4, bill.parseDate());

        return ps;
    }

    private PreparedStatement createUpdatePreparedStatement(Connection con, Bill bill) throws SQLException {
        String query = "UPDATE bills SET personId = ?, status = ?, questId = ?,  achieve_date = ?" +
                " WHERE billId = ?;";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, bill.getPerson().getId());
        ps.setString(2, bill.parseStatus());
        ps.setInt(3, bill.getQuest().getId());
        ps.setString(4, bill.parseDate());
        ps.setInt(5, bill.getId());

        return ps;
    }

    private PreparedStatement createDeletePreparedStatement(Connection con, Bill bill) throws SQLException {
        String query = "DELETE FROM bills WHERE billId = ?;";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, bill.getId());

        return ps;
    }

    private PreparedStatement createNullAllPreparedStatement(Connection con, Quest quest) throws SQLException {
        String query = "UPDATE bills SET questId = NULL WHERE questId = ?;";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, quest.getId());

        return ps;
    }

    private PreparedStatement createDeleteAllPreparedStatement(Connection con, Account person) throws SQLException {
        String query = "DELETE FROM bills WHERE personId = ?;";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, person.getId());

        return ps;
    }
}
