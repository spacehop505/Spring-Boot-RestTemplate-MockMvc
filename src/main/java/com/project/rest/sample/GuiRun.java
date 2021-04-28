package com.project.rest.sample;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.project.rest.models.Fruit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiRun {

    private JButton button1;
    private JPanel panelMain;
    private JTable showTable;
    private JTextField textField1;
    private JTextField putID;
    private JTextField putName;
    private JTextField putPrice;
    private JTextField putQuantity;
    private JButton putButton;
    private JTextField postId;
    private JTextField postName;
    private JTextField postPrice;
    private JTextField postQuantity;
    private DefaultTableModel tableModel;

    public GuiRun() {

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // textField1.getText();
                System.out.println(textField1.getText());
                //textField1.setText("");


            }
        });


        putButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(putID.getText());
                String name = putName.getText();
                double price = Double.parseDouble(putPrice.getText());
                int quantity = Integer.parseInt(putQuantity.getText());

                ClientHttpRequests clientHttpRequests = new ClientHttpRequests();
                clientHttpRequests.putUri(id, name, price, quantity);
                refreshTable();
                //  getFruit();

                putID.setText("");
                putName.setText("");
                putPrice.setText("");
                putQuantity.setText("");
            }
        });
        createTable();
        refreshTable();
    }


    public void refreshTable() {
        ClientHttpRequests clientHttpRequests = new ClientHttpRequests();
        clientHttpRequests.getUri();

        DefaultTableModel model = (DefaultTableModel) showTable.getModel();
        model.setRowCount(0);
        for (Fruit fruit : clientHttpRequests.getUri()) {
            Object rowData1[] = new Object[4];
            rowData1[0] = fruit.getId();
            rowData1[1] = fruit.getName();
            rowData1[2] = fruit.getPrice();
            rowData1[3] = fruit.getQuantity();
            model.addRow(rowData1);
        }


    }


    public void createTable() {
        showTable.setModel(new DefaultTableModel(
                null,
                new String[]{"ID", "Name", "Surname", "Age"}
        ));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setContentPane(new GuiRun().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }



}
