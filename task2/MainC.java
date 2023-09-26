public class MainC {
	// class for the thread, this thread increments a shared counter 1 million times
	public static class Incrementer implements Runnable {
		public synchronized void run() {
			for (int j = 0; j < 1000000; j++) {
				synchronized (MainC.class) {
					counter++;
				}
			}
		}
	}

	static volatile int counter = 0;

	public static void main(String[] args) throws InterruptedException {
		// measuring the time it takes to execute the run_experiment method for n cores
		// where n spans from 2,4,8,16,32,64

		// this loop is for warm up for the cache
		int x = 20;
		int n = 1;
		for (int i = 0; i < x; i++) {
			run_experiment(n);
		}

		// this loop executes the actual measurments we use
		int y = 10;
		for (int i = 0; i < y; i++) {
			long elapsedTime = run_experiment(n);
			System.out.println(elapsedTime);
		}

	}

	// this method is used for running n increment threads, each incrementing the
	// shared int
	// counter 1 million times and measuring the execution time in nanoseconds
	public static long run_experiment(int n) throws InterruptedException {
		Thread[] t = new Thread[n];
		long startTime = 0;
		// create n threads
		for (int i = 0; i < n; i++) {
			// start measuring execution time
			if (i == 0) {
				startTime = System.nanoTime();
			}
			t[i] = new Thread(new Incrementer());
			t[i].start();
		}

		for (int i = 0; i < n; i++) {
			t[i].join();
		}
		// stop the time measuring
		long endTime = System.nanoTime();
		return endTime - startTime;
	}
}
