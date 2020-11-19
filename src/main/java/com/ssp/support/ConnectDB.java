package com.ssp.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;


public class ConnectDB {

  public static Connection connection = null;
  public static Statement statement = null;
  String connSatus = "Initialized";



  public ConnectDB(String connectionString) throws SQLException {

    Log.message("Method into db trying to connect");
    boolean connectionClosed = false;
    if (connection != null)
      if (connection.isClosed() == true)
        connectionClosed = true;

    if (connection == null || connectionClosed) {
      statement = null;
      try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      } catch (ClassNotFoundException e) {
        throw new SQLException("Database Connection Failed!! " + e.getMessage());
      }

      try {

        connection = DriverManager.getConnection(connectionString);

      } catch (SQLException ex) {
        connSatus = "Database Connection Failed!!";
        throw new SQLException(connSatus + ex.getMessage());

      }

      if (connection != null) {
        Log.event("Database connection established successfully.");
      } else {
        Log.event("Failed to make connection!");
      }
    }
  }

  public ResultSet runQuery(String query) throws SQLException {
    try {
      ResultSet rs;
      statement = connection.createStatement();
      rs = statement.executeQuery(query);
      return rs;
    }

    catch (SQLException ex) {
      throw new SQLException("Failed to execute query in DB, exception occured" + ex.getMessage());
    }

  }

}
