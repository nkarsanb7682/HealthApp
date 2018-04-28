package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class HealthApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }
Label test = new Label();
//retrieves data from database
      getDataMethod retrieve = new getDataMethod();
      int current;
      String currStr;
      ArrayList<Integer> addDataList = new ArrayList<>();
      ArrayList<String> addDataStrList = new ArrayList<>();

//Arrays of excercises, and instance of ChooseSchedule
    ChooseSchedule CS = new ChooseSchedule();
    String[] arms = {"Hammer Curls", "Curls", "EZ Bar Curl", "Skull Crusher", "Tricep Dip"};
    String[] Abs = {"Crunches", "Side Plank"};

//Create calendar


//Method gets date.
    DateFormat df = new SimpleDateFormat("MM");
    DateFormat day = new SimpleDateFormat("dd");
    Calendar calendar = Calendar.getInstance();
    String month = (df.format(calendar.getTime()));
    String day1 = (day.format(calendar.getTime()));
    int months = Integer.parseInt(month);

// Creates variables to put into equation
    Integer abdomenCalc;
    Integer neckCalc;
    Integer heightCalc;


//Allows add method to access text fields
    TextField abdomen = new TextField();
    TextField neck = new TextField();
    TextField height = new TextField();
// Name of month. Class variable to add button method can access it.
    String monthString;

// Declares the graph
    XYChart.Series data = new XYChart.Series();

//Declares the add and delete buttons
    Button delete = new Button();
    Button add = new Button();

// Variable declarations for navigation buttons.
    Button bWeights = new Button();
    Button FatPctg = new Button();

//Creates the list that the user can see
    ObservableList<Integer> BFP = FXCollections.observableArrayList();

//Creates the underlying list
    ListView<Integer> BodyFatPercentage = new ListView<Integer>(BFP);

// Creates line graph
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);

    // Counter for add, and delete buttons.
    int count = 0;


