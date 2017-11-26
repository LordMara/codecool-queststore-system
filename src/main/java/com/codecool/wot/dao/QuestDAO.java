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

    private PreparedStatement createSelectPreparedStatement(Connection con) throws SQLException {
        String query = "SELECT * FROM quests;";
        PreparedStatement ps = con.prepareStatement(query);

        return ps;
    }

}
