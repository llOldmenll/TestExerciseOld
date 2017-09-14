package com.oldmen.attendancemanager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class OpenAddressHashMapTest extends Assert {
    private final OpenAddressHashMap hashMap = new OpenAddressHashMap(500);

    @Before
    public void setUp() throws Exception {

        for (int i = 0; i < 250; i++) {
            hashMap.put(i, (long) (457547 + i) * (i + 1));
        }
    }


    @Test
    public void get() throws Exception {

        for (int i = 0; i < 250; i++) {
            assertEquals((long) (457547 + i) * (i + 1), (long) hashMap.get(i));
        }

    }

    @Test
    public void put() throws Exception {

        for (int i = 0; i < 250; i++){
                hashMap.put(i, 74356473547L);
        }

        for (int i = 0; i < 250; i++) {
            assertEquals(74356473547L, (long) hashMap.get(i));
        }


    }

    @Test
    public void delete() throws Exception {

        for (int i = 0; i < 250; i++) {
            hashMap.delete(i);
        }

        for (int i = 0; i < 250; i++) {
            assertEquals(null, (Long) hashMap.get(i));
        }

    }

}