package com.oldmen.attendancemanager.OpenAddressMap;


public class OpenAddressHashMap {

    private KeyValue[] table;
    private int size;

    public OpenAddressHashMap() {
        size = Integer.MAX_VALUE;
        this.table = new KeyValue[size];
    }

    public OpenAddressHashMap(int size) {
        this.size = size;
        this.table = new KeyValue[this.size];
    }

    public boolean put(int x, long y) {
        int h = getHash(x);

        try{
            if (table[h].isDeleted() ) {
                table[h] = new KeyValue(x, y);
                return true;
            }
            for (int i = h + 1; i != h; i = (i + 1) % size) {
                if (table[i].isDeleted() || table[i].getKey() == x) {
                    table[i] = new KeyValue(x, y);
                    return true;
                }
            }
            return false;
        } catch(NullPointerException e) {
            table[h] = new KeyValue(x, y);
            return true;
        }
    }

    public boolean delete(int x) {
        int h = getHash(x);
        try{
            if (table[h].getKey() == x) {
                table[h].deleteKeyValue();
                return true;
            }
            for (int i = h + 1; i != h; i = (i + 1) % size) {
                if (table[i].getValue() == x && !table[i].isDeleted()) {
                    table[i].deleteKeyValue();
                    return true;
                }
            }
            return false;
        } catch (NullPointerException e){
            return false;
        }
    }

    public Long get(int x) {
        int h = getHash(x);
        try{
            if (table[h].getKey() == x && !table[h].isDeleted()) {
                return table[h].getValue();
            }
            for (int i = h + 1; i != h; i = (i + 1) % size) {
                if(table[i].getValue() == x && !table[i].isDeleted()) {
                    return table[h].getValue();
                }
            }
            return null;
        }catch(NullPointerException e){
            return null;
        }
    }


    private int getHash(Integer x) {
        return x.hashCode();
    }

}
