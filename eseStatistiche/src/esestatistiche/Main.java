package esestatistiche;


import java.util.Scanner;

public class Main {

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int caratteri = 0;
        caratteri = scan.nextInt();
        datiCondivisi dc = new datiCondivisi(caratteri);

        thEstrai th = new thEstrai(dc);
        thCerca thPunto =new thCerca(dc,'.'),thSpazio =new thCerca(dc,' ');
        thVisualizza thvis = new thVisualizza(dc);
        thvis.start();
        thPunto.start();
        thSpazio.start();
        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
