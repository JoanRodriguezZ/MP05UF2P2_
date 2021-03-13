package ex2;

import ex1.HashTable;
import org.junit.jupiter.api.Assertions;

class HashTableTest {

    ex1.HashTable ht = new HashTable();

    @org.junit.jupiter.api.Test
    void count() {
        ht.put("1", "11");
        ht.put("1", "1111"); //No lo añade porque tiene la misma key que el anterior
        ht.put("3", "33");
        ht.put("4", "44");
        ht.put("5", "55");
        ht.put("25", "25");
        Assertions.assertEquals(5, ht.count());
    }

    @org.junit.jupiter.api.Test
    void size() {
        Assertions.assertEquals(16, ht.size());
    }

    @org.junit.jupiter.api.Test
    void put() {
        ht.put("1", "11");
        ht.put("1", "1111");
        ht.put("2", "22");
        ht.put("3", "33");
        ht.put("4", "44");
        ht.put("5", "55");
        ht.put("5", "55");
        ht.put("5", "55");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, 11]\n" +
                " bucket[2] = [2, 22]\n" +
                " bucket[3] = [3, 33]\n" +
                " bucket[4] = [4, 44]\n" +
                " bucket[5] = [5, 55]", ht.toString());
    }

    @org.junit.jupiter.api.Test
    void get() {
        ht.put("1", "11");
        ht.put("2", "22");
        ht.put("3", "33");
        ht.put("4", "44");
        ht.put("4", "60");
        ht.put("5", "55");
        ht.put("16", "16");
        ht.put("27", "27");
        Assertions.assertEquals("44", ht.get("4"));
        Assertions.assertEquals("16", ht.get("16"));
        Assertions.assertEquals("27", ht.get("27"));
        Assertions.assertEquals(null, ht.get("38"));
        Assertions.assertEquals(null, ht.get("6"));

    }
    @org.junit.jupiter.api.Test
    void getBAKEADO() {
        ht.put("1", "11");
        Assertions.assertEquals(null, ht.get("12"));

    }

    @org.junit.jupiter.api.Test
    void drop() {
        ht.put("1", "11");
        ht.put("1", "1111");
        ht.put("3", "33");
        ht.put("4", "44");
        ht.put("5", "55");
        ht.put("16", "16");
        ht.put("25", "25");
        ht.put("27", "27");
        ht.put("38", "38");
        ht.put("49", "49");
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, 11]\n" +
                " bucket[3] = [3, 33] -> [25, 25]\n" +
                " bucket[4] = [4, 44]\n" +
                " bucket[5] = [5, 55] -> [16, 16] -> [27, 27] -> [38, 38] -> [49, 49]",
                ht.toString());
        Assertions.assertEquals(9,ht.count());

        ht.drop("1");
        Assertions.assertEquals("\n" +
                " bucket[3] = [3, 33] -> [25, 25]\n" +
                " bucket[4] = [4, 44]\n" +
                " bucket[5] = [5, 55] -> [16, 16] -> [27, 27] -> [38, 38] -> [49, 49]",
                ht.toString());
        Assertions.assertEquals(8,ht.count());

        ht.drop("16");
        Assertions.assertEquals("\n" +
                " bucket[3] = [3, 33] -> [25, 25]\n" +
                " bucket[4] = [4, 44]\n" +
                " bucket[5] = [5, 55] -> [27, 27] -> [38, 38] -> [49, 49]",
                ht.toString());
        Assertions.assertEquals(7,ht.count());

        ht.drop("3");
        Assertions.assertEquals("\n" +
                " bucket[3] = [25, 25]\n" +
                " bucket[4] = [4, 44]\n" +
                " bucket[5] = [5, 55] -> [27, 27] -> [38, 38] -> [49, 49]",
                ht.toString());
        Assertions.assertEquals(6,ht.count());

        ht.drop("5");
        Assertions.assertEquals("\n" +
                " bucket[3] = [25, 25]\n" +
                " bucket[4] = [4, 44]\n" +
                " bucket[5] = [27, 27] -> [38, 38] -> [49, 49]",
                ht.toString());
        Assertions.assertEquals(5,ht.count());

        ht.drop("5");
        Assertions.assertEquals("\n" +
                " bucket[3] = [25, 25]\n" +
                " bucket[4] = [4, 44]\n" +
                " bucket[5] = [27, 27] -> [38, 38] -> [49, 49]",
                ht.toString());
        Assertions.assertEquals(5,ht.count());
    }

    @org.junit.jupiter.api.Test
    void dropBAKEADO() {
        ht.put("2", "22");
        ht.put("1", "11");
        ht.drop("12");
        ht.drop("23");
        ht.drop("1");
        Assertions.assertEquals("\n" + " bucket[2] = [2, 22]", ht.toString());

    }
}