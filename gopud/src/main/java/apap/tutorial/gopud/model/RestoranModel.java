package apap.tutorial.gopud.model;

public class RestoranModel {
    private String idRestoran;
    private String nama;
    private String alamat;
    private Integer nomorTelepon;

    public void setIdRestoran(String idRestoran) {
        this.idRestoran = idRestoran;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setNomorTelepon(Integer nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public RestoranModel(String idRestoran, String nama, String alamat, Integer nomorTelepon) {
        this.idRestoran = idRestoran;
        this.nama = nama;
        this.alamat = alamat;
        this.nomorTelepon = nomorTelepon;
    }

    public String getIdRestoran() {
        return idRestoran;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public Integer getNomorTelepon() {
        return nomorTelepon;
    }
}
