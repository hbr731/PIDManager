package com.brooklyncollege;

/*
 ***
 *** authors: Habib-ur-Rehman, Shahzeb Ali
 ***
 */

import java.rmi.UnexpectedException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    // Variables that will specify the range of pid values


    // Queue that will hold the Process Ids

    // Instance of the class that will create an ArrayList of threads.
//    static MyThreads qArr = new MyThreads(100);

    public static void main(String[] args) throws UnexpectedException {
        PID_Map pids = new PID_Map();

        Integer allocate_Map = pids.allocateMap();
        // if allocate_map returns -1, there was an error allocating the PIDs.
        if (allocate_Map == -1) throw new IndexOutOfBoundsException();

//        for (Integer i = 0; i < qArr.getNumThreads(); i++) pids.allocatePID();
//
//
//        for (int i = 0; i < qArr.getNumThreads(); i++) {
//            qArr.run();
//
//            pids.releasePID(qArr.threads.get(i));
//            qArr.threads.set(i, 0);
//        }
//        int l = qArr.threads.size();
//        for(int i = l-1; i >= 0; i--) {
//            if(qArr.threads.get(i) == 0) qArr.threads.remove(i);
//        }
//        for(int i = 0; i < qArr.threads.size(); i++) {
//            System.out.println("thread still running: " + qArr.threads.get(i));
//        }
//
//        if(!qArr.threads.isEmpty()) throw new UnexpectedException("Threads array not empty");

        ExecutorService pool = Executors.newFixedThreadPool(100);
        for (int i = 1; i <= 100; i++) {
            MyThreads task = new MyThreads(i, pids);
            pool.execute(task);
        }

        pool.shutdown();

        while(!pool.isTerminated()){}
        System.out.println("All threads in the pool have been released");

    }



}




/* Output
Process successfully terminated
The pid doesnt exist
The pid doesnt exist

Process finished with exit code 0
 */