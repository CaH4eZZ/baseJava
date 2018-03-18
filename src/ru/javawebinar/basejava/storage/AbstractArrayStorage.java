package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int MAX_SIZE = 5;
    protected int size = 0;

    protected Resume[] storage = new Resume[MAX_SIZE];

    @Override
    public int size() {
        return size;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    @Override
    protected void clearStorage() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected Resume getElement(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected boolean checkOverflow() {
        if (size >= MAX_SIZE) return true;
        return false;
    }

    @Override
    protected void updateElement(Resume r, Object index) {
        storage[(Integer) index] = r;
    }


    @Override
    protected boolean checkIndexOnExist(Object index) {
        if ((Integer) index >= 0) return true;
        return false;
    }

    @Override
    protected void insertIntoStorage(Resume r, Object index) {
        setElement(r, index);
        size++;
    }

    @Override
    protected void deleteFromStorage(String uuid, Object index) {
        deleteElement(uuid, index);
        storage[size - 1] = null;
        size--;
    }

    abstract protected void setElement(Resume r, Object index);

    abstract protected void deleteElement(String uuid, Object index);
}
