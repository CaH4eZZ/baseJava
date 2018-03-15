package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage;

    public ListStorage() {
        storage = new ArrayList<Resume>();
    }

    public Resume[] getAll() {
        Resume[] r = new Resume[storage.size()];
        return storage.toArray(r);
    }

    @Override
    protected void clearStorage() {
        storage.clear();
    }

    @Override
    protected int getIndex(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

    @Override
    protected Resume getElement(int index, String uuid) {
        return storage.get(index);
    }

    @Override
    protected boolean checkOverflow() {
        return false;
    }

    @Override
    protected void insertIntoStorage(Resume r, int index) {
        storage.add(r);
    }

    @Override
    protected void updateElement(Resume r, int index) {
        storage.set(index, r);
    }

    @Override
    protected void deleteFromStorage(String uuid, int index) {
        storage.remove(index);
    }
}

