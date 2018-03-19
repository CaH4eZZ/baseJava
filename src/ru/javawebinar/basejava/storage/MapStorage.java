package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage;

    public MapStorage() {
        storage = new HashMap<String, Resume>();
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
        return storage.get((String)index);
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
    protected void deleteFromStorage(String uuid, Object index) {
        storage.remove(uuid);
    }

    @Override
    protected boolean checkIndexOnExist(Object index) {
        return storage.containsKey((String)index);
    }
}
