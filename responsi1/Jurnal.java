import java.util.Objects;

class Jurnal {
    private String judul;
    private String penulis;
    private String topik;
    private String konten;
    private int tahunPublikasi;

    public Jurnal(String judul, String penulis, String topik, String konten, int tahunPublikasi) {
        this.judul = judul;
        this.penulis = penulis;
        this.topik = topik;
        this.konten = konten;
        this.tahunPublikasi = tahunPublikasi;
    }

    public String getJudul() { return judul; }
    public String getPenulis() { return penulis; }
    public String getTopik() { return topik; }
    public String getKonten() { return konten; }
    public int getTahunPublikasi() { return tahunPublikasi; }

    @Override
    public String toString() {
        return "Judul: " + judul + ", Penulis: " + penulis + ", Topik: " + topik + ", Tahun: " + tahunPublikasi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jurnal jurnal = (Jurnal) o;
        return tahunPublikasi == jurnal.tahunPublikasi &&
            Objects.equals(judul, jurnal.judul) &&
            Objects.equals(penulis, jurnal.penulis) &&
            Objects.equals(topik, jurnal.topik);
    }

    @Override
    public int hashCode() {
        return Objects.hash(judul, penulis, topik, tahunPublikasi);
    }
}

