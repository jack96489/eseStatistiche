package esestatistiche;


import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class datiCondivisi {

    private static final int LUNGHEZZA_BUFFER = 10;
    private static final int PERC_SPAZI = 10;
    private static final int PERC_PUNTI = 10;

    private char[] buffer;

    private int numSpaziInseriti, numPuntiInseriti;
    private int numSpaziLetti, numPuntiLetti;
    private final int numCaratteriDaGenerare;
    private int numCaratteriDaLeggere;
    private boolean[] terminato;

    private final Semaphore lettoBufferPunti, lettoBufferSpazi;
    private final Semaphore scrittoBufferPunti, scrittoBufferSpazi;
    private final Semaphore visuallizzareSem, visualizzatoSem;
    private final Semaphore joinSemaphore;

    public datiCondivisi(int numCaratteriDaGenerare) {
        buffer = new char[LUNGHEZZA_BUFFER];
        this.numCaratteriDaGenerare = numCaratteriDaGenerare;
        numCaratteriDaLeggere = 0;
        lettoBufferPunti = new Semaphore(1);
        lettoBufferSpazi = new Semaphore(1);
        scrittoBufferPunti = new Semaphore(0);
        scrittoBufferSpazi = new Semaphore(0);
        visuallizzareSem = new Semaphore(0);
        visualizzatoSem = new Semaphore(0);
        joinSemaphore = new Semaphore(0);
        terminato = new boolean[]{false, false, false};
    }

    public synchronized int getNumCaratteriDaGenerare() {
        return numCaratteriDaGenerare;
    }

    public synchronized int getLunghezzaBuffer() {
        return LUNGHEZZA_BUFFER;
    }

    public synchronized boolean isEstrazioneTerminata() {
        return terminato[0];
    }

    public synchronized void setEstrazioneTerminata(boolean estrazioneTerminata) {
        this.terminato[0] = estrazioneTerminata;
    }

    public synchronized boolean isRicercaTerminata() {
        return terminato[1] && terminato[2];
    }

    public synchronized void setRicercaTerminata(boolean val, boolean index) {
        int pos = index ? 1 : 2;
        terminato[pos] = val;
    }

    public synchronized int getNumCaratteriDaLeggere() {
        return numCaratteriDaLeggere;
    }

    public synchronized void setNumCaratteriDaLeggere(int numCaratteriDaLeggere) {
        this.numCaratteriDaLeggere = numCaratteriDaLeggere;
    }

    public synchronized int getNumSpaziInseriti() {
        return numSpaziInseriti;
    }

    public synchronized void incNumSpaziInseriti() {
        this.numSpaziInseriti++;
    }

    public synchronized int getNumPuntiInseriti() {
        return numPuntiInseriti;
    }

    public synchronized void incNumPuntiInseriti() {
        this.numPuntiInseriti++;
    }

    public synchronized int getNumSpaziLetti() {
        return numSpaziLetti;
    }

    public synchronized void incNumSpaziLetti() {
        this.numSpaziLetti++;
    }

    public synchronized int getNumPuntiLetti() {
        return numPuntiLetti;
    }

    public synchronized void incNumPuntiLetti() {
        this.numPuntiLetti++;
    }

    public synchronized Semaphore getLettoBufferPunti() {
        return lettoBufferPunti;
    }

    public synchronized Semaphore getLettoBufferSpazi() {
        return lettoBufferSpazi;
    }

    public synchronized Semaphore getScrittoBufferPunti() {
        return scrittoBufferPunti;
    }

    public synchronized Semaphore getScrittoBufferSpazi() {
        return scrittoBufferSpazi;
    }

    public synchronized Semaphore getVisuallizzareSem() {
        return visuallizzareSem;
    }

    public synchronized Semaphore getVisualizzatoSem() {
        return visualizzatoSem;
    }

    public synchronized Semaphore getJoinSemaphore() {
        return joinSemaphore;
    }

    public synchronized int getPercSpazi() {
        return PERC_SPAZI;
    }

    public synchronized int getPercPunti() {
        return PERC_PUNTI;
    }

    public synchronized void setBufferAt(int index, char val) {
        buffer[index] = val;
    }

    public synchronized char getBufferAt(int index) {
        return buffer[index];
    }

    /**
     * ritorna il {@link #buffer} solo per la visualizzazione perchè NON garantisce la mutua esclusione (neanche con il synchronized)
     * per garantire la mutua esclusione ci sono i metodi {@link #getBufferAt(int)} e {@link #setBufferAt(int, char)}
     *
     * @return buffer
     */
    public char[] getBuffer() {
        return buffer;
    }

    public synchronized void resetBuffer() {
        buffer = new char[LUNGHEZZA_BUFFER];
    }

    public synchronized void visualizza(){
        System.out.println("Buffer:\t" + Arrays.toString(buffer));
        System.out.println("Numero di spazi insiriti:\t" + numSpaziInseriti);
        System.out.println("Numero di spazi letti:\t" + numSpaziLetti);
        System.out.println("Numero di punti inseriti:\t" + numPuntiInseriti);
        System.out.println("Numero di punti letti:\t" + numPuntiLetti);
        System.out.println("_____________________________________________");
    }
}
