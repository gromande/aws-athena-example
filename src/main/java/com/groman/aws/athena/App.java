package com.groman.aws.athena;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;

public class App {
  public static void main(String[] args) throws Exception {
    Properties info = new Properties();
    info.put("AwsCredentialsProviderClass",
        "com.simba.athena.amazonaws.auth.DefaultAWSCredentialsProviderChain");
    info.put("S3OutputLocation", "s3://<your-s3-bucket>/");
    Class.forName("com.simba.athena.jdbc.Driver");


    try (
        Connection conn =
            DriverManager.getConnection("jdbc:awsathena://AwsRegion=us-east-1;", info);
        Statement statement = conn.createStatement()) {
      ResultSet rs = statement.executeQuery(
          "SELECT * FROM applications.application_roles where application_name='Application 1'");

      ResultSetMetaData rsmd = rs.getMetaData();
      int columnsNumber = rsmd.getColumnCount();
      while (rs.next()) {
        for (int i = 1; i <= columnsNumber; i++) {
          if (i > 1)
            System.out.print(",  ");
          String columnValue = rs.getString(i);
          System.out.print(columnValue + " " + rsmd.getColumnName(i));
        }
        System.out.println("");
      }

      rs.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
