package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {
    private Map<String, Resume> storage;

    public MapUuidStorage() {
        storage = new HashMap<>();
    }

    public Resume[] getAll() {
        Resume[] r = new Resume[storage.size()];
        return storage.values().toArray(r);
    }

    @Override
    protected Object getIndex(String uuid) {
        return uuid;
    }

    @Override
    protected Resume getElement(Object index) {
        return storage.get(index);
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
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void updateElement(Resume r, Object index) {
        storage.replace(r.getUuid(), r);
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
