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
import java.util.Map;


class WordView extends JScrollPane {
    private static final long serialVersionUID = 1L;

    private JTable table;
    private DefaultTableModel tableModel;
    private Map<String, String> words;

    WordView(Map<String, String> map, int width, int height, String description) {
        String[] column = {"Słowo:", "Wystąpienia:"};
        tableModel = new DefaultTableModel(column, 0);
        table = new JTable(tableModel);
        table.setRowSelectionAllowed(false);
        this.words = map;
        setViewportView(table);
        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createTitledBorder(description));
    }

    void refresh() {
        tableModel.setRowCount(0);
        for (String key : words.keySet()) {
            String value = words.get(key);
            String[] row = {key, value};
            tableModel.addRow(row);
        }

    }

}