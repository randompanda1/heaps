package heapsAndPriorityQueues;

import java.util.Scanner;

public class AuctionForCabbages {
	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		Integer[] array = new Integer[5];
		for (int i = 0; i < 5; i++)
			array[i] = (Integer) i + 1;
		MaxPriorityQueue<Integer> queue = new MaxPriorityQueue<>(array);
		System.out.println(
				"Welcome to our Cabbage Auction. The highest bid right now is " + queue.getMax() + " dollars.");
		System.out.println("Place a bid, or type \"-1 + Enter\" to quit");
		int bid;
		while ((bid = conIn.nextInt()) != -1) {
			queue.insert(bid);
			System.out.println("Great, your bid has been placed!");
			System.out.println("The highest bid right now is " + queue.getMax() + " dollars.");
			System.out.println("Place another bid, or type \"-1 + Enter\" to quit");
		}
		System.out.println("The bids placed, smallest to greatest is the below:");
		queue.getHeap().naturalOrderHeapSort();
		conIn.close();
	}
}
