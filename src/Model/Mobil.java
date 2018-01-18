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
public class Mobil implements Serializable {
    private int idMobil;
    private String noPolisi;
    private String kapasitas;
    private String merkMobil;

    public Mobil(int idMobil, String noPolisi, String kapasitas, String merkMobil) {
        this.idMobil = idMobil;
        this.noPolisi = noPolisi;
        this.kapasitas = kapasitas;
        this.merkMobil = merkMobil;
    }

    public int getIdMobil() {
        return idMobil;
    }

    public void setIdMobil(int idMobil) {
        this.idMobil = idMobil;
    }

    public String getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(String kapasitas) {
        this.kapasitas = kapasitas;
    }

    public String getMerkMobil() {
        return merkMobil;
    }

    public void setMerkMobil(String merkMobil) {
        this.merkMobil = merkMobil;
    }

    public String getNoPolisi() {
        return noPolisi;
    }

    public void setNoPolisi(String noPolisi) {
        this.noPolisi = noPolisi;
    }
    
    @Override
    public String toString(){
        return "Id Mobil\t: " +idMobil
            +"\nNo Pol\t: " +noPolisi
            +"\nKapasitas\t: " +kapasitas +" Orang"
            +"\nMerk\t: " +merkMobil;
              
    }

    public void writeObject(){
        File file = new File("database/Mobil");
        if (!file.exists()) {
            if (file.mkdirs()) {
                System.out.println("Created!");
            } else {
                System.out.println("Failed to create!");
            }
        }
        try{
            FileOutputStream fos = new FileOutputStream("database/Mobil/" +this.getIdMobil() +".dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
            System.out.println("Success!");
        }
        catch (IOException e){System.out.println("Failed. Error: "+e);}
    }   

}
