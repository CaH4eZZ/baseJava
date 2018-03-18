package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }

    @Override
    protected void setElement(Resume r, Object index) {
        int i = -(Integer) index - 1;

        System.arraycopy(storage, i, storage, i + 1, size - i);
        storage[i] = r;
    }

    @Override
    protected void deleteElement(String uuid, Object index) {
        int i = (Integer) index;
        int countEl = size - (i + 1);
        if (countEl > 0) {
            System.arraycopy(storage, i + 1, storage, i, countEl);
        }
    }
}
