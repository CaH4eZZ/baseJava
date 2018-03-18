package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected Object getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i] != null && uuid.equals(storage[i].getUuid()))
                return i;
        }
        return -1;
    }

    @Override
    protected void setElement(Resume r, Object index) {
        storage[size] = r;
    }

    @Override
    protected void deleteElement(String uuid, Object index) {
        storage[(int) index] = storage[size - 1];
    }
}
