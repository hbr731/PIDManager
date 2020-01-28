package com.brooklyncollege;

//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import static java.lang.Thread.currentThread;

public class MyThreads implements Runnable{
	private Integer threadID;
	private PID_Map pids;

//	protected List<Integer> threads;

	public MyThreads() {
//		this.threads = Collections.synchronizedList(new ArrayList<>());
	}

	public MyThreads(Integer threadID, PID_Map pids) {
//		this.numThreads = numThreads;
//		this.threads =  Collections.synchronizedList(
//				new ArrayList<>(this.numThreads));
		this.threadID = threadID;
		this.pids = pids;
	}

	@Override
	public void run() {
		Lock lock1 = new ReentrantLock();
		Lock lock2 = new ReentrantLock();


		Integer newPid;
//		System.out.println("Thread "+ currentThread().getName());

		lock1.lock();
		try {
			newPid = pids.allocatePID();
			while (newPid == -1) newPid = pids.allocatePID();
		} finally {
			lock1.unlock();
		}

		currentThread().setName(newPid.toString());

		try {
			Thread.sleep((long)(Math.random() * 100));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Integer toRelease = Integer.valueOf(currentThread().getName());

		lock2.lock();
		try {
			pids.releasePID(toRelease);
		} finally {
			lock2.unlock();
		}
		System.out.println("Released: " + toRelease);
	}

//	public Integer getNumThreads() {
//		return numThreads;
//	}
}