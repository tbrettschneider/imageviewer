package acdsee.sorting;

import acdsee.ui.components.thumbnail.ScrollableThumbnailPane;
import acdsee.sorting.comparator.ComparatorFactory;
import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class SortMenu extends JMenu {

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
            ComparatorFactory cf = ComparatorFactory.getInstance(tp.getSource());
            removeAll();
            buttonGroup = new ButtonGroup();

            sortByFilename = new JRadioButtonMenuItem(new SortAction("Filename", cf.getNameComparator()));
            this.add(sortByFilename);
            buttonGroup.add(sortByFilename);

            sortBySize = new JRadioButtonMenuItem(new SortAction("Size (KB)", cf.getSizeComparator()));
            this.add(sortBySize);
            buttonGroup.add(sortBySize);

            sortByModifiedDate = new JRadioButtonMenuItem(new SortAction("Modified Date", cf.getLastModifiedComparator()));
            this.add(sortByModifiedDate);
            buttonGroup.add(sortByModifiedDate);

            sortByImageProperties = new JRadioButtonMenuItem(new SortAction("Image Properties", cf.getImagePropertiesComparator()));
            this.add(sortByImageProperties);
            buttonGroup.add(sortByImageProperties);

            this.addSeparator();

            this.add(new JRadioButtonMenuItem(new SortAction("Reverse Order", cf.reverse())));
        }
    }
    
    public void setSortableContainer(Container container) {
        this.sortableContainer = container;
    }

    public Container getSortableContainer() {
        return this.sortableContainer;
    }

    class SortAction extends AbstractAction {

        private final Comparator comparator;

        public SortAction(String text, Comparator comparator) {
            super(text);
            this.comparator = comparator;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Component[] thumbnails = sortableContainer.getComponents();
            Arrays.sort(thumbnails, comparator);
            sortableContainer.removeAll();
            Arrays.stream(thumbnails).parallel().forEach(thumb -> sortableContainer.add(thumb));
            tp.getViewport().setViewPosition(new Point(0, 0));
            sortableContainer.invalidate();
            sortableContainer.validate();
            sortableContainer.repaint();
        }
    }
}