//Allows Methods to access view1. thie is the view for th strength page.
    GridPane view1 = new GridPane();

    @Override
    public void start(Stage primaryStage) {
//Create scene

        //Scene scene = new Scene(view, 1000, 800);
        GridPane view = new GridPane();
        Scene scene = new Scene(view, 1000, 800);
        primaryStage.setTitle("Health Application");
        primaryStage.setScene(scene);
        primaryStage.show();
        view.getStylesheets().add("CSSList.css");

//Menu
        bWeights.getStyleClass().add("bWeights");
        bWeights.setText("Strength");
        GridPane.setConstraints(bWeights,0,0);
        view.getChildren().add(bWeights);

        FatPctg.getStyleClass().add("bFat");
        FatPctg.setText("Body Fat");
        GridPane.setConstraints(FatPctg,1,0);
        view.getChildren().add(FatPctg);

//Add the list it to the scene, and fills the list with data from the database
        BodyFatPercentage.setItems(BFP);
        retrieve.getData();
        BFP.addAll(retrieve.addDataList);
        GridPane.setConstraints(BodyFatPercentage, 0, 1);
        view.getChildren().add(BodyFatPercentage);
        GridPane.setMargin(BodyFatPercentage, new Insets(10, 0, 0, 10));

//Create delete button, and declare the method that sets the behavior of the delete button.
        delete.setText("Delete");
        delete.setOnAction(e -> ClickDelete());
        GridPane.setConstraints(delete, 2, 4);
        view.getChildren().add(delete);
        //GridPane.setMargin(delete, new Insets(20, 0, 0, 0));
        GridPane.setMargin(delete, new Insets(20, 0, 0, 5));

//Creates add button
        add.setText("Add");
        add.setOnAction(e -> ClickAdd());
        GridPane.setConstraints(add, 1, 4);
        view.getChildren().add(add);
        GridPane.setMargin(add, new Insets(20, 5, 0, 10));
        //GridPane.setMargin(delete, new Insets(20, 5, 0, 10));

//Add a label and text field to view. The Declaration is at the top as a class variable.
        //Find a way to add a maximum length to textfield, so that the number doen't become a long.
        abdomen.setPrefColumnCount(10);
        GridPane.setConstraints(abdomen, 0, 2);
        view.getChildren().add(abdomen);
        abdomen.setPromptText("Abdomen Measurement (in)");
        GridPane.setMargin(abdomen, new Insets(15, 0, 0, 10));

        neck.setPrefColumnCount(10);
        GridPane.setConstraints(neck, 0, 3);
        view.getChildren().add(neck);
        neck.setPromptText("Neck Measurement (in)");
        GridPane.setMargin(neck, new Insets(20, 0, 0, 10));

        height.setPrefColumnCount(10);
        GridPane.setConstraints(height, 0, 4);
        view.getChildren().add(height);
        height.setPromptText("Height (in)");
        GridPane.setMargin(height, new Insets(20, 0, 0, 10));

// Adds line chart to scene
        view.getChildren().add(lineChart);
        GridPane.setConstraints(lineChart, 3, 1);

//Add title and data to line chart
        data.setName("Body Fat Percentage");
        lineChart.getData().add(data);

//your parents dont love you ballsack

// Converts month number to name of month

        switch (months) {
            case 1:
                monthString = "Jan " + day1;
                break;
            case 2:
                monthString = "Feb " + day1;
                break;
            case 3:
                monthString = "Mar " + day1;
                break;
            case 4:
                monthString = "Apr " + day1;
                break;
            case 5:
                monthString = "May " + day1;
                break;
            case 6:
                monthString = "Jun " + day1;
                break;
            case 7:
                monthString = "Jul " + day1;
                break;
            case 8:
                monthString = "Aug " + day1;
                break;
            case 9:
                monthString = "Sep " + day1;
                break;
            case 10:
                monthString = "Oct " + day1;
                break;
            case 11:
                monthString = "Nov " + day1;
                break;
            case 12:
                monthString = "Dec " + day1;
                monthString = "Dec " + day1;
                break;
            default:
                monthString = "Invalid month";
                break;
        }

        try{
            Connection mycon = DriverManager.getConnection("jdbc:mysql://localhost/health?autoReconnect=true&useSSL=false", "root", "SQL17^*@)!&neel");
            String sqlGetDate = "SELECT Date FROM bodyfatpercentage";
            String sqlGetBFP = "SELECT BFP FROM bodyfatpercentage";
            ResultSet rs = mycon.createStatement().executeQuery(sqlGetDate);
            ResultSet rs1 = mycon.createStatement().executeQuery(sqlGetBFP);

            while (rs.next()) {
                currStr = rs.getString("Date");
                addDataStrList.add(currStr);

            }
            while(rs1.next()){
                current = rs1.getInt("BFP");
                addDataList.add(current);
            }
            ArrayList<Object> joined = new ArrayList<>();
            joined.addAll(addDataStrList);
            joined.addAll(addDataList);

          int halfSize = joined.size()/2;
          int x = 0;
          int y = joined.size()/2;

          while(x < halfSize && y >= halfSize){
              Object dates = joined.get(x);
              Object fat = joined.get(y);
              data.getData().addAll(new XYChart.Data(dates, fat));
              y++;
              x++;
          }

        }catch(Exception e){
            e.printStackTrace();
        }


//THIS HAS TO STAY HERE. IT IS THE START OF THE STRENGTH PAGE.


// Add navigation buttons, and allows CSS to style this button separately from the rest.
        view1.getStylesheets().add("CSSList.css");

        bWeights.setOnAction(StrengthAction -> {
            scene.setRoot(view1);
            primaryStage.setTitle("Strength");
            primaryStage.setScene(scene);
            primaryStage.show();
        });

        FatPctg.setOnAction(StrengthAction -> {
            scene.setRoot(view);
            //primaryStage.setTitle("Strength");
            primaryStage.setScene(scene);
            primaryStage.show();
        });

//Strength menu button for strength page.
        Button bWeights1 = new Button();
        bWeights1.getStyleClass().add("bWeights");
        bWeights1.setText("Strength");
        GridPane.setConstraints(bWeights1,0,0);
        view1.getChildren().add(bWeights1);

//BFP button for strength page
        FatPctg.getStyleClass().add("bFat");
        FatPctg.setText("Body Fat");
        GridPane.setConstraints(FatPctg,1,0);
        view1.getChildren().add(FatPctg);

//Calendar

        Rectangle monday = new Rectangle();
        monday.setHeight(130);
        monday.setWidth(130);
        GridPane.setConstraints(monday,0,2);
        GridPane.setMargin(monday, new Insets(20,0,20,45));
        view1.getChildren().add(monday);
        monday.setFill(Color.gray(0.3569));
        monday.setStroke(Color.gray(0.2471));
        Label mondayLabel = new Label("Monday");
        GridPane.setConstraints(mondayLabel,0,2);
        GridPane.setMargin(mondayLabel, new Insets(5,0,115,45));
        mondayLabel.setPadding(new Insets(0,0,0,10));
        mondayLabel.setMaxWidth(130.0);
        view1.getChildren().add(mondayLabel);

        Rectangle tuesday = new Rectangle();
        createCalendar(tuesday, 1, "Tuesday", CS.excerciseChooser(arms));

        Rectangle wednesday = new Rectangle();
        createCalendar(wednesday, 2, "Wednesday", CS.excerciseChooser(arms));

        Rectangle thursday = new Rectangle();
        createCalendar(thursday, 3, "Thursday", CS.excerciseChooser(arms));

        Rectangle friday = new Rectangle();
        createCalendar(friday, 4, "Friday", CS.excerciseChooser(arms));

        Rectangle saturday = new Rectangle();
        createCalendar(saturday, 5, "Saturday", CS.excerciseChooser(arms));

        Rectangle sunday = new Rectangle();
        createCalendar(sunday, 6, "Sunday", CS.excerciseChooser(arms));

        Label monExcer = new Label(CS.excerciseChooser(arms));
        GridPane.setConstraints(monExcer,0,2);
        GridPane.setMargin(monExcer, new Insets(60,5,115,55));
        monExcer.getStyleClass().add("mondayExcerciseLabel");
        view1.getChildren().add(monExcer);
        //Need to apply CS method to each array of excercises, for each day, depending on how you set up body part days.
    }

