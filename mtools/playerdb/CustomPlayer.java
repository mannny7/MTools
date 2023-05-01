package com.manny.mtools.playerdb;

import com.manny.mtools.MTools;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CustomPlayer {

    private final MTools main;


    private final UUID uuid;
    private String rank;
    private int xp;
    private int muted;
    private int frozen;

    public CustomPlayer(MTools main, UUID uuid) throws SQLException {

        this.main = main;
        this.uuid = uuid;

        PreparedStatement statement = main.getDatabase().getConnection().prepareStatement(
                "SELECT RANK, XP, FROZEN, MUTED FROM players WHERE UUID = ?;");
        statement.setString(1, uuid.toString());
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            rank = rs.getString("RANK");
            xp = rs.getInt("XP");
            frozen = rs.getInt("FROZEN");
            muted = rs.getInt("MUTED");
        } else {
            rank = "GUEST";
            xp = 0;
            frozen = 0;
            muted = 0;
            PreparedStatement statement1 = main.getDatabase().getConnection().prepareStatement(
                    "INSERT INTO players(ID, UUID, RANK, XP, FROZEN, MUTED) VALUES (" +
                            "default," +
                            "'" + uuid + "'," +
                            "'" + rank + "', " +
                            xp + "," + frozen + "," + muted + ")");
            statement1.executeUpdate();
            }

    }

    public void setRank(String rank) {
        this.rank = rank;

        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement(
                    "UPDATE players SET RANK = '" + rank + "' WHERE UUID = '" + uuid + "'");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setFrozen(boolean frozen) {

        int to_set;

        if (frozen) {
            to_set = 1;
        } else {
            to_set = 0;
        }

        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement(
                    "UPDATE players SET FROZEN = " + to_set + " WHERE UUID = '" + uuid + "'");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setXP (int xp) {

        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement(
                    "UPDATE players SET XP = " + xp + " WHERE UUID = '" + uuid + "'");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.xp = xp;
    }


    public void setMuted(boolean muted) {

        int to_set;

        if (muted) {
            to_set = 1;
        } else {
            to_set = 0;
        }


        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement(
                    "UPDATE players SET MUTED = " + to_set + " WHERE UUID = '" + uuid + "'");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int getFrozen() { return frozen; }
    public int getMuted() { return muted; }
    public String getRank() { return rank; }
    public int getXP() { return xp; }

}
