package com.tommybrettschneider.imageviewer.sort;

import com.tommybrettschneider.imageviewer.ui.thumbnail.ScrollableThumbnailPane;
import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;

public class SortMenu extends JMenu {

    private ComparatorFactory cf;
    private Container sortableContainer;
    private ButtonGroup buttonGroup;
    private JMenuItem sortByFilename;
    private JMenuItem sortBySize;
    private JMenuItem sortByModifiedDate;
    private JMenuItem sortByImageProperties;
    private ScrollableThumbnailPane tp;

    public SortMenu() {
        super("Sort");
        initialize();
    }

    public SortMenu(ScrollableThumbnailPane tp) {
        super("Sort");
        this.tp = tp;
        initialize();
    }

    private void initialize() {
        if (tp != null) {
            cf = ComparatorFactory.getInstance(tp.getSource());
            removeAll();
            buttonGroup = new ButtonGroup();

            sortByFilename = new JRadioButtonMenuItem(new SortAction("Filename", cf.getNameComparator()));
            add(sortByFilename);
            buttonGroup.add(sortByFilename);

            sortBySize = new JRadioButtonMenuItem(new SortAction("Size (KB)", cf.getSizeComparator()));
            add(sortBySize);
            buttonGroup.add(sortBySize);

            sortByModifiedDate = new JRadioButtonMenuItem(new SortAction("Modified Date", cf.getLastModifiedComparator()));
            add(sortByModifiedDate);
            buttonGroup.add(sortByModifiedDate);

            sortByImageProperties = new JRadioButtonMenuItem(new SortAction("Image Properties", cf.getImagePropertiesComparator()));
            add(sortByImageProperties);
            buttonGroup.add(sortByImageProperties);

            addSeparator();

            add(new JCheckBoxMenuItem("Reverse"));
        }
    }
    
    public void setSortableContainer(Container container) {
        this.sortableContainer = container;
    }

    class SortAction extends AbstractAction {

        private Comparator comparator;

        public SortAction(String text) {
            super(text);
        }
        
        public SortAction(String text, Comparator comparator) {
            super(text);
            this.comparator = comparator;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(comparator);
            Component[] thumbnails = sortableContainer.getComponents();
            Thread t = new Thread() {
                @Override
                public void run() {
                    Arrays.parallelSort(thumbnails, comparator);
                    SwingUtilities.invokeLater(() -> {
                        sortableContainer.removeAll();
                        Arrays.stream(thumbnails).forEach(thumb -> sortableContainer.add(thumb));
                        tp.getViewport().setViewPosition(new Point(0, 0));
                        sortableContainer.invalidate();
                        sortableContainer.validate();
                        sortableContainer.repaint();         
                    });
                }
            };
            t.start();  
        }
    }
}