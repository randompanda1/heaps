package heapsAndPriorityQueues;

import arrayutils.ArrayUtils;

/*
 * Bloch would probably suggest that I "favor" a List as the underlying
 * structure since I am dealing with generics, but I just wanted to go a
 * little lower level for fun. Perhaps on an Immutable Heap I might make
 * later, I'll use a List.
 */
public class MaxHeap<T extends Comparable<T>> {
	private T[] heap;
	private int heapLength;

	public MaxHeap(T[] array) {
		heap = array;
		heapLength = heap.length - 1;
		buildHeap(heap);
	}

	public T[] getHeap() {
		return heap;
	}

	public int getHeapLength() {
		return heapLength;
	}

	public T getMax() {
		if (heapLength < 0)
			throw new IllegalStateException("Heap is empty. MaxHeap.getMax");
		return heap[0];
	}

	public T extractMax() {
		if (heapLength < 0)
			throw new IllegalStateException("Heap is empty. MaxHeap.extractMax");
		T max = heap[0];
		heap[0] = heap[heapLength--];
		maxHeapify(heap, 0);
		return max;
	}

	// postConditon: sorts the heap into natural order
	// Once called, the "heap" can no longer be guaranteed an actual heap
	public void naturalOrderHeapSort() {
		int heapLengthCopy = heapLength;
		for (int i = heapLength; i > 0; i--) {
			ArrayUtils.swap(heap, 0, i);
			--heapLength;
			maxHeapify(heap, 0);
		}
		printHeap(heapLengthCopy);
	}

	public void buildHeap(T[] array) {
		for (int i = array.length / 2 - 1; i >= 0; i--)
			maxHeapify(array, i);
	}

	private void maxHeapify(T[] array, int i) {
		if (i * 2 + 1 <= heapLength && i > -1) {
			int j = -1;
			if (array[i * 2 + 1].compareTo(array[i]) > 0)
				j = i * 2 + 1;
			if (i * 2 + 2 <= heapLength && array[i * 2 + 2].compareTo(array[i]) > 0
					&& array[i * 2 + 2].compareTo(array[i * 2 + 1]) > 0)
				j = i * 2 + 2;
			if (j > -1)
				ArrayUtils.swap(array, i, j);
			maxHeapify(array, j);
		}
	}

	// postCondition: inserts a new T element into the heap
	// if the heap.length < heapLength + 1, then doubles the array size
	public void insert(T data) {
		if (heap.length - 1 < heapLength + 1)
			doubleArray();
		heap[++heapLength] = (T) (Integer) Integer.MIN_VALUE;
		heapIncreaseKey(heap, heapLength, data);
	}

	public void heapIncreaseKey(T[] heap, int index, T data) {
		if (data.compareTo(heap[index]) < 0)
			throw new IllegalArgumentException("New value is smaller. MaxHeap.heapIncreaseKey");
		heap[index] = data;
		int parent = index % 2 == 0 ? index / 2 - 1 : index / 2;
		while (index > 0 && heap[parent].compareTo(heap[index]) < 0) {
			ArrayUtils.swap(heap, index, parent);
			index = parent;
			parent = index % 2 == 0 ? index / 2 - 1 : index / 2;
		}
	}
	
	private void doubleArray() {
		T[] newArray = (T[]) new Comparable[heap.length * 2];
		for (int i = 0; i < heap.length; i++)
			newArray[i] = heap[i];
		heap = newArray;
	}
	
	public void printHeap(int heapLengthCopy) {
		System.out.print("[");
		for (int i = 0; i < heapLengthCopy; i++)
			System.out.print(heap[i] + ", ");
		System.out.print(heap[heapLengthCopy]);
		System.out.println("]");
	}
}
