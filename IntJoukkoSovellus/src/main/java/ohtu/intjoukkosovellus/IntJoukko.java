package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this.ljono = new int[KAPASITEETTI];
        alustaJono();
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        this.ljono = new int[kapasiteetti];
        alustaJono();
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            return;
        }
        this.ljono = new int[kapasiteetti];
        alustaJono();
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        }
        ljono[alkioidenLkm] = luku;
        alkioidenLkm++;
        if (alkioidenLkm % ljono.length == 0) {
            int[] vanhaTaulukko = ljono;
            ljono = new int[ljono.length + kasvatuskoko];
            kopioiTaulukko(vanhaTaulukko, ljono);
        }
        return true;
    }

    public boolean poista(int luku) {
        if (!kuuluu(luku)) {
            return false;
        }
        int poistettava = haePoistettava(luku);
        ljono[poistettava] = 0;
        jarjestaTaulukko(poistettava);
        alkioidenLkm--;
        return true;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(i);
        }
        return z;
    }

    public void alustaJono() {
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < ljono.length; i++) {
            if (luku == ljono[i]) {
                return true;
            }
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int haePoistettava(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return i;
            }
        }
        return -1;
    }

    public void jarjestaTaulukko(int poistettu) {
        int apu = -1;
        for (int i = poistettu; i < alkioidenLkm; i++) {
            apu = ljono[i];
            ljono[i] = ljono[i + 1];
            ljono[i + 1] = apu;
        }
    }

    @Override
    public String toString() {
        switch (alkioidenLkm) {
            case 0:
                return "{}";
            case 1:
                return "{" + ljono[0] + "}";
            default:
                String tuotos = "{";
                for (int i = 0; i < alkioidenLkm - 1; i++) {
                    tuotos += ljono[i] + ", ";
                }
                tuotos += ljono[alkioidenLkm - 1] + "}";
                return tuotos;
        }
    }

}
