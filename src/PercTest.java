import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PercTest {
  public static void main(String[] args) {
    Percolation p = null;
    int count = 0;
    try {
      File myObj = new File(args[0]);
      Scanner myReader = new Scanner(myObj);
      p = new Percolation(Integer.parseInt(myReader.nextLine()));
      while (myReader.hasNextLine() && count < 56) {
        String data = myReader.nextLine();
        // System.out.println(data);
        String[] coor = data.split(" ");
        // for (String c : coor) {
        // System.out.println(Integer.parseInt(c));
        // }
        try {
          p.open(Integer.parseInt(coor[0]), Integer.parseInt(coor[1]));
          count++;
        } catch (Exception e) {

        }

      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    // System.out.println(p.percolates());
    System.out.println(p.isFull(9, 1));
  }
}
