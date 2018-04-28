package sample;
import com.sun.rowset.internal.Row;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import javax.sql.RowSet;
import javax.sql.XAConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by nkars on 6/13/2017.
 */
public class getDataMethod{
    int current;
    String currStr;
    ArrayList<Integer> addDataList = new ArrayList<>();
    ArrayList<String> addDataStrList = new ArrayList<>();

    public void getData(){
HealthApp thing = new HealthApp();
        try {
            HealthApp HA = new HealthApp();
            Connection mycon = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/health?autoReconnect=true&useSSL=true", "root", "SQL17^*@)!&neel");
            String sqlGet = "SELECT BFP FROM bodyfatpercentage";
            String sqlGetDate = "SELECT Date FROM bodyfatpercentage";
            ResultSet rs = mycon.createStatement().executeQuery(sqlGet);
            ResultSet rs1 = mycon.createStatement().executeQuery(sqlGetDate);

            while (rs.next()){
                current = rs.getInt("BFP");
                addDataList.add(current);
            }
            while(rs1.next()){
                currStr = rs1.getString("Date");
                addDataStrList.add(currStr);
            }
            System.out.println("This worked");
            mycon.close();
            System.out.println("Connection closed");
        }catch (Exception exc) {
            exc.printStackTrace();
        }
    }
  }