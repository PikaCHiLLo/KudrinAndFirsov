package ru.ssau.tk.kaf.kudrinandfirsov.ui;

import ru.ssau.tk.kaf.kudrinandfirsov.functions.TabulatedFunction;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.kaf.kudrinandfirsov.io.FunctionsIO;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Main extends JFrame {
    protected static TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
    private final ArrayList<String> stringsX;
    private final ArrayList<String> stringsY;
    private final AbstractTableModel tableModel;
    private final JTable table;
    private TabulatedFunction function;
    private int size;

    public Main() {
        JFrame main = new JFrame("Главное окно");
        Image image = new ImageIcon("Фон.png").getImage();
        setContentPane(new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(image, 0, 0, null);
            }
        });
        JFileChooser fileOpen = new JFileChooser();
        fileOpen.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileOpen.setDialogTitle("Загрузка функции");
        fileOpen.addChoosableFileFilter(new FileNameExtensionFilter("Text files", "bin"));
        fileOpen.setAcceptAllFileFilterUsed(false);
        JFileChooser fileSave = new JFileChooser();
        fileSave.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileSave.setDialogTitle("Сохранение функции");
        fileSave.addChoosableFileFilter(new FileNameExtensionFilter("Text files", "bin"));
        fileSave.setAcceptAllFileFilterUsed(false);
        stringsX = new ArrayList<>();
        stringsY = new ArrayList<>();
        tableModel = new TableModel(stringsX, stringsY);
        table = new JTable(tableModel);
        setSize(new Dimension(1100, 560));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JLabel count = new JLabel("Введите размер");
        JTextField countField = new JTextField("   ");
        JButton countButton = new JButton("Ввести");
        countButton.addActionListener(e -> size = Integer.parseInt(countField.getText()));
        JButton create = new JButton("Создать ф-ю");
        create.addActionListener(e -> {
            stringsX.clear();
            stringsY.clear();
            Table table = new Table(size, factory);
            table.setVisible(true);
            for (int i = 0; i < size; i++) {
                stringsX.add(table.getStringsX().get(i));
                stringsY.add(table.getStringsY().get(i));
            }
            tableModel.fireTableDataChanged();
            function = table.getFunction();
        });
        JButton load = new JButton("Загрузить ф-ю");
        load.addActionListener(e -> {
            stringsX.clear();
            stringsY.clear();
            fileOpen.showOpenDialog(main);
            File file = fileOpen.getSelectedFile();
            if (file != null) {
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
                    TabulatedFunction function = FunctionsIO.deserialize(in);
                    for (int i = 0; i < function.getCount(); i++) {
                        stringsX.add(i, String.valueOf(function.getX(i)));
                        stringsY.add(i, String.valueOf(function.getY(i)));
                        tableModel.fireTableDataChanged();
                    }
                    double[] xValues = new double[function.getCount()];
                    double[] yValues = new double[function.getCount()];
                    for (int i = 0; i < table.getRowCount(); i++) {
                        xValues[i] = Double.parseDouble(stringsX.get(i));
                        yValues[i] = Double.parseDouble(stringsY.get(i));
                    }
                    size = function.getCount();
                    System.out.println(function.toString());
                    function = factory.create(xValues, yValues);
                } catch (IOException | ClassNotFoundException err) {
                    err.printStackTrace();
                }
            }
        });
        JButton save = new JButton("Сохранить ф-ю");
        save.addActionListener(e -> {
            if (table.getRowCount() == 0) {
                JOptionPane.showMessageDialog(main, "Создайте функцию");
            } else {
                fileSave.showSaveDialog(main);
                File file = fileSave.getSelectedFile();
                if (file != null) {
                    double[] xValues = new double[table.getRowCount()];
                    double[] yValues = new double[table.getRowCount()];
                    for (int i = 0; i < table.getRowCount(); i++) {
                        xValues[i] = Double.parseDouble(table.getValueAt(i, 0).toString());
                        yValues[i] = Double.parseDouble(table.getValueAt(i, 1).toString());
                    }

                    function = factory.create(xValues, yValues);

                    try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
                        FunctionsIO.serialize(out, function);
                    } catch (IOException error) {
                        error.printStackTrace();
                    }
                }
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenu menuTab = new JMenu("Табулированная функция");
        menuTab.setSize(100,50);
        menuTab.add(createTabulatedFunction());
        JMenu menuMath = new JMenu("Математичекские функции");
        menuMath.setSize(100,50);
        menuMath.add(createMathFunction());
        JMenuBar menuBar = new JMenuBar();
        menuBar.setSize(2000,50);
        menuBar.add(menuTab);
        menuBar.add(menuMath);
        JMenu menuSettings = new JMenu("Настройки");
        menuSettings.setSize(100,50);
        menuBar.add(menuSettings);
        menuSettings.add(settings());
        setJMenuBar(menuBar);
        setLocationRelativeTo(null);
        JScrollPane tableScrollPane = new JScrollPane(table);
        getContentPane().add(count);
        getContentPane().add(countField);
        getContentPane().add(countButton);
        getContentPane().add(tableScrollPane);
        getContentPane().add(create);
        getContentPane().add(load);
        getContentPane().add(save);
        setLayout(new FlowLayout());
    }

    private JMenu settings() {
        JMenu set = new JMenu("Настройки");
        JMenuItem item = new JMenuItem("Открыть");
        set.add(item);
        item.addActionListener(e -> {
            Settings settings = new Settings();
            settings.setVisible(true);
            factory = settings.getFactory();
        });
        return set;
    }

    private JMenu createTabulatedFunction() {
        JMenu tab = new JMenu("Табулированная функция");
        JMenuItem itemCreate = new JMenuItem("Создать");
        tab.add(itemCreate);
        itemCreate.addActionListener(e -> {
            WindowOfTabulated tabulatedFunction = new WindowOfTabulated(factory);
            tabulatedFunction.setVisible(true);
        });

        return tab;
    }

    private JMenu createMathFunction() {
        JMenu menuMath = new JMenu("Математичекские функции");
        JMenuItem math = new JMenuItem("Открыть");
        menuMath.add(math);
        math.addActionListener(e -> {
            MathFunctionWindow math1 = new MathFunctionWindow(factory);
            math1.setVisible(true);
        });

        return menuMath;
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public static void main(String[] args) {
        JFrame main = new Main();
        main.setVisible(true);
    }
}