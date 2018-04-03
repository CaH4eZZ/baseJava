package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage;

    public ListStorage() {
        storage = new ArrayList<>();
    }

    public Resume[] getAll() {
        Resume[] r = new Resume[storage.size()];
        return storage.toArray(r);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected Object getIndex(String uuid) {
        Iterator<Resume> iterator = storage.iterator();
        Integer index = 0;
        while (iterator.hasNext()) {
            Resume r = iterator.next();
            if (uuid.equals(r.getUuid())) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    protected Resume getElement(Object index) {
        return storage.get((int) index);
    }

    @Override
    protected void insertIntoStorage(Resume r, Object index) {
        storage.add(r);
    }

    @Override
    protected void updateElement(Resume r, Object index) {
        storage.set((int) index, r);
    }

    @Override
    protected void deleteFromStorage(Object index) {
        storage.remove((int) index);
    }

    @Override
    protected boolean checkIndexOnExist(Object index) {
        return ((int) index >= 0);
    }
}

