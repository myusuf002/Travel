/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;
import View.ViewDriversPage;
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
public class ControllerViewDriversPage  extends MouseAdapter implements ActionListener {
    static List<Mobil> listMobil = new ArrayList();
    static List<Supir> listSupir = new ArrayList();
    static List<Admin> listAdmin = new ArrayList();
    static List<Customer> listCustomer = new ArrayList();
    static List<Transaksi> listTransaksi = new ArrayList();
    
    String[] listDaftarSupir;
    int idMobil;
    String noPol;
    String kapasitas;
    String merk;
    
    private ViewDriversPage view;
    private Admin admin;

    public ControllerViewDriversPage() {
        this.view = new ViewDriversPage();
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
        
        String[] listDaftarSupir = new String[listSupir.size()];
        for(int i = 0; i < listDaftarSupir.length; i++){
            listDaftarSupir[i] = String.valueOf(listSupir.get(i).getNip());
        }
        view.setjListDrivers(listDaftarSupir);
    }
    private String[] getDaftarSupir(){
        String[] listDaftarSupir = new String[listSupir.size()];
        for(int i = 0; i < listDaftarSupir.length; i++){
            listDaftarSupir[i] = String.valueOf(listSupir.get(i).getNip());
        }
        return listDaftarSupir;
    }
    
    public void mousePressed(MouseEvent me){
        Object source = me.getSource();
        if(source.equals(view.getjListDrivers())){
            int i = view.getSelectedtDriver();
            Supir s = listSupir.get(i);
            view.setjTextAreaDetails(s.toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source.equals(view.getjButtonEdit())){
            int i = view.getSelectedtDriver();
            
            kapasitas = JOptionPane.showInputDialog("Name", listSupir.get(i).getNama());
            if(kapasitas == null){
                kapasitas = listSupir.get(i).getNama();
            }
            
            Supir s = new Supir(kapasitas, listSupir.get(i).getNip(), listSupir.get(i).getMobil());
            s.writeObject();
            listSupir.set(i, s);
            view.ResetView();
            view.setjListDrivers(getDaftarSupir());
            view.setjLabelStatus("Edited");
        }
        else if(source.equals(view.getjButtonDelete())){
            int i = view.getSelectedtDriver();
            try{
            //Specify the file name and path
            File file = new File("database/Supir/" +listSupir.get(i).getNip() +".dat");
                
                if(file.delete()){System.out.println(file.getName() + " is deleted!");}
                else{System.out.println("Delete failed: " +file.getName() +" didn't delete");}
            }
            catch(Exception ex){
                System.out.println("Exception occurred");
                ex.printStackTrace();
            }
            listSupir.remove(i);
            view.ResetView();
            view.setjListDrivers(getDaftarSupir());
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
