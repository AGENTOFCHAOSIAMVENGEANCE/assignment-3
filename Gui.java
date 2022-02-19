package com.nursultan;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Gui {

    JFrame frame = new JFrame();
    DefaultTableModel model;
    JTable table ;
    JButton ins = null;
    JButton upd = null;
    JButton del = null;
    JPanel panel =null;
    addValue av = new addValue(this);
    static String[] arr = new String[50];
    delValue dv;
    updValue up;
    String columns[] = { "ID", "Name", "Price", "Expiration Date", "Manufacturer" };
    String data[][] = new String[50][6];
    public Gui() {
       dbHandler.conn.isConnected();
       frame.setTitle("GUI");
       frame.setBounds(100,100,500,500);
       init(frame);
    }
    void init(JFrame frame){
        ins = new JButton("insert");
        upd = new JButton("update");
        del = new JButton("delete");
        panel = new JPanel();
        fillTable();
        JScrollPane pane = new JScrollPane(table);
        panel.add(pane);
        frame.add(panel);
        panel.add(ins);
        panel.add(upd);
        panel.add(del);
        frame.add(panel);
        frame.setSize(800,  800);
        buttonActions();
        frame.setVisible(true);
    }

    void buttonActions(){
        ins.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        av.Show(true);
                        frame.setVisible(false);
                    }
                }
        );
        del.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dv= new delValue(arr);
                        dv.Show(true);
                        frame.setVisible(false);
                    }
                }
        );
        upd.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        up = new updValue(arr);
                        up.Show(true);
                        frame.setVisible(false);
                    }
                }
        );
    }
    public void fillTable(){
        ResultSet rs = dbHandler.conn.res("");
        int i = 0;
        while (true) {
            try {
                if (!rs.next()) break;
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                int price  = rs.getInt("Price");
                Date date  = rs.getDate("exp_date");
                String manuf  = rs.getString("Manufacturer");
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                data[i][0] = id + "";
                arr[i]=name;
                data[i][1] = name;
                data[i][2] = Integer.toString(price);
                data[i][3] = df.format(date);
                data[i][4] = manuf;
                i++;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
    }
    public static void main(String[] args) {
        Gui gui = new Gui();
//        addValue av = new addValue();
//        av.Show(true);
//        delValue dv = new delValue(arr);
//        dv.Show(true);
//        updValue uv = new updValue(arr);
//        uv.Show(true);
    }

}
