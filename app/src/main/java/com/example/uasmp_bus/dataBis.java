package com.example.uasmp_bus;

public class dataBis {
    String bis_nama, bis_harga, bis_fasilitas, bis_waktuber, bis_waktuti;


    public dataBis(String bis_nama, String bis_harga, String bis_fasilitas, String bis_waktuber, String bis_waktuti){
        this.bis_nama = bis_nama;
        this.bis_harga = bis_harga;
        this.bis_fasilitas = bis_fasilitas;
        this.bis_waktuber = bis_waktuber;
        this.bis_waktuti = bis_waktuti;
    }
    public String getBis_nama() {
        return bis_nama;
    }

    public void setBis_nama(String bis_nama) {
        this.bis_nama = bis_nama;
    }

    public String getBis_harga() {
        return bis_harga;
    }

    public void setBis_harga(String bis_harga) {
        this.bis_harga = bis_harga;
    }

    public String getBis_fasilitas() {
        return bis_fasilitas;
    }

    public void setBis_fasilitas(String bis_fasilitas) {
        this.bis_fasilitas = bis_fasilitas;
    }

    public String getBis_waktuber() {
        return bis_waktuber;
    }

    public void setBis_waktuber(String bis_waktuber) {
        this.bis_waktuber = bis_waktuber;
    }

    public String getBis_waktuti() {
        return bis_waktuti;
    }

    public void setBis_waktuti(String bis_waktuti) {
        this.bis_waktuti = bis_waktuti;
    }
}
