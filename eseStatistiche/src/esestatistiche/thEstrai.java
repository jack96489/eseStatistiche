import java.util.Random;

public class thEstrai extends Thread {

    private datiCondivisi ptrDati;

    public thEstrai(datiCondivisi ptrDati) {
        this.ptrDati = ptrDati;
    }

    @Override
    public void run() {
        Random rn = new Random();
        //try {
        double cicli = Math.ceil(ptrDati.getNumCaratteri() / 10.0);
        for (int i = 0; i < cicli; i++) {
            int daLeggere = Math.min(ptrDati.getNumCaratteri() - (i * 10), 10);
            //ptrDati.getLettoBuffer1().acquire();
            //ptrDati.getLettoBuffer2().acquire();
            for (int j = 0; j < daLeggere; j++) {
                int val = rn.nextInt(100);
                char valore;
                if (val < ptrDati.getPercPunti())
                    valore = '.';
                else if (val < ptrDati.getPercSpazi() + ptrDati.getPercPunti())
                    valore = ' ';
                else
                    valore = (char) (rn.nextInt(25) + 65);

                ptrDati.getBuffer()[j] = valore;
                System.out.println(valore);
            }
            ptrDati.getScrittoBuffer1().release();
            ptrDati.getScrittoBuffer2().release();
        }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
}
