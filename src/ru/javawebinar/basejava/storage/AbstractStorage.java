package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    abstract public int size();

    public List<Resume> getAllSorted(){
        Resume[] arr = getAll();
        Arrays.sort(arr, Comparator.comparing(Resume::getFullName)
                                     .thenComparing(Comparator.comparing(Resume::getUuid)));
        return Arrays.asList(arr);
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
        if (r == null) {
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
        if (uuid == null) {
            System.out.println("uuid can't be null");
            return;
        }
        Object index = getIndex(uuid);
        if (!checkIndexOnExist(index)) {
            throw new NotExistStorageException(uuid);
        }
        deleteFromStorage(index);
    }

    protected abstract Object getIndex(String uuid);

    protected abstract Resume getElement(Object index);

    protected abstract void insertIntoStorage(Resume r, Object index);

    protected abstract void updateElement(Resume r, Object index);

    protected abstract void deleteFromStorage(Object index);

    protected abstract boolean checkIndexOnExist(Object index);

    protected abstract Resume[] getAll();

}
