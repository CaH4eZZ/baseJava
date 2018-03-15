package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String,Resume> storage;

    public MapStorage(){
        storage = new HashMap<String,Resume>();
    }

    public Resume[] getAll(){
        Resume[] r = new Resume[storage.size()];
        return storage.values().toArray(r);
    }

    @Override
    protected int getIndex(String uuid) {
        return storage.containsKey(uuid) ? 0 : -1;
    }

    @Override
    protected Resume getElement(int index, String uuid) {
        return storage.get(uuid);
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
    protected void insertIntoStorage(Resume r, int index) {
        storage.put(r.getUuid(),r);
    }

    @Override
    protected void updateElement(Resume r, int index) {
        storage.replace(r.getUuid(), r);
    }

    @Override
    protected void deleteFromStorage(String uuid, int index) {
        storage.remove(uuid);
    }
}
