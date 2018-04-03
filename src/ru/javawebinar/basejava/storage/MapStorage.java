package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<Resume, Resume> storage;

    public MapStorage() {
        storage = new HashMap<>();
    }

    public Resume[] getAll() {
        Resume[] r = new Resume[storage.size()];
        return storage.values().toArray(r);
    }

    @Override
    protected Object getIndex(String uuid) {
        return new Resume(uuid);
    }

    @Override
    protected Resume getElement(Object searchKey) {
        return storage.get(searchKey);
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
    protected void insertIntoStorage(Resume r, Object index) {
        storage.put(r, r);
    }

    @Override
    protected void updateElement(Resume r, Object index) {
        storage.replace(r, r);
    }

    @Override
    protected void deleteFromStorage(Object index) {
        storage.remove(index);
    }

    @Override
    protected boolean checkIndexOnExist(Object index) {
        return storage.containsKey(index);
    }
}
