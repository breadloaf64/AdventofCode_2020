import java.io.IOException;

import Days.*;

public class code {

    public static void main(String[] args) throws IOException {
        Day18.solve();
        //printAllAnswersAndSolutions();
    }

    public static void printAllAnswers() throws IOException {
        Day1.solve();
        Day2.solve();
        Day3.solve();
        Day4.solve();
        Day5.solve();
        Day6.solve();
    }

    public static void printAllAnswersAndSolutions() throws IOException {
        Day1.solve();
        System.out.println("--Solutions: 658899, 155806250");
        Day2.solve();
        System.out.println("--Solutions: 622, 263");
        Day3.solve();
        System.out.println("--Solutions: 265, 3154761400");
        Day4.solve();
        System.out.println("--Solutions: 216, ___");
        Day5.solve();
        System.out.println("--Solutions: 818, 559");
        Day6.solve();
        System.out.println("--Solutions: 6249, 3103");
    }
}


