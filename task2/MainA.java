public class MainA {
	// class for the thread, this thread increments a shared counter 1 million times
	public static class Incrementer implements Runnable {
		public void run() {
			for (int j = 0; j < 1000000; j++) {
				counter++;
			}
		}
	}

	static volatile int counter = 0;

	public static void main(String[] args) throws InterruptedException {
		int n = 4;
		Thread[] t = new Thread[n];
		for (int i = 0; i < n; i++) {
			t[i] = new Thread(new Incrementer());
			t[i].start();
		}

		for (int i = 0; i < n; i++) {
			t[i].join();
		}

		System.out.println(counter);
	}
}
