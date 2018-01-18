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
public class Supir extends Staff implements Serializable{
    private Mobil mobil;

    public Supir(String nama, String nip, Mobil mobil) {
        super(nip, nama);
        this.mobil = mobil;
    }

    public Mobil getMobil() {
        return mobil;
    }

    public void setMobil(Mobil mobil) {
        this.mobil = mobil;
    }

    @Override
    public void lihatProgress(Transaksi T) {
            if(T.getSupir() == this){
            System.out.println("Progress  : ");
            System.out.println("Tanggal   : " +T.getTglTransaksi());
            System.out.println("Transaksi : " +T.getIdTransaksi());
            System.out.println("Customer  : " +T.getCustomer().getNama() +"(" +T.getCustomer().getIdCustomer() +")");
            System.out.println("Tujuan    : " +T.getCustomer().getTujuan());
        }  
    }

    @Override
    public String toString(){
        String temp = "";
        if(this.mobil != null){
            temp  = "\nMobil\t: " +this.mobil.getMerkMobil()
                   +"\nNopol\t: " +this.mobil.getNoPolisi();
        }
        return "NIP\t: " +super.getNip()
            +"\nNama\t: " +super.getNama() 
               +temp;
            
    }
    
     public void writeObject(){
        File file = new File("database/Supir");
        if (!file.exists()) {
            if (file.mkdirs()) {
                System.out.println("Created!");
            } else {
                System.out.println("Failed to create!");
            }
        }
        try{
            FileOutputStream fos = new FileOutputStream("database/Supir/" +this.getNip() +".dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
            System.out.println("Success!");
        }
        catch (IOException e){System.out.println("Failed. Error: "+e);}
    }    
}
