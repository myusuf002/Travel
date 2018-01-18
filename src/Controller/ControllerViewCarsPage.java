/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;
import View.ViewCarsPage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import jdk.nashorn.internal.runtime.JSType;


/**
 *
 * @author myusuf, fairuz, diniyal
 */
public class ControllerViewCarsPage  extends MouseAdapter implements ActionListener {
    static List<Mobil> listMobil = new ArrayList();
    static List<Supir> listSupir = new ArrayList();
    static List<Admin> listAdmin = new ArrayList();
    static List<Customer> listCustomer = new ArrayList();
    static List<Transaksi> listTransaksi = new ArrayList();
    
    String[] listDaftarMobil;
    int idMobil;
    String noPol;
    String kapasitas;
    String merk;
    
    private ViewCarsPage view;
    private Admin admin;

    public ControllerViewCarsPage() {
        this.view = new ViewCarsPage();
        view.addActionListener(this);
        view.addMouseAdapter(this);
        view.setVisible(true);
        if(ControllerLoginPage.admin != null){
            admin = ControllerLoginPage.admin;
            view.setjLabelAdminName(admin.getNama());
        }
        
        listMobil = Main.listMobil;
        listSupir = Main.listSupir;
        listAdmin = Main.listAdmin;
        listCustomer = Main.listCustomer;
        listTransaksi = Main.listTransaksi;
        
        String[] listDaftarMobil = new String[listMobil.size()];
        for(int i = 0; i < listDaftarMobil.length; i++){
            listDaftarMobil[i] = String.valueOf(listMobil.get(i).getIdMobil());
        }
        view.setjListCars(listDaftarMobil);
    }
    private String[] getDaftarMobil(){
        String[] listDaftarMobil = new String[listMobil.size()];
        for(int i = 0; i < listDaftarMobil.length; i++){
            listDaftarMobil[i] = String.valueOf(listMobil.get(i).getIdMobil());
        }
        return listDaftarMobil;
    }
    
    public void mousePressed(MouseEvent me){
        Object source = me.getSource();
        if(source.equals(view.getjListCars())){
            int i = view.getSelectedtCar();
            Mobil m = listMobil.get(i);
            view.setjTextAreaDetails(m.toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source.equals(view.getjButtonEdit())){
            int i = view.getSelectedtCar();
            
            noPol = JOptionPane.showInputDialog("Police Number", listMobil.get(i).getNoPolisi());
            if(noPol == null){
                noPol = listMobil.get(i).getNoPolisi();
            }
            
            kapasitas = JOptionPane.showInputDialog("Capacity", listMobil.get(i).getKapasitas());
            if(kapasitas == null){
                kapasitas = listMobil.get(i).getKapasitas();
            }
            
            merk = JOptionPane.showInputDialog("Brand", listMobil.get(i).getMerkMobil());
            if(merk == null){
                merk = listMobil.get(i).getMerkMobil();
            }
            
            Mobil m = new Mobil(listMobil.get(i).getIdMobil(), noPol, kapasitas, merk);
            m.writeObject();
            listMobil.set(i, m);
            view.ResetView();
            view.setjListCars(getDaftarMobil());
            view.setjLabelStatus("Edited");
        }
        else if(source.equals(view.getjButtonDelete())){
            int i = view.getSelectedtCar();
            try{
            //Specify the file name and path
            File file = new File("database/Mobil/" +listMobil.get(i).getIdMobil() +".dat");
                
                if(file.delete()){System.out.println(file.getName() + " is deleted!");}
                else{System.out.println("Delete failed: " +file.getName() +" didn't delete");}
            }
            catch(Exception ex){
                System.out.println("Exception occurred");
                ex.printStackTrace();
            }
            listMobil.remove(i);
            view.ResetView();
            view.setjListCars(getDaftarMobil());
            view.setjLabelStatus("Deleted");
        }
        
        else if(source.equals(view.getjButtonHome())){
            view.dispose();
            new ControllerAdminPage();
        }
        
        else if(source.equals(view.getjButtonSignOut())){
            view.dispose();
            new ControllerLoginPage();
            ControllerLoginPage.admin = null;
        }
    }
}
