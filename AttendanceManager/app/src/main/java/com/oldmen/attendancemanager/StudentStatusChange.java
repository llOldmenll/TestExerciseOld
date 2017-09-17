package com.oldmen.attendancemanager;



public interface StudentStatusChange {

    void onStatusChanged(Student std, String newStatus);

}
