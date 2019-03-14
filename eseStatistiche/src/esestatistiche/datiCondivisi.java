import java.util.concurrent.Semaphore;

public class datiCondivisi {

    private static final int LUNGHEZZA_BUFFER = 10;
    private static final int PERC_SPAZI=10;
    private static final int PERC_PUNTI=10;

    private char[] buffer;

    private int numSpaziInseriti, numPuntiInseriti;
    private int numSpaziLetti, numPuntiLetti;
    private final int numCaratteri;

    private Semaphore lettoBuffer1, lettoBuffer2;
    private Semaphore scrittoBuffer1, scrittoBuffer2;

    public datiCondivisi(int numCaratteri) {
        buffer = new char[LUNGHEZZA_BUFFER];
        this.numCaratteri = numCaratteri;
        lettoBuffer1=new Semaphore(1);
        lettoBuffer2=new Semaphore(1);
        scrittoBuffer1=new Semaphore(0);
        scrittoBuffer2=new Semaphore(0);

    }

    public synchronized int getNumCaratteri() {
        return numCaratteri;
    }

    public synchronized char[] getBuffer() {
        return buffer;
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

    public synchronized Semaphore getLettoBuffer1() {
        return lettoBuffer1;
    }

    public synchronized Semaphore getLettoBuffer2() {
        return lettoBuffer2;
    }

    public synchronized Semaphore getScrittoBuffer1() {
        return scrittoBuffer1;
    }

    public synchronized Semaphore getScrittoBuffer2() {
        return scrittoBuffer2;
    }

    public synchronized int getPercSpazi() {
        return PERC_SPAZI;
    }

    public synchronized int getPercPunti() {
        return PERC_PUNTI;
    }
}
