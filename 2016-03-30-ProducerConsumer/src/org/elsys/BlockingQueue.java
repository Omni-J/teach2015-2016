package org.elsys;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;

public class BlockingQueue<T> {

	private int capacity;
	private Queue<T> queue = new LinkedList<T>();

	public BlockingQueue(int capacity) {
		this.capacity = capacity;
	}

	private boolean isFull() {
		return queue.size() >= capacity;
	}

	public synchronized void put(T task) throws InterruptedException {
		while (isFull()) {
			wait();
		}
		queue.add(task);
		notify();
	}

	public synchronized T take() throws InterruptedException {
		while (queue.isEmpty()) {
			wait();
		}
		T task = queue.poll();
		notify();
		return task;
	}

	/**
	 * 
	 * @param task object to add into queue
	 * @param timeout time in millis to wait
	 * @return true if successfull, false otherwise
	 * @throws InterruptedException 
	 */
	public synchronized boolean offer(T task, long timeout) throws InterruptedException {
		while(isFull()){
			wait(timeout); //Does it exits when stopped waiting
		}
		if (isFull()) {
			return false;
		} else {
			queue.add(task);
			notify(); // Is it true ??
			return true;
		}
	}
	
	/**
	 * 
	 * @param timeout in millis
	 * @return object if successfull, on timeout returns null
	 * @throws InterruptedException 
	 */
	public  synchronized T poll(long timeout) throws InterruptedException {
		while(queue.isEmpty()){
			wait(timeout);
		}
		if(queue.isEmpty()){
			return null;
		} else {
			return queue.poll(); // Does it require nofity???
		}
	}
	
	private static AtomicLong producersCount = new AtomicLong(0);

	public static void main(String[] args) {
		BlockingQueue<String> queue = new BlockingQueue<>(10);

		List<Thread> producers = new LinkedList<Thread>();
		for (int i = 0; i < 10; ++i) {
			producers.add(new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							String task = "task" + producersCount.incrementAndGet();
							System.out.println("going to add task: " + task);
							queue.put(task);
							System.out.println("task <" + task + "> added...");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}));
		}

		for (Thread thread : producers) {
			thread.start();
		}

/*		List<Thread> consumers = new LinkedList<>();
		for (int i = 0; i < 5; ++i) {
			consumers.add(new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							Thread.sleep(2000);
							String task = queue.take();
							System.err.println("task consumed: " + task);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}));
		}

		for (Thread thread : consumers) {
			thread.start();
		}

		for (Thread thread : consumers) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
		List<Thread> testers = new LinkedList<Thread>();
		for (int i = 0; i < 10; ++i) {
			testers.add(new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							String task = "task" + producersCount.incrementAndGet();
							System.out.println("going to add task: " + task);
							queue.put(task);
							System.out.println("task <" + task + "> added...");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}));
		}
	}
}
