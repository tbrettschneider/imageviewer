package com.tommybrettschneider.imageviewer.ui.directorybox;

import com.tommybrettschneider.imageviewer.util.Files;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.tommybrettschneider.imageviewer.util.UIUtils;

/**
 *
 * @author A752203
 */
public class DirectoryBox extends JComboBox {

    public DirectoryBox() {
        super();
        setEditable(true);
        setModel(new LimitedEntriesComboxBoxModel(10));
        setEditor(new MyComboBoxEditor());
        setOpaque(false);
        setRenderer(new MyComboBoxRenderer());
        addActionListener((java.awt.event.ActionEvent evt) -> {
            System.out.println(evt);
        });
    }

    private class MyComboBoxEditor extends JPanel implements ComboBoxEditor, FocusListener {

        private final JTextField textField;
        private final JLabel label;
        private Object value;

        public MyComboBoxEditor() {
            super(new BorderLayout(0, 0));
            textField = new JTextField();
            textField.addFocusListener(this);
            /*textField.addKeyListener(new KeyAdapter() {
             public void keyReleased(KeyEvent e) {
             if (e.getKeyCode()==KeyEvent.VK_ENTER) {
             File f = new File(textField.getText());
             if (f.exists() && f.isDirectory() && f.canRead()) {
             TreeNode node = treeFilesystem.findNode(treeFilesystem, f);
             if (node!=null) {
             TreePath path = getPath(node);
             treeFilesystem.expandPath(path);
             treeFilesystem.setSelectionPath(path);
             }
             }
             }
             }
             });*/
            textField.setBorder(BorderFactory.createEmptyBorder(1, 2, 1, 2));
            label = new JLabel();
            label.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));
            this.add(label, BorderLayout.WEST);
            this.add(textField, BorderLayout.CENTER);
            this.setOpaque(false);
        }

        // Returns a TreePath containing the specified node.
        public TreePath getPath(TreeNode node) {
            List list = new ArrayList();

            // Add all nodes to list
            while (node != null) {
                list.add(node);
                node = node.getParent();
            }
            Collections.reverse(list);

            // Convert array of nodes to TreePath
            return new TreePath(list.toArray());
        }

        @Override
        public Component getEditorComponent() {
            if (getItem() != null) {
                if (getItem() instanceof File) {
                    File file = (File) getItem();
                    textField.setText(file.toString());
                    label.setIcon(Files.getSmallFileIcon(file));
                    this.add(label, BorderLayout.WEST);
                }
            } else {
                textField.setText("");
                this.remove(label);
            }
            validate();
            repaint();

            return this;
        }

        @Override
        public void setItem(Object anObject) {
            value = anObject;
        }

        @Override
        public Object getItem() {
            return value;
        }

        @Override
        public void selectAll() {
            textField.selectAll();
        }

        @Override
        public void focusGained(FocusEvent e) {
            selectAll();
        }

        @Override
        public void focusLost(FocusEvent e) {
        }

        @Override
        public void addActionListener(ActionListener l) {
        }

        @Override
        public void removeActionListener(ActionListener l) {
        }
    }

    public class MyComboBoxRenderer extends DefaultListCellRenderer {

        public MyComboBoxRenderer() {

        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            File file = (File) value;
            if (file != null) {
                label.setText(file.toString());
                label.setIcon(Files.getSmallFileIcon(file));
            }
            label.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));
            return label;
        }
    }
}