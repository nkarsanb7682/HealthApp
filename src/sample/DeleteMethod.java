package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by nkars on 6/23/2017.
 */
public class DeleteMethod {

    HealthApp HA = new HealthApp();

    public void deleteData(Integer DataPiece){
        String sqlDelete = "DELETE FROM bodyfatpercentage WHERE BFP = ?";
        try{

            Connection mycon = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/health?autoReconnect=true&useSSL=true", "root", "SQL17^*@)!&neel");
            PreparedStatement ps = mycon.prepareStatement(sqlDelete);
            ps.setInt(1, DataPiece);
            ps.executeUpdate();
            mycon.close();
        }catch(Exception e){

        }
    }
}
