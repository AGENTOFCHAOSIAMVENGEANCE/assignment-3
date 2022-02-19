package com.nursultan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class updValue {
    JFrame frame = new JFrame("update medicine");
    JLabel idLbl = new JLabel("ID");
    JLabel idVal = new JLabel("");
    JLabel nameLbl = new JLabel("Name");
    JTextField nameTxt = new JTextField();
    JLabel priceLbl = new JLabel("Price");
    JTextField priceTxt = new JTextField();
    JLabel dateLbl = new JLabel("Expiration Date");
    JTextField dateTxt = new JTextField();
    JLabel manufLbl = new JLabel("Manufacturer");
    JTextField manufTxt = new JTextField();
    JButton del = new JButton("Update");
    JComboBox comboBox;
    ArrayList<Component> comps = new ArrayList<>();
    public updValue(String arr[]) {
        comboBox = new JComboBox(arr);
        comboBox.setSelectedIndex(0);
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
        del.setEnabled(true);
        frame.setBounds(100,100,500,550);
        frame.setLayout(null);
    }
    void arr(){
        comps.add(comboBox);
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
        comps.add(del);
        del.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int id = Integer.parseInt(idVal.getText());
                        String name = nameTxt.getText();
                        int price = Integer.parseInt(priceTxt.getText());
                        String expDate = dateTxt.getText();
                        String manuf = manufTxt.getText();
                        if(dbHandler.conn.update(id,name,price,expDate,manuf)){
                            JOptionPane.showMessageDialog(frame,"data updated succesfully");
                            Gui gui = new Gui();
                        } else {
                            JOptionPane.showMessageDialog(frame,"data is not updated");

                        }
                    }
                }
        );
        comboBox.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String item = (String)comboBox.getSelectedItem();
                        ResultSet rs = dbHandler.conn.res(" where name='"+item+"'");
                        while(true){
                            try {
                                if (!rs.next()) break;
                                idVal.setText(Integer.toString(rs.getInt("ID")));
                                nameTxt.setText(rs.getString("Name"));
                                priceTxt.setText(Integer.toString(rs.getInt("Price")));
                                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                dateTxt.setText(df.format(rs.getDate("exp_date")));
                                manufTxt.setText(rs.getString("Manufacturer"));
                            } catch (SQLException er) {
                                er.printStackTrace();
                            }
                        }
                    }
                }
        );
    }
    void Show(boolean isShown){
        frame.setVisible(isShown);
    }
}
