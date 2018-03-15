package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        clearStorage();
        size = 0;
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
        return getElement(index, uuid);
    }

    public void save(Resume r) {
        if (checkOverflow()) {
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

    public void update(Resume r) {
        if (r == null || r.getUuid() == null) {
            System.out.println("Resume is empty.");
            return;
        }
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        }
        updateElement(r, index);
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
    }

    protected abstract void clearStorage();

    protected abstract int getIndex(String uuid);

    protected abstract Resume getElement(int index, String uuid);

    protected abstract void insertIntoStorage(Resume r, int index);

    protected abstract boolean checkOverflow();

    protected abstract void updateElement(Resume r, int index);

    protected abstract void deleteFromStorage(String uuid, int index);

}
