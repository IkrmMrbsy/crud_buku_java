package model;

public class Buku {
    private String kodeBuku;
    private String judul;
    private String pengarang;
    private int tahun;

    public Buku(String kodeBuku, String judul, String pengarang, int tahun) {
        this.kodeBuku = kodeBuku;
        this.judul = judul;
        this.pengarang = pengarang;
        this.tahun = tahun;
    }

    public String getKodeBuku() { return kodeBuku; }
    public String getJudul() { return judul; }
    public String getPengarang() { return pengarang; }
    public int getTahun() { return tahun; }
}
