package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {
    //внутрренний статичный класс
    /*  private static class ResumeComparator implements Comparator<Resume> {
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    }
    private static final ResumeComparator RESUME_COMPARATOR = new ResumeComparator();*/
    //реализация анонимного класса
    /*private static final Comparator<Resume> RESUME_COMPARATOR = new Comparator<Resume>(){
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    };*/
    //лямбда
    //private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    //Comparator.comparing
    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    protected Object getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid,""),RESUME_COMPARATOR);
    }

    @Override
    protected void setElement(Resume r, Object index) {
        int i = -(int) index - 1;

        System.arraycopy(storage, i, storage, i + 1, size - i);
        storage[i] = r;
    }

    @Override
    protected void deleteElement(Object index) {
        int i = (int) index;
        int countEl = size - (i + 1);
        if (countEl > 0) {
            System.arraycopy(storage, i + 1, storage, i, countEl);
        }
    }
}
