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
        return storage.containsKey(uuid) ? uuid : null;
    }

    @Override
    protected Resume getElement(Object index) {
        return storage.get((String) index);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void clearStorage() {
        storage.clear();
    }

    @Override
    protected boolean checkOverflow() {
        return false;
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
        if (index != null) return true;
        return false;
    }
}
