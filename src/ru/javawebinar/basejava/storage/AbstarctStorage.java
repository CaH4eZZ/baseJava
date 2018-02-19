package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstarctStorage implements Storage {
    protected static final int MAX_SIZE = 10000;

    protected Resume[] storage = new Resume[MAX_SIZE];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void update(Resume r) {
        if (r == null || r.getUuid() == null) {
            System.out.println("Resume is empty.");
            return;
        }
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Resume is not found");
            return;
        }
        storage[index] = r;
    }

    public Resume get(String uuid) {
        if (uuid == null) {
            System.out.println("uuid is null");
            return null;
        }
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume is not found");
            return null;
        }
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    public void clear() {
        Arrays.fill(storage,0,size,null);
        size = 0;
    }

    protected abstract int getIndex(String uuid);
}
