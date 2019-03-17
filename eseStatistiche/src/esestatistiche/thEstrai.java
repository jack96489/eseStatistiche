package esestatistiche;


import java.util.Random;

public class thEstrai extends Thread {

    private datiCondivisi ptrDati;

    public thEstrai(datiCondivisi ptrDati) {
        this.ptrDati = ptrDati;
    }

    @Override
    public void run() {
        Random rn = new Random();
        final int BUFFER_SIZE = ptrDati.getLunghezzaBuffer();
        try {
            int daGenerare = ptrDati.getNumCaratteriDaGenerare();
            while (daGenerare > 0) {
                int daEstrarre = Math.min(daGenerare, BUFFER_SIZE);
                ptrDati.getLettoBufferPunti().acquire();
                ptrDati.getLettoBufferSpazi().acquire();
                ptrDati.resetBuffer();

                for (int j = 0; j < daEstrarre; j++) {
                    int val = rn.nextInt(100);
                    char valore;
                    if (val < ptrDati.getPercPunti()) {
                        valore = '.';
                        ptrDati.getVisualizzatoSem().acquire();
                        ptrDati.incNumPuntiInseriti();
//                        System.out.println("[DEBUG][ThEstrai]:\tStampo...");
                        ptrDati.getVisuallizzareSem().release();    //faccio partire il thread visualizza
                    } else if (val < ptrDati.getPercSpazi() + ptrDati.getPercPunti()) {
                        valore = ' ';
                        ptrDati.getVisualizzatoSem().acquire();
                        ptrDati.incNumSpaziInseriti();
//                        System.out.println("[DEBUG][ThEstrai]:\tStampo...");
                        ptrDati.getVisuallizzareSem().release();    //faccio partire il thread visualizza
                    } else
                        valore = (char) (rn.nextInt(25) + 65);

                    ptrDati.getVisualizzatoSem().acquire();
//                    System.out.println("[DEBUG][ThEstrai]:\tEstratto:\t"+valore);
//                    System.out.println("[DEBUG][ThEstrai]:\tStampo...");
                    ptrDati.setBufferAt(j, valore);
                    ptrDati.getVisuallizzareSem().release();    //faccio partire il thread visualizza

                }
                ptrDati.setNumCaratteriDaLeggere(daEstrarre);
                ptrDati.getScrittoBufferPunti().release();  //faccio partire i thread cerca
                ptrDati.getScrittoBufferSpazi().release();

                daGenerare -= BUFFER_SIZE;
            }
            ptrDati.setEstrazioneTerminata(true);
            ptrDati.getJoinSemaphore().release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
