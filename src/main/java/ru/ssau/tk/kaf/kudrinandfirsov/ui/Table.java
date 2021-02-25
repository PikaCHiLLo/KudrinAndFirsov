package ru.ssau.tk.kaf.kudrinandfirsov.ui;

import ru.ssau.tk.kaf.kudrinandfirsov.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.TabulatedFunction;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Table extends JDialog {
    private final ArrayList<String> stringsX;
    private final ArrayList<String> stringsY;
    private TabulatedFunction function;
    private double[] xValues;
    private double[] yValues;
    JDialog dialog = new JDialog();

    public Table(int size, TabulatedFunctionFactory factory) {
        setModal(true);
        stringsY = new ArrayList<>(size);
        stringsX = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            stringsX.add("");
            stringsY.add("");
        }
        AbstractTableModel tableModel = new TableModel(stringsX, stringsY);
        JTable table = new JTable(tableModel);
        setSize(new Dimension(500, 500));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JButton create = new JButton("Создать");
        create.addActionListener(e -> {
            try {
                xValues = new double[size];
                yValues = new double[size];
                for (int i = 0; i < size; i++) {
                    xValues[i] = Double.parseDouble(stringsX.get(i));
                    yValues[i] = Double.parseDouble(stringsY.get(i));
                }
                function = factory.create(xValues, yValues);
                System.out.println(function.toString());
                dispose();
            } catch (NumberFormatException | ArrayIsNotSortedException exception) {
                JOptionPane.showMessageDialog(dialog, "Неверно введены значения или иксы неотсортированы");
            }
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane tableScrollPaneX = new JScrollPane(table);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addGroup(layout.createSequentialGroup().addComponent(tableScrollPaneX)).addGroup(layout.createSequentialGroup().addComponent(create)));
        layout.setVerticalGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup().addComponent(tableScrollPaneX)).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(create)));
        setLocationRelativeTo(null);
    }

    public TabulatedFunction getFunction() {
        return function;
    }

    public ArrayList<String> getStringsX() {
        return stringsX;
    }

    public ArrayList<String> getStringsY() {
        return stringsY;
    }
}