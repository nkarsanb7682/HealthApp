package sample;

import java.util.ArrayList;
import java.util.Random;

public class ChooseSchedule {
    Random rand = new Random();

    public String excerciseChooser(String[]excer){
        int size = excer.length;
        int index = rand.nextInt(size);

        return excer[index];
    }
}
