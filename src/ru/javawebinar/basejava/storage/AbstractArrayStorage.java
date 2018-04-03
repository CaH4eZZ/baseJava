package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
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
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected Resume getElement(Object index) {
        return storage[(int) index];
    }

    @Override
    protected void updateElement(Resume r, Object index) {
        storage[(int) index] = r;
    }


    @Override
    protected boolean checkIndexOnExist(Object index) {
        return ((int) index >= 0);
    }

    @Override
    protected void insertIntoStorage(Resume r, Object index) {
        if (size >= MAX_SIZE) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        setElement(r, index);
        size++;
    }

    @Override
    protected void deleteFromStorage(Object index) {
        deleteElement(index);
        storage[size - 1] = null;
        size--;
    }

    abstract protected void setElement(Resume r, Object index);

    abstract protected void deleteElement(Object index);
}
