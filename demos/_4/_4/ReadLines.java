package demos._4._4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ReadLines {
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new File("demos/_4/_4/haiku.txt"));
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            System.out.println(line);
        }
        reader.close();
    }
}
