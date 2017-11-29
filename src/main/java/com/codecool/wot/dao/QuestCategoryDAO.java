package com.codecool.wot.dao;

import com.codecool.wot.model.QuestCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class QuestCategoryDAO {
    private static final QuestCategoryDAO INSTANCE = new QuestCategoryDAO();

    private List<QuestCategory> questCategories;

    private QuestCategoryDAO() {
        this.questCategories = new LinkedList<>();
        loadQuestCategoriesFromDatabase();
    }

    public static QuestCategoryDAO getInstance() {
        return INSTANCE;
    }

    public List<QuestCategory> read() {
        return this.questCategories;
    }

    public void add(QuestCategory questCategory) {
        try {
            addQuestCategoryToDatabase(questCategory);
            this.questCategories.add(questCategory);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void update(QuestCategory questCategory) {
        try {
            updateQuestCategoryInDatabase(questCategory);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void remove(QuestCategory questCategory) {
        try {
            QuestDAO.getInstance().setAllQuestCategoryToNull(questCategory);
            deleteQuestCategoryFromDatabase(questCategory);
            this.questCategories.remove(questCategory);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public QuestCategory getQuestCategory(Integer questId) {
        QuestCategory questCategory = null;
        for (QuestCategory candidate : this.questCategories) {
            if (candidate.getId().equals(questId)) {
                questCategory = candidate;
            }
        }
        return questCategory;
    }

    public QuestCategory getQuestCategory(String name) {
        QuestCategory questCategory = null;
        for (QuestCategory candidate : this.questCategories) {
            if (candidate.getName().equals(name)) {
                questCategory = candidate;
            }
        }
        return questCategory;
    }

    private void loadQuestCategoriesFromDatabase() {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createSelectPreparedStatement(con);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                Integer questCategoryId = result.getInt("quest_category_id");
                String name = result.getString("name");

                questCategories.add(new QuestCategory(questCategoryId, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void addQuestCategoryToDatabase(QuestCategory questCategory) throws SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createAddPreparedStatement(con, questCategory)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }

    private void updateQuestCategoryInDatabase(QuestCategory questCategory) throws SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createUpdatePreparedStatement(con, questCategory)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }

    private void deleteQuestCategoryFromDatabase(QuestCategory questCategory) throws SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createDeletePreparedStatement(con, questCategory)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }

    private PreparedStatement createSelectPreparedStatement(Connection con) throws SQLException {
        String query = "SELECT * FROM quests_category;";
        PreparedStatement ps = con.prepareStatement(query);

        return ps;
    }

    private PreparedStatement createAddPreparedStatement(Connection con, QuestCategory questCategory) throws SQLException {
        String query = "INSERT INTO quests_category (name) VALUES (?);";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, questCategory.getName());

        return ps;
    }

    private PreparedStatement createUpdatePreparedStatement(Connection con, QuestCategory questCategory) throws SQLException {
        String query = "UPDATE quests_category SET name = ? WHERE quest_category_id = ?;";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, questCategory.getName());
        ps.setInt(2, questCategory.getId());

        return ps;
    }

    private PreparedStatement createDeletePreparedStatement(Connection con, QuestCategory questCategory) throws SQLException {
        String query = "DELETE FROM quests_category WHERE quest_category_id = ?;";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, questCategory.getId());

        return ps;
    }
}
