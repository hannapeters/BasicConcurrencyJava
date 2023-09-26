public class Main {
	public static class MyThreadedCode implements Runnable {
		public void run() {
			long id = Thread.currentThread().getId();
			System.out.println("Hello world " + id);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		/*
		 * //create a new thread
		 * Thread t = new Thread(new MyThreadedCode());
		 * t.start();
		 * //Code above is for 1A
		 * 
		 * for(int i=0; i<5; i++){
		 * Thread t = new Thread(new MyThreadedCode());
		 * t.start();
		 * t.join();
		 * }
		 * System.out.println("Goodbye");
		 * //Code above is for 1B
		 */

		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(new MyThreadedCode());
			t.start();
			t.join();
		}
		System.out.println("Goodbye");
	}
}
