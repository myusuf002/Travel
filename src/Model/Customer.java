/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author myusuf, fairuz, diniyal
 */
public class Customer extends Orang implements Serializable{
    private String idCustomer;
    private String tujuan;

    
    public Customer(String idCustomer, String tujuan, String nama) {
        super(nama);
        this.idCustomer = idCustomer;
        this.tujuan = tujuan;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }
 
    @Override
    public String tampilBiodata(){
        return "Nama\t: " +super.getNama() + 
             "\nID\t: " +this.idCustomer +
             "\nTujuan\t: " +this.tujuan;
    }
    
    
    public void writeObject(){
        File file = new File("database/Customer");
        if (!file.exists()) {
            if (file.mkdirs()) {
                System.out.println("Created!");
            } else {
                System.out.println("Failed to create!");
            }
        }
        try{
            FileOutputStream fos = new FileOutputStream("database/Customer/" +this.getIdCustomer() +".dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
            System.out.println("Success!");
        }
        catch (IOException e){System.out.println("Failed. Error: "+e);}
    }
}
