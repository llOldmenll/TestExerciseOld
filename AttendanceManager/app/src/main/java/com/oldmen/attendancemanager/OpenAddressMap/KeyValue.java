package com.oldmen.attendancemanager.OpenAddressMap;


public class KeyValue {

    private int key;
    private long value;
    private boolean deleted;

    public KeyValue(int key, long value) {
        this.key = key;
        this.value = value;
        this.deleted = false;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean deleteKeyValue() {

        if (this.deleted)
            return false;
        else {
            this.deleted = true;
            return true;
        }

    }
}
