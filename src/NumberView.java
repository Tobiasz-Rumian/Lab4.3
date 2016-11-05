/*
 * @version 1.0
 * @author Tobiasz Rumian
 * Data: 05 Listopad 2016 r.
 * Indeks: 226131
 * Grupa: śr 13:15 TN
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;


class NumberView extends JScrollPane {
    private static final long serialVersionUID = 1L;

    private JTable table;
    private DefaultTableModel tableModel;
    private Vector<Integer> value;
    private Vector<String> key;

    NumberView(Vector<Integer> value, Vector<String> key, int width, int height, String description) {
        String[] column = {"Wystąpienia:", "Słowo:"};
        tableModel = new DefaultTableModel(column, 0);
        table = new JTable(tableModel);
        table.setRowSelectionAllowed(false);
        this.value = value;
        this.key = key;
        setViewportView(table);
        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createTitledBorder(description));
    }

    void refresh() {
        tableModel.setRowCount(0);
        for (int i = 0; i < value.size(); i++) {
            String[] row = {Integer.toString(value.get(i)), key.get(i)};
            tableModel.addRow(row);
        }
    }
}