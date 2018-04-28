package sample;

import javafx.scene.chart.XYChart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by nkars on 7/12/2017.
 */
public class ChartData {
    int current;
    String currStr;
    int d;
    String r;
    ArrayList<Integer> addDataList = new ArrayList<>();
    ArrayList<String> addDataStrList = new ArrayList<>();
    int x = 0;

    public String dataDate() {

        try {
            HealthApp HA = new HealthApp();
            Connection mycon = DriverManager.getConnection("jdbc:mysql://localhost/health?autoReconnect=true&useSSL=false", "root", "SQL17^*@)!&neel");
            String sqlGetDate = "SELECT Date FROM bodyfatpercentage";
            ResultSet rs1 = mycon.createStatement().executeQuery(sqlGetDate);

            while (rs1.next()) {
                currStr = rs1.getString("Date");
                addDataStrList.add(currStr);
            }

            for (int index = 0; index < addDataStrList.size(); index++) {
                System.out.println(addDataStrList.get(index));
                r = addDataStrList.get(index);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public int dataBFP() {
        try {
            HealthApp HA = new HealthApp();
            Connection mycon = DriverManager.getConnection("jdbc:mysql://localhost/health?autoReconnect=true&useSSL=false", "root", "SQL17^*@)!&neel");
            String sqlGetBFP = "SELECT BFP FROM bodyfatpercentage";
            ResultSet rs = mycon.createStatement().executeQuery(sqlGetBFP);

            while (rs.next()) {
                current = rs.getInt("BFP");
                addDataList.add(rs.getInt("BFP"));
            }

            for (int i = 0; i < addDataList.size(); i++) {
                System.out.println(addDataList.get(i));
                d = addDataList.get(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }
}