package com.kazlovich;

import org.junit.Test;
import java.util.Optional;
import static org.junit.Assert.*;

public class CustomLinkedListTest {

    private CustomLinkedList getList(){
        CustomLinkedList<Long> list = new CustomLinkedList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        return list;
    }

    @Test
    public void add() {
        CustomLinkedList<Long> list = getList();
        assertNotNull(list.get(1));
    }
    @Test
    public void addByIndex(){
        CustomLinkedList<Long> list = getList();
        list.addByIndex(100L,2);
        assertEquals(Optional.of(100L), Optional.of(list.get(2)));
    }

    @Test
    public void clear() {
        CustomLinkedList<Long> list = getList();
        list.add(1L);
        list.clear();
        assertEquals(0, list.size);
    }

    @Test
    public void get() {
        CustomLinkedList<Long> list = getList();
        assertNotNull(list.get(0));
    }

    @Test
    public void remove() {
        CustomLinkedList<Long> list = getList();
        list.remove(2);
        System.out.println(list.size);
    }

    @Test
    public void reverse() {
        CustomLinkedList<Long> list = getList();
        Object last = list.get(list.size - 1);
        list.reverse();
        assertEquals(list.get(0), last);
    }
}