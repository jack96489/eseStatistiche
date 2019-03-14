import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int caratteri = 0;
        caratteri = scan.nextInt();
        datiCondivisi dc = new datiCondivisi(caratteri);

        thEstrai th = new thEstrai(dc);
        th.start();
    }

}
