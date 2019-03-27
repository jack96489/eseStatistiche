package esestatistiche;

import java.io.IOException;
import java.util.Arrays;

public class thVisualizza extends Thread {
    private final datiCondivisi ptrDati;

    public thVisualizza(datiCondivisi ptrDati) {
        this.ptrDati = ptrDati;
    }

    @Override
    public void run() {
        try {
            while (!ptrDati.isRicercaTerminata()) {

                ptrDati.getVisualizzatoSem().release();

//                System.out.println("[DEBUG] [ThVisualizza]:\tAspetto qualcosa da stampare...");
                ptrDati.getVisuallizzareSem().acquire();

                ptrDati.visualizza();
                clearConsole();

//                System.out.println("[DEBUG] [ThVisualizza]:\tFine stampa...");
            }
            ptrDati.getJoinSemaphore().release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void clearConsole() {
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ex) {
        }
    }
}
