package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MainCollections {

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private final String UUID_4 = "uuid4";
    private final Resume RESUME_4 = new Resume(UUID_4);

    public static void main(String[] args) {
        Collection<Resume> c = new ArrayList();
        c.add(RESUME_1);
        c.add(RESUME_2);
        c.add(RESUME_3);
        /*
        for (Resume r : c){
            System.out.println(r);
            if (Objects.equals(r.getUuid(), UUID_1)){
                //c.remove(r);
            }
        }*/
        Iterator<Resume> iterator = c.iterator();

        while (iterator.hasNext()) {
            Resume r = iterator.next();
            System.out.println(r);
            if (Objects.equals(r.getUuid(), UUID_1)){
                iterator.remove();
            }
        }
        System.out.println(c.toString());

        Map<String, Resume> map = new HashMap<>();
        map.put(UUID_1,RESUME_1);
        map.put(UUID_2,RESUME_2);
        map.put(UUID_3,RESUME_3);

        for (Map.Entry<String,Resume> entry : map.entrySet()){
            System.out.println(entry.getValue());
        }
    }

}