//Method to create boxes for calendar
    public void createCalendar(Rectangle day, int columnNumber, String dayString, String excerString){
        Label dayLabel = new Label(dayString);
        Label excerLabel = new Label(excerString);
        day.setWidth(130.0);
        day.setHeight(130.0);
        GridPane.setMargin(day, new Insets(20,0,20,45));
        GridPane.setConstraints(day,columnNumber,2);
        day.setFill(Color.gray(0.3569));
        day.setStroke(Color.gray(0.2471));
        view1.getChildren().add(day);

        //Start of code for the day label
        GridPane.setConstraints(dayLabel, columnNumber,2);
        GridPane.setMargin(dayLabel, new Insets(5,0,115,45));
        dayLabel.setPadding(new Insets(0,0,0,10));
        dayLabel.setMaxWidth(130.0);
        view1.getChildren().add(dayLabel);

        //Start of code for excercise label. The height of the day label is 50.
        GridPane.setConstraints(excerLabel, columnNumber,2);
        GridPane.setMargin(excerLabel, new Insets(60,5,115,45));
        excerLabel.setPadding(new Insets(0,0,0,10));
        excerLabel.setMaxWidth(130.0);
        excerLabel.getStyleClass().add("excerciseLabel");
        view1.getChildren().add(excerLabel);
    }

    public void AddLabel(Label dayExcercises){
        //Need to make method apply excercise label to all

    }


