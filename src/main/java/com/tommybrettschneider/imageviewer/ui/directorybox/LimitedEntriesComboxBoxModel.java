package com.tommybrettschneider.imageviewer.ui.directorybox;

import java.io.Serializable;
import java.util.LinkedList;

import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;

/**
 *
 * @author Tommy Brettschneider
 */
public class LimitedEntriesComboxBoxModel extends AbstractListModel
        implements MutableComboBoxModel, Serializable {

    private int maxEntries;
    private Object selectedItem = null;
    private LinkedList list;

    /**
     * Creates a new instance of LimitedEntriesComboxBoxModel
     */
    public LimitedEntriesComboxBoxModel() {
        super();
        list = new LinkedList();
    }

    public LimitedEntriesComboxBoxModel(int maxEntries) {
        this();
        this.maxEntries = maxEntries;
    }

    public void setMaxEntries(int maxEntries) {
        this.maxEntries = maxEntries;
    }

    public int getMaxEntries() {
        return maxEntries;
    }

    @Override
    public void addElement(Object object) {
        if (getSize() == getMaxEntries() && !list.contains(object)) {
            list.removeLast();
        }

        if (!list.contains(object)) {
            list.addFirst(object);
            int index = list.size() - 1;
            fireIntervalAdded(this, index, index);
        }

        setSelectedItem(object);
    }

    public int getIndexOf(Object object) {
        return list.indexOf(object);
    }

    @Override
    public Object getElementAt(int index) {
        if (index < 0 || index >= list.size()) {
            return null;
        }
        return list.get(index);
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }

    @Override
    public void setSelectedItem(Object object) {
        if (selectedItem == null) {
            if (object == null) {
                return;
            }
        } else {
            if (selectedItem.equals(object)) {
                return;
            }
        }
        selectedItem = object;
        fireContentsChanged(this, -1, -1);
    }

    @Override
    public int getSize() {
        return list.size();
    }

    public void removeAllElements() {
        selectedItem = null;
        int size = getSize();
        if (size > 0) {
            list.clear();
            fireIntervalRemoved(this, 0, size - 1);
        }
    }

    @Override
    public void removeElement(Object object) {
        int index = getIndexOf(object);
        if (index != -1) {
            removeElementAt(index);
        }
    }

    @Override
    public void insertElementAt(Object object, int index) {
        list.add(index, object);
        fireIntervalAdded(this, index, index);
    }

    @Override
    public void removeElementAt(int index) {
        int selected = getIndexOf(selectedItem);
        if (selected == index) // choose a new selected item
        {
            if (selected > 0) {
                setSelectedItem(getElementAt(selected - 1));
            } else {
                setSelectedItem(getElementAt(selected + 1));
            }
        }
        list.remove(index);
        fireIntervalRemoved(this, index, index);
    }
}