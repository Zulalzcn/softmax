Kırklareli Toplu Taşıma Hattı Planlaması

Bu proje, Kırklareli'nin üç farklı mahallesi için yeni bir toplu taşıma hattı güzergahı belirlemek üzere hazırlanmıştır. Projede, her mahalle için aşağıdaki kriterler değerlendirilmiştir:

Nüfus Yoğunluğu
Mevcut Ulaşım Altyapısı
Maliyet Analizi
Çevresel Etki
Sosyal Fayda
Veriler sentetik olarak belirlenmiş olup, her kriter normalize edildikten sonra, ağırlıklandırma ve Softmax algoritması kullanılarak her mahalle için göreceli başarı oranı (olasılık) hesaplanmaktadır. Böylece, en yüksek softmax skoruna sahip mahalle, toplu taşıma hattı için en uygun güzergah olarak seçilmektedir.

Proje Açıklaması: Bu projede, her mahalle için belirlenen kriterler üzerinden sentetik veriler kullanılarak toplu taşıma hattı güzergahı seçimi yapılmaktadır. Projenin temel adımları şunlardır:

Veri Tanımlama:
Her mahalle için nüfus, ulaşım, maliyet, çevresel etki ve sosyal fayda değerleri tanımlanır.
Ağırlıklandırma: Her kriter, proje gereksinimlerine göre belirlenen ağırlıklarla değerlendirilir (örneğin, nüfus %30, ulaşım %20, maliyet %20, çevre %15, sosyal %15).
Normalizasyon: Farklı ölçeklerdeki verileri aynı aralığa çekmek için min-max normalizasyon yöntemi uygulanır. Maliyet kriterinde düşük değerler tercih edildiğinden ters normalizasyon yapılır.
Ham Skor Hesaplaması: Normalize edilmiş değerler, ilgili ağırlıklarla çarpılarak her mahalle için bir ham skor hesaplanır.
Softmax Uygulaması: Hesaplanan ham skorlar üzerinden softmax algoritması uygulanır; böylece her mahalle için göreceli olasılık değeri elde edilir.
Sonuçların Değerlendirilmesi: En yüksek softmax skoruna sahip mahalle, toplu taşıma hattının açılacağı en uygun güzergah olarak belirlenir.

Özellikler:
Çok Kriterli Karar Verme: Nüfus, ulaşım, maliyet, çevre ve sosyal fayda gibi kriterler detaylı olarak değerlendirilir.
Normalizasyon: Farklı ölçeklerdeki veriler, kıyaslama yapılabilir hale getirilir.
Softmax Hesaplaması: Ham skorlar üzerinden hesaplanan softmax değerleri, her mahallenin göreceli başarı oranını gösterir.
Kolay Genişletilebilirlik: Proje, gerçek verilerle entegre edilebilir veya yeni kriterler eklenerek genişletilebilir.
Kullanılan Teknolojiler:

Java:
Projenin temel programlama dili.
Java Collections Framework: 
Veri yapıları ve liste işlemleri için kullanılmıştır.
Matematiksel İşlemler:
Ham skor ve softmax hesaplamaları için Java’nın Math sınıfı kullanılmıştır.

Örnek Çıktı:
Final Softmax Skorları: Mahalle1: 0.4231 Mahalle2: 0.3582 Mahalle3: 0.2187

En uygun güzergah, Mahalle1 mahallesinde değerlendirilmektedir.
