package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    abstract public int size();

    public void clear() {
        clearStorage();
    }

    public Resume get(String uuid) {
        if (uuid == null) {
            System.out.println("uuid is null");
            return null;
        }
        Object index = getIndex(uuid);
        if (!checkIndexOnExist(index)) {
            throw new NotExistStorageException(uuid);
        }
        return getElement(index);
    }

    public void save(Resume r) {
        if (checkOverflow()) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else if (r == null) {
            System.out.println("Can't save empty Resume");
            return;
        }

        Object index = getIndex(r.getUuid());
        if (checkIndexOnExist(index)) {
            throw new ExistStorageException(r.getUuid());
        }
        insertIntoStorage(r, index);
    }

    public void update(Resume r) {
        if (r == null || r.getUuid() == null) {
            System.out.println("Resume is empty.");
            return;
        }
        Object index = getIndex(r.getUuid());
        if (!checkIndexOnExist(index)) {
            throw new NotExistStorageException(r.getUuid());
        }
        updateElement(r, index);
    }

    public void delete(String uuid) {
        if (uuid == null || "".equals(uuid)) {
            System.out.println("uuid can't be null");
            return;
        }
        Object index = getIndex(uuid);
        if (!checkIndexOnExist(index)) {
            throw new NotExistStorageException(uuid);
        }
        deleteFromStorage(uuid, index);
    }

    protected abstract void clearStorage();

    protected abstract Object getIndex(String uuid);

    protected abstract Resume getElement(Object index);

    protected abstract void insertIntoStorage(Resume r, Object index);

    protected abstract boolean checkOverflow();

    protected abstract void updateElement(Resume r, Object index);

    protected abstract void deleteFromStorage(String uuid, Object index);

    protected abstract boolean checkIndexOnExist(Object index);
}
