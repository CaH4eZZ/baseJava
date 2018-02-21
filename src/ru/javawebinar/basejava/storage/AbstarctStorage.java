package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstarctStorage implements Storage {
    protected static final int MAX_SIZE = 5;

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
            throw new NotExistStorageException(r.getUuid());
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
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size >= MAX_SIZE) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else if (r == null) {
            System.out.println("Can't save empty Resume");
            return;
        }
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        }
        insertIntoStorage(r, index);
        size++;
    }

    public void delete(String uuid) {
        if (uuid == null || "".equals(uuid)) {
            System.out.println("uuid can't be null");
            return;
        }
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteFromStorage(uuid, index);
        size--;
        storage[size] = null;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertIntoStorage(Resume r, int index);

    protected abstract void deleteFromStorage(String uuid, int index);
}
