package com.nursultan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

public class addValue {
    int id = (int)(Math.random()*1500);
    JFrame frame = new JFrame("Add medicine");
    JLabel idLbl = new JLabel("ID");
    JLabel idVal = new JLabel(Integer.toString(id));
    JLabel nameLbl = new JLabel("Name");
    JTextField nameTxt = new JTextField();
    JLabel priceLbl = new JLabel("Price");
    JSpinner priceTxt = new JSpinner(new SpinnerNumberModel(0,0,10000000,1));
    JLabel dateLbl = new JLabel("Expiration Date");
    JFormattedTextField dateTxt = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
    JLabel manufLbl = new JLabel("Manufacturer");
    JTextField manufTxt = new JTextField();
    JButton ins = new JButton("Save");
    ArrayList<Component> comps = new ArrayList<>();
    Gui fr;
    public addValue(Gui fr) {
        this.fr = fr;
        arr();
        for(int i=0; i < comps.size();i++) {
            comps.get(i).setVisible(true);
            comps.get(i).setBounds(10, 10 + i * 40, 100, 30);
            frame.add(comps.get(i));
        }
        for (Component comp :
                comps) {
            frame.add(comp);
        }
        frame.setBounds(100,100,500,500);
        frame.setLayout(null);
    }
    void Show(boolean isShown){
        frame.setVisible(isShown);
    }
    void arr(){
        comps.add(idLbl);
        comps.add(idVal);
        comps.add(nameLbl);
        comps.add(nameTxt);
        comps.add(priceLbl);
        comps.add(priceTxt);
        comps.add(dateLbl);
        comps.add(dateTxt);
        comps.add(manufLbl);
        comps.add(manufTxt);
        comps.add(ins);
        ins.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        save();
                    }
                }
        );
    }

    void save(){

        if(nameTxt.getText().isEmpty() || dateTxt.getText().isEmpty() || manufTxt.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(frame, "You have to fill all the text");
            return;
        }
        if((int)priceTxt.getValue()==0){
            JOptionPane.showMessageDialog(frame, "Price cannot be equal to 0");
            return;
        }
        int id = Integer.parseInt(idVal.getText());
        String name = nameTxt.getText();
        int price = (int)priceTxt.getValue();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date expDate = Date.valueOf(df.format(dateTxt.getValue()));
        String manuf = manufTxt.getText();
        if(dbHandler.conn.insert(id,name,price,expDate,manuf)){
            JOptionPane.showMessageDialog(frame,"data saved succesfully");
            Gui gui = new Gui();
        } else {
            JOptionPane.showMessageDialog(frame,"data is not saved");

        }
    }
}
