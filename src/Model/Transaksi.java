/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.*;

/**
 *
 * @author myusuf, fairuz, diniyal
 */
public class Transaksi implements Serializable {
    private int idTransaksi;
    private Admin admin;
    private Supir supir;
    private Customer customer;
    private String tglTransaksi;
    private String harga;

    public Transaksi(int idTransaksi, Admin admin, Supir supir, Customer customer, String tglTransaksi, String harga) {
        this.idTransaksi = idTransaksi;
        this.admin = admin;
        this.supir = supir;
        this.customer = customer;
        this.tglTransaksi = tglTransaksi;
        this.harga = harga;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Supir getSupir() {
        return supir;
    }

    public void setSupir(Supir supir) {
        this.supir = supir;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void addCustomer(String nama, String idCustomer, String tujuan) {
        customer = new Customer(nama,idCustomer,tujuan);
    }
    
    public String getTglTransaksi() {
        return tglTransaksi;
    }

    public void setTglTransaksi(String tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
    
    @Override
    public String toString(){
        String temp = "";
        if(this.supir != null){
            temp  = this.supir.toString();
        }
        String temp1 = "";
        if(this.customer != null){
            temp = this.customer.toString();
        }
        
        return "Id\t: " +this.idTransaksi
            +"\nTanggal\t: " +this.tglTransaksi
            +"\nHarga\t: Rp. " +this.harga;
    }
    
   public void writeObject(){
        File file = new File("database/Transaksi");
        if (!file.exists()) {
            if (file.mkdirs()) {
                System.out.println("Created!");
            } else {
                System.out.println("Failed to create!");
            }
        }
        try{
            FileOutputStream fos = new FileOutputStream("database/Transaksi/" +this.getIdTransaksi() +".dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
            System.out.println("Success!");
        }
        catch (IOException e){System.out.println("Failed. Error: "+e);}
    }
}
