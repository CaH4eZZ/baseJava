package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage;

    public MapStorage() {
        storage = new HashMap<>();
    }

    public Resume[] getAll() {
        Resume[] r = new Resume[storage.size()];
        return storage.values().toArray(r);
    }

    @Override
    protected Object getIndex(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected Resume getElement(Object r) {
        return (Resume) r;
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
    protected void insertIntoStorage(Resume r, Object searchKey) {
        storage.put(r.getUuid() , r);
    }

    @Override
    protected void updateElement(Resume r, Object searchKey) {
        storage.replace(r.getUuid(), r);
    }

    @Override
    protected void deleteFromStorage(Object r) {
        storage.remove(((Resume) r).getUuid());
    }

    @Override
    protected boolean checkIndexOnExist(Object r) {
        return r != null;
    }
}
