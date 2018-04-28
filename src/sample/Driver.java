package sample;

import com.sun.java.util.jar.pack.PackerImpl;

import java.sql.*;

/**
 * Created by nkars on 6/10/2017.
 */
public class Driver {
    public static void main(String[] args) {

        try{

            // Get a connection to the database.
            Connection mycon = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/health?autoReconnect=true&useSSL=true","root", "SQL17^*@)!&neel");

            // Create a statement
            Statement myStmt = mycon.createStatement();

            //Execute query
            ResultSet myRs = myStmt.executeQuery("select * from health.bodyfatpercentage");

            //Process result set
            while(myRs.next()){
                System.out.println(myRs.getString("BFP") + ", " + myRs.getString("Date"));
            }

        }catch(Exception exc){
            exc.printStackTrace();
        }

        try{
            Connection mycon = DriverManager.getConnection("jdbc:mysql://localhost/health?autoReconnect=true&useSSL=false","root", "SQL17^*@)!&neel");
            Statement myStmt1 = mycon.createStatement();

            String sql = "insert into health.bodyfatpercentage" + " (Date, BFP) " + " values ('2000-12-02', '78')";
            myStmt1.executeUpdate(sql);
            System.out.println("Update Complete");

            /*ResultSet myRs1 = myStmt1.executeQuery(sql);

            while (myRs1.next()){
                System.out.println(myRs1.getString("BFP") + ", " + myRs1.getString("Date"));
            } */
        }catch(Exception exc1){
            exc1.printStackTrace();
        }
    }
}
