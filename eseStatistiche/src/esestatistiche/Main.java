package esestatistiche;


import java.util.Scanner;

public class Main {

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Inserisci il numero di caratteri da estrarre: ");
        int caratteri = scan.nextInt();
        while (caratteri < 1) {
            System.err.println("Inserisci un numero maggiore di 0!!");
            caratteri = scan.nextInt();
        }

        final datiCondivisi dc = new datiCondivisi(caratteri);

        final thEstrai thEstrai = new thEstrai(dc);
        final thCerca thPunto = new thCerca(dc, '.'), thSpazio = new thCerca(dc, ' ');
        final thVisualizza thvis = new thVisualizza(dc);
        thvis.start();
        thEstrai.start();
        thPunto.start();
        thSpazio.start();

        try {
            dc.getJoinSemaphore().acquire(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(dc.getNumPuntiInseriti()==dc.getNumPuntiLetti()&&dc.getNumSpaziInseriti()==dc.getNumSpaziLetti())
            System.out.println("Estrazione avvenuta correttamente");
        else
            System.err.println("C'è stato un errore!");

    }

}
