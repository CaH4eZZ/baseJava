package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int MAX_SIZE = 5;

    protected Resume[] storage = new Resume[MAX_SIZE];

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    @Override
    protected void clearStorage() {
        Arrays.fill(storage, 0, size, null);
    }

    @Override
    protected Resume getElement(int index, String uuid){
        return storage[index];
    }

    @Override
    protected boolean checkOverflow() {
        if (size >= MAX_SIZE) {
            return true;
        }
        return false;
    }

    @Override
    protected void updateElement(Resume r, int index) {
        storage[index] = r;
    }

}
