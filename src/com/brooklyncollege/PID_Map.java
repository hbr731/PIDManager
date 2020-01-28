package com.brooklyncollege;

import java.util.Deque;
import java.util.LinkedList;

public class PID_Map {
    private static final Integer MIN_PID = 300;
    private static final Integer MAX_PID = 5000;

    private static Deque<Integer> qPID = new LinkedList<>();


    //Allocates and returns a pid; returns -1 if if unable to allocate a pid (all pids are in use)
    //
    protected static Integer allocatePID() {
//        try{
//            qArr.threads.add(qPID.remove());
//        } catch (Exception e) {
//            return -1;
//        }
        if (!qPID.isEmpty()) return qPID.remove();
        return -1;
    }

    // Initializes the queue for representing pids; returns -1 if unsuccessful and 1 if successful
    protected static Integer allocateMap() {
        try{
            for (Integer i = MIN_PID; i <= MAX_PID; i++) {
                qPID.offer(i);
            }
        } catch (Exception e) {
            return -1;
        }
        return 1;
    }

    // Releases the PID from the thread that has completed its task.
    protected static void releasePID(Integer pid){
        qPID.offer(pid);
        System.out.println("Process " + pid + " successfully terminated");
    }
}
