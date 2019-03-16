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
            while (!ptrDati.isEstrazioneTerminata()) {
                ptrDati.getVisuallizzareSem().acquire();

                System.out.println("Buffer:\t" + Arrays.toString(ptrDati.getBuffer()));
                System.out.println("Numero di spazi insiriti:\t"+ptrDati.getNumSpaziInseriti());
                System.out.println("Numero di spazi letti:\t"+ptrDati.getNumSpaziLetti());
                System.out.println("Numero di punti inseriti:\t"+ptrDati.getNumPuntiInseriti());
                System.out.println("Numero di punti letti:\t"+ptrDati.getNumPuntiLetti());
                System.out.println("_____________________________________________");
                clearConsole();

                ptrDati.getVisualizzatoSem().release();
            }
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