// Sets actions of the Add button.
    public void ClickAdd() {
        DBAddMethod DBAdd = new DBAddMethod();
        if (add.getText().equals("Add")) {
//Abdomen data Retrieval.
            if (abdomen.getText().matches("(\\d+?)")) {
                count = 1 + count;
                abdomenCalc = Integer.parseInt(abdomen.getText());

            } else if (abdomen.getText().equals("")) {
                Alert notNumber = new Alert(Alert.AlertType.INFORMATION);
                notNumber.setTitle("Error");
                notNumber.setContentText("Enter a Number without a decimal point, or units");
                notNumber.setHeaderText("Error");
                notNumber.showAndWait();
                abdomen.clear();
            }

            //Neck data Retrieval.
            if (neck.getText().matches("(\\d+?)")) {
                count = 1 + count;
                neckCalc = Integer.parseInt(neck.getText());
            } else if (neck.getText().equals("")) {
                Alert notNumber = new Alert(Alert.AlertType.INFORMATION);
                notNumber.setTitle("Error");
                notNumber.setContentText("Enter a Number without a decimal point, or units");
                notNumber.setHeaderText("Error");
                notNumber.showAndWait();
                neck.clear();
            }

            //Height data Retrieval.
            if (height.getText().matches("(\\d+?)") && !abdomen.getText().equals("") && !neck.getText().equals("")) {
                count = 1 + count;
                heightCalc = Integer.parseInt(height.getText());

                // Calculate Body Fat Percentage.
                double calculation = 86.010 * Math.log10(abdomenCalc - neckCalc) - 70.041 * Math.log10(heightCalc) + 36.76;
                Integer calculationInt = (int) calculation;

                if(calculation >= 30){
                    String bip = "C:\\Users\\nkars\\Desktop\\Fat.m4a";
                    Media hit = new Media(new File(bip).toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(hit);
                    mediaPlayer.play();
                }
                DBAdd.AddMethod(monthString, calculationInt);

                BFP.add(calculationInt);
                data.getData().add(new XYChart.Data(monthString, calculationInt));
                abdomen.clear();
                neck.clear();
                height.clear();

            } else if (height.getText().equals("")) {
                Alert notNumber = new Alert(Alert.AlertType.INFORMATION);
                notNumber.setTitle("Error");
                notNumber.setContentText("Enter a Number without a decimal point, or units.");
                notNumber.setHeaderText("Error");
                notNumber.showAndWait();
                height.clear();
            }
        }
    }

// Sets actions of the Delete button.

    //ONE OF THE PROBLEMS WITH THIS METHOD OF DELETING DATA IS THAT IT CAN DELETE MULTIPLE DATA POINTS UNINTENTIONALLY IF THEY HAVE THE SAME VALUES.
    public void ClickDelete() {
        System.out.println(BodyFatPercentage.getSelectionModel().getSelectedItem());
        System.out.println(BodyFatPercentage.getSelectionModel().getSelectedItem());
        DeleteMethod DM = new DeleteMethod();
        int dataPiece = BodyFatPercentage.getSelectionModel().getSelectedItem();
        if (delete.getText().equals("Delete") && BodyFatPercentage.getItems().size() >= 0) {
            int selected = BodyFatPercentage.getSelectionModel().getSelectedIndex();
            BodyFatPercentage.getItems().remove(selected);
            data.getData().remove(selected);
            DM.deleteData(dataPiece);
            count--;
        } else {
            Alert nothingSelected = new Alert(Alert.AlertType.INFORMATION);
            nothingSelected.setTitle("Nothing Selected");
            nothingSelected.setHeaderText("Selection Problem");
            nothingSelected.setContentText("You must select an item from the list to be deleted");
            nothingSelected.showAndWait();
        }
        if (BodyFatPercentage.getItems().size() <= 0) {
            Alert empty = new Alert(Alert.AlertType.WARNING);
            empty.setTitle("Information Dialog");
            empty.setHeaderText("Warning:");
            empty.setContentText("The list is empty");
            empty.showAndWait();
        }
    }

}