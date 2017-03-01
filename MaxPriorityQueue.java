package heapsAndPriorityQueues;

public class MaxPriorityQueue<T extends Comparable<T>> {
	private MaxHeap<T> heap;

	public MaxHeap<T> getHeap() {
		return heap;
	}
	
	// precondition: array must not have null elements
	public MaxPriorityQueue(T[] array) {
		heap = new MaxHeap<T>(array);
	}

	// precondition: data must not be null
	// postCondition: inserts a new T element into the heap
	// also will double the heap if heap runs out of space
	public void insert(T data) {
		heap.insert(data);
	}
	
	// postcondition: will throw IllegalStateException if heap size < 1
	// else return the maximum element from the heap
	public T getMax() {
		return heap.getMax();
	}

	// postcondition: will throw IllegalStateException if heap size < 1
	// else remove the maximum element from the heap and return it
	public T extractMax() {
		return heap.extractMax();
	}

	// postcondition: throws IllegalArgumentException if newValue < value at heap[index]
	// postcondition: increases value at to newValue and maintains heap property
	public void increaseKey(int index, T newValue) {
		heap.heapIncreaseKey(heap.getHeap(), index, newValue);
	}
}
