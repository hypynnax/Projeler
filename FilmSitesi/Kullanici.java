package filmsitesi;

import java.util.ArrayList;

class Kullanici {

    private String adSoyad;
    private String email;
    private String sifre;
    private long tel;
    private double bakiye;
    private ArrayList<Film> sahipOlduguFilmler;
    private final boolean adminMi;
    private boolean uyeMi;

    public Kullanici(String adSoyad, String email, String sifre, long tel, boolean adminMi, boolean uyeMi) {
        if (adminMi) {
            this.adSoyad = adSoyad;
            this.sifre = sifre;
            this.adminMi = true;
            this.uyeMi = true;
        } else if (uyeMi) {
            this.adSoyad = adSoyad;
            this.email = email;
            this.sifre = sifre;
            this.tel = tel;
            this.bakiye = 0.0;
            this.sahipOlduguFilmler = new ArrayList<>();
            this.adminMi = false;
            this.uyeMi = true;
        } else {
            this.adSoyad = adSoyad;
            this.sifre = sifre;
            this.bakiye = 0.0;
            this.sahipOlduguFilmler = new ArrayList<>();
            this.adminMi = false;
            this.uyeMi = false;
        }
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    public double getBakiye() {
        return bakiye;
    }

    public void setBakiye(double bakiye) {
        this.bakiye = bakiye;
    }

    public String getSahipOlduguFilmler() {
        String filmler = "";
        int i = 1;
        for (Film film : sahipOlduguFilmler) {
            filmler += i + "-> " + film.getAd() + "\n";
            i++;
        }
        return filmler;
    }

    boolean uyeOlmaUcretiKontrolu(Kullanici kullanici, double uyelikUcreti) {
        boolean sonuc = false;
        if (kullanici.getBakiye() >= uyelikUcreti) {
            kullanici.setBakiye(kullanici.getBakiye() - uyelikUcreti);
            kullanici.setUyeMi(true);
            sonuc = true;
        }
        return sonuc;
    }

    public void setSahipOlduguFilmlerEkle(Film film) {
        sahipOlduguFilmler.add(film);
    }

    public void setSahipOlduguFilmlerCikar(Film film) {
        sahipOlduguFilmler.remove(film);
    }

    public boolean adminMi() {
        return adminMi;
    }

    public boolean uyeMi() {
        return uyeMi;
    }

    public void setUyeMi(boolean uyeMi) {
        this.uyeMi = uyeMi;
    }

    String kullaniciKontrol(Kullanici kullanici) {
        if (kullanici.adminMi) {
            return "admin";
        } else if (kullanici.uyeMi) {
            return "uye";
        } else {
            return "misafir kullan??c??";
        }
    }

    void kutuphane() {
        getSahipOlduguFilmler();
    }

    void filmSatinAl(Film film) {
        boolean bulunuyorMu = false;
        for (Film film1 : sahipOlduguFilmler) {
            if (film.getAd().equalsIgnoreCase(film1.getAd())) {
                bulunuyorMu = true;
            }
        }
        if (!bulunuyorMu) {
            if (getBakiye() >= film.getFiyat()) {
                setSahipOlduguFilmlerEkle(film);
                setBakiye(bakiye - film.getFiyat());
                System.out.println("????lem Ba??ar??l??");
                System.out.println(film.getAd() + " K??t??phaneye Eklendi.");
            } else {
                System.out.println("Yetersiz bakiye nedeni ile i??lem iptal edildi");
            }
        } else {
            System.out.println("K??t??phanede bulunuyor");
        }

    }

    void filmIadeEt(Film film) {
        setSahipOlduguFilmlerCikar(film);
        setBakiye(bakiye + film.getFiyat());
        System.out.println("????lem Ba??ar??l??");
        System.out.println("Film K??t??phaneden Kald??r??ld??");
    }

    @Override
    public String toString() {
        if (adminMi) {
            return "\n*******ADM??N B??LG??LER??*******"
                    + "\nAdmin Ad?? : " + getAdSoyad();
        } else if (uyeMi) {
            return "\n*******UYE B??LG??LER??*******"
                    + "\n??ye Ad?? : " + getAdSoyad()
                    + "\n??ye Bakiyesi : " + getBakiye()
                    + "\n??yenin Sahip Oldu??u Filmler : \n" + getSahipOlduguFilmler();
        } else {
            return "\n*******KULLANICI B??LG??LER??*******"
                    + "\nKullan??c?? Ad?? : " + getAdSoyad()
                    + "\nKullan??c??n??n Sahip Oldu??u Filmler : \n" + getSahipOlduguFilmler();
        }
    }
}
