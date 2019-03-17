package esestatistiche;


public class thCerca extends Thread {
    private final datiCondivisi ptrDati;
    private final char daCercare;

    public thCerca(datiCondivisi ptrDati, char daCercare) {
        this.ptrDati = ptrDati;
        this.daCercare = daCercare;
    }

    @Override
    public void run() {
        try {
            do {
                if (daCercare == '.')
                    ptrDati.getScrittoBufferPunti().acquire();
                else
                    ptrDati.getScrittoBufferSpazi().acquire();

                for (int i = 0; i < ptrDati.getNumCaratteriDaLeggere(); i++) {
                    char valore = ptrDati.getBufferAt(i);

                    if (valore == daCercare) {
                        ptrDati.getVisualizzatoSem().acquire();
                        if (valore == '.')
                            ptrDati.incNumPuntiLetti();
                        else if (valore == ' ')
                            ptrDati.incNumSpaziLetti();
                        System.out.println("[DEBUG][ThCerca]:\tStampo...");
                        ptrDati.getVisuallizzareSem().release();
                    }
                    System.out.println("[DEBUG][ThCerca]:\tThread " + daCercare + ":\ttrovato: " + valore);
                }
                if (daCercare == '.')
                    ptrDati.getLettoBufferPunti().release();
                else
                    ptrDati.getLettoBufferSpazi().release();
            } while (!ptrDati.isEstrazioneTerminata());
            if (daCercare == '.')
                ptrDati.setRicercaTerminata(true, true);
            else
                ptrDati.setRicercaTerminata(true, false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}