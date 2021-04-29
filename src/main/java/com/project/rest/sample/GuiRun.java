package com.project.rest.sample;

import com.project.rest.models.Fruit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiRun {

    private JButton getRefresh;
    private JPanel panelMain;
    private JTable showTable;
    private JTextField textField1;
    private JTextField putID;
    private JTextField putName;
    private JTextField putPrice;
    private JTextField putQuantity;
    private JButton putButton;
    private JTextField postID;
    private JTextField postName;
    private JTextField postPrice;
    private JTextField postQuantity;
    private JButton postButton;
    private JButton deleteButton;
    private JTextField deleteID;
    private JButton getIDButton;
    private JTextField getID;
    private DefaultTableModel tableModel;

    public GuiRun() {

        getRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
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

                putID.setText("");
                putName.setText("");
                putPrice.setText("");
                putQuantity.setText("");
            }
        });

        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(postID.getText());
                String name = postName.getText();
                double price = Double.parseDouble(postPrice.getText());
                int quantity = Integer.parseInt(postQuantity.getText());

                ClientHttpRequests clientHttpRequests = new ClientHttpRequests();
                clientHttpRequests.postUri(id, name, price, quantity);

                refreshTable();

                postID.setText("");
                postName.setText("");
                postPrice.setText("");
                postQuantity.setText("");
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(deleteID.getText());

                ClientHttpRequests clientHttpRequests = new ClientHttpRequests();
                clientHttpRequests.deleteUri(id);

                refreshTable();

                deleteID.setText("");
            }
        });
        getIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int id = Integer.parseInt(getID.getText());
                System.out.println(id);
                refreshSearchTable(id);
                getID.setText("");
            }
        });
        createTable();
        refreshTable();

    }


    public void refreshTable() {
        ClientHttpRequests clientHttpRequests = new ClientHttpRequests();

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

    public void refreshSearchTable(int id) {
        ClientHttpRequests clientHttpRequests = new ClientHttpRequests();

        DefaultTableModel model = (DefaultTableModel) showTable.getModel();
        model.setRowCount(0);
        for (Fruit fruit : clientHttpRequests.getUriId(id)) {
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
                new String[]{"ID", "Name", "Price", "Quantity"}
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
