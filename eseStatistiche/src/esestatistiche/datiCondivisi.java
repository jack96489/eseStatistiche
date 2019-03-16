package esestatistiche;


import java.util.concurrent.Semaphore;

public class datiCondivisi {

    private static final int LUNGHEZZA_BUFFER = 10;
    private static final int PERC_SPAZI = 10;
    private static final int PERC_PUNTI = 10;

    private final char[] buffer;

    private int numSpaziInseriti, numPuntiInseriti;
    private int numSpaziLetti, numPuntiLetti;
    private int numCaratteriDaGenerare;
    private int numCaratteriDaLeggere;
    private boolean estrazioneTerminata;

    private final Semaphore lettoBufferPunti, lettoBufferSpazi;
    private final Semaphore scrittoBufferPunti, scrittoBufferSpazi;
    private final Semaphore visuallizzareSem;

    public datiCondivisi(int numCaratteriDaGenerare) {
        buffer = new char[LUNGHEZZA_BUFFER];
        this.numCaratteriDaGenerare = numCaratteriDaGenerare;
        numCaratteriDaLeggere = 0;
        lettoBufferPunti = new Semaphore(1);
        lettoBufferSpazi = new Semaphore(1);
        scrittoBufferPunti = new Semaphore(0);
        scrittoBufferSpazi = new Semaphore(0);
        visuallizzareSem = new Semaphore(0);
        estrazioneTerminata = false;
    }

    public synchronized int getNumCaratteriDaGenerare() {
        return numCaratteriDaGenerare;
    }

    public int getLunghezzaBuffer() {
        return LUNGHEZZA_BUFFER;
    }

    public boolean isEstrazioneTerminata() {
        return estrazioneTerminata;
    }

    public void setEstrazioneTerminata(boolean estrazioneTerminata) {
        this.estrazioneTerminata = estrazioneTerminata;
    }

    public int getNumCaratteriDaLeggere() {
        return numCaratteriDaLeggere;
    }

    public void setNumCaratteriDaLeggere(int numCaratteriDaLeggere) {
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

}
