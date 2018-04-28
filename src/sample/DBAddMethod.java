package sample;

import java.sql.*;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by nkars on 6/11/2017.
 */
public class DBAddMethod {
    public void AddMethod(String time, Integer fat) {

        String sqladd = "INSERT INTO health.bodyfatpercentage(Date, BFP) VALUES(?, ?)";

        //ORIGINAL URL: jdbc:mysql://localhost/health?autoReconnect=true&useSSL=false
        //SECOND URL: jdbc:mysql://localhost:3306
        //THIRD URL: jdbc:mysql://127.0.0.1:3306/health
        //FOURTH URL: jdbc:mysql://127.0.0.1:3306/health?autoReconnect=true&useSSL=true

        try {
            Connection mycon = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/health?autoReconnect=true&useSSL=true", "root", "SQL17^*@)!&neel");

            PreparedStatement addStmt = mycon.prepareStatement(sqladd);

            String fatStr = fat.toString();

            addStmt.setString(1, time);
            addStmt.setString(2, fatStr);

            //Statement addStmt = mycon.createStatement();

            addStmt.executeUpdate();

            System.out.println("Update Complete");
            mycon.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
