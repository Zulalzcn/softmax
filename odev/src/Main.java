import java.util.ArrayList;
import java.util.List;

public class Main {

    // Mahalle verilerini tutacak sınıf
    static class Mahalle {
        String isim;
        double nufus;
        double ulasim;
        double maliyet;
        double cevre;
        double sosyal;
        double rawScore;
        double softmaxScore;

        public Mahalle(String isim, double nufus, double ulasim, double maliyet, double cevre, double sosyal) {
            this.isim = isim;
            this.nufus = nufus;
            this.ulasim = ulasim;
            this.maliyet = maliyet;
            this.cevre = cevre;
            this.sosyal = sosyal;
        }
    }

    public static void main(String[] args) {
        // 1. Adım: Her mahalle için sentetik kriter değerlerinin tanımlanması
        List<Mahalle> mahalleler = new ArrayList<>();
        mahalleler.add(new Mahalle("Mahalle1", 8000, 5, 300, 7, 9));
        mahalleler.add(new Mahalle("Mahalle2", 6000, 8, 250, 6, 8));
        mahalleler.add(new Mahalle("Mahalle3", 9000, 6, 400, 5, 7));

        // 2. Adım: Kriter ağırlıklarının belirlenmesi (toplamı 1 olacak şekilde)
        double weightNufus   = 0.3;   // Nüfus yoğunluğu
        double weightUlasim  = 0.2;   // Ulaşım altyapısı
        double weightMaliyet = 0.2;   // Düşük maliyet tercih edilir.
        double weightCevre   = 0.15;  // Çevresel etki
        double weightSosyal  = 0.15;  // Sosyal fayda

        // 3. Adım: Normalizasyon için min-max değerlerinin hesaplanması
        double minNufus   = Double.MAX_VALUE, maxNufus   = Double.MIN_VALUE;
        double minUlasim  = Double.MAX_VALUE, maxUlasim  = Double.MIN_VALUE;
        double minMaliyet = Double.MAX_VALUE, maxMaliyet = Double.MIN_VALUE;
        double minCevre   = Double.MAX_VALUE, maxCevre   = Double.MIN_VALUE;
        double minSosyal  = Double.MAX_VALUE, maxSosyal  = Double.MIN_VALUE;

        for (Mahalle m : mahalleler) {
            if (m.nufus < minNufus) minNufus = m.nufus;
            if (m.nufus > maxNufus) maxNufus = m.nufus;

            if (m.ulasim < minUlasim) minUlasim = m.ulasim;
            if (m.ulasim > maxUlasim) maxUlasim = m.ulasim;

            if (m.maliyet < minMaliyet) minMaliyet = m.maliyet;
            if (m.maliyet > maxMaliyet) maxMaliyet = m.maliyet;

            if (m.cevre < minCevre) minCevre = m.cevre;
            if (m.cevre > maxCevre) maxCevre = m.cevre;

            if (m.sosyal < minSosyal) minSosyal = m.sosyal;
            if (m.sosyal > maxSosyal) maxSosyal = m.sosyal;
        }

        // 4. Adım: Her mahalle için normalizasyon ve ağırlıklı ham skor hesaplaması
        for (Mahalle m : mahalleler) {
            // Normalizasyon: Diğer kriterler için (value - min) / (max - min)
            double normNufus   = (m.nufus - minNufus) / (maxNufus - minNufus);
            double normUlasim  = (m.ulasim - minUlasim) / (maxUlasim - minUlasim);
            // "Maliyet" için düşük değer daha iyi olduğundan ters normalizasyon uygulanır:
            double normMaliyet = (maxMaliyet - m.maliyet) / (maxMaliyet - minMaliyet);
            double normCevre   = (m.cevre - minCevre) / (maxCevre - minCevre);
            double normSosyal  = (m.sosyal - minSosyal) / (maxSosyal - minSosyal);

            // Ağırlıklı ham skorun hesaplanması
            m.rawScore = normNufus   * weightNufus +
                    normUlasim  * weightUlasim +
                    normMaliyet * weightMaliyet +
                    normCevre   * weightCevre +
                    normSosyal  * weightSosyal;
        }

        // 5. Adım: Softmax fonksiyonunun uygulanması
        // Öncelikle en yüksek ham skoru buluyoruz (numerik kararlılık için)
        double maxScore = Double.NEGATIVE_INFINITY;
        for (Mahalle m : mahalleler) {
            if (m.rawScore > maxScore) {
                maxScore = m.rawScore;
            }
        }

        double sumExp = 0.0;
        for (Mahalle m : mahalleler) {
            sumExp += Math.exp(m.rawScore - maxScore);
        }

        for (Mahalle m : mahalleler) {
            m.softmaxScore = Math.exp(m.rawScore - maxScore) / sumExp;
        }

        // 6. Adım: Sonuçların ekrana yazdırılması
        System.out.println("Final Softmax Skorları:");
        for (Mahalle m : mahalleler) {
            System.out.printf("%s: %.4f\n", m.isim, m.softmaxScore);
        }

        // En yüksek softmax skoruna sahip mahalleyi belirleme
        Mahalle best = null;
        for (Mahalle m : mahalleler) {
            if (best == null || m.softmaxScore > best.softmaxScore) {
                best = m;
            }
        }
        if (best != null) {
            System.out.println("\nEn uygun güzergah, " + best.isim + " mahallesinde değerlendirilmektedir.");
        }
    }
}
