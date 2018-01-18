/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author myusuf, fairuz, diniyal
 */
public abstract class Staff extends Orang  implements Serializable{
    private String nip;

    public Staff(String nip, String nama) {
        super(nama);
        this.nip = nip;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    @Override
    public String tampilBiodata(){
        return "Nama : " +super.getNama() + 
             "\nNIP  : " +this.nip;
    }
    
    public abstract void lihatProgress(Transaksi T);
}
