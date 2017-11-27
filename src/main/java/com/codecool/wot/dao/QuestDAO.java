package com.codecool.wot.dao;

import com.codecool.wot.model.Quest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class QuestDAO {
    private static final QuestDAO INSTANCE = new QuestDAO();

    private List<Quest> quests;

    private QuestDAO() {
        this.quests = new LinkedList<>();
        loadQuestsFromDatabase();
    }

    public static QuestDAO getInstance() {
        return INSTANCE;
    }

    public List<Quest> read() {
        return this.quests;
    }

    public void add(Quest quest) {
        try {
            addQuestToDatabase(quest);
            this.quests.add(quest);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void update(Quest quest) {
        try {
            updateQuestInDatabase(quest);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void remove(Quest quest) {
        try {
            BillDAO.getInstance().setAllQuestToNull(quest);
            deleteQuestFromDatabase(quest);
            this.quests.remove(quest);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public Quest getQuest(Integer questId) {
        Quest quest = null;
        for (Quest candidate : this.quests) {
            if (candidate.getId().equals(questId)) {
                quest = candidate;
            }
        }
        return quest;
    }

    public Quest getQuest(String questName) {
        Quest quest = null;
        for (Quest candidate : this.quests) {
            if (candidate.getName().equals(questName)) {
                quest = candidate;
            }
        }
        return quest;
    }

    private void loadQuestsFromDatabase() {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createSelectPreparedStatement(con);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                Integer questId = result.getInt("questId");
                String name = result.getString("name");
                String description = result.getString("description");
                Double price = result.getDouble("price");

                quests.add(new Quest(questId, name, description, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void addQuestToDatabase(Quest quest) throws SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createAddPreparedStatement(con, quest)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }

    private void updateQuestInDatabase(Quest quest) throws SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createUpdatePreparedStatement(con, quest)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }

    private void deleteQuestFromDatabase(Quest quest) throws SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createDeletePreparedStatement(con, quest)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }

    private PreparedStatement createSelectPreparedStatement(Connection con) throws SQLException {
        String query = "SELECT * FROM quests;";
        PreparedStatement ps = con.prepareStatement(query);

        return ps;
    }

    private PreparedStatement createAddPreparedStatement(Connection con, Quest quest) throws SQLException {
        String query = "INSERT INTO quests (name, description, price) VALUES (?, ?, ?);";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, quest.getName());
        ps.setString(2, quest.getDescription());
        ps.setDouble(3, quest.getPrice());

        return ps;
    }

    private PreparedStatement createUpdatePreparedStatement(Connection con, Quest quest) throws SQLException {
        String query = "UPDATE quests SET name = ?, description = ?, price = ? WHERE questId = ?;";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, quest.getName());
        ps.setString(2, quest.getDescription());
        ps.setDouble(3, quest.getPrice());
        ps.setInt(4, quest.getId());

        return ps;
    }

    private PreparedStatement createDeletePreparedStatement(Connection con, Quest quest) throws SQLException {
        String query = "DELETE FROM quests WHERE questId = ?;";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, quest.getId());

        return ps;
    }
}
