import java.util.*;
public class SuperSpecialArrayList<E> {

private int size;
private int capacity;
private Object[] array;

public SuperSpecialArrayList(int c) {
	capacity = c;
	array = new Object[capacity];
}

public Object get(int index) {
	if(index > size()) {
		return null;
	}
	return array[index];
}

public void set(int index, E e) {
	if(!(index > size())) {
		array[index] = e;
	}
}

public void add(E e) {
	ensureCapacity(1);
	array[size()] = e;
	setSize(size() + 1);
}

public void addAll(Collection<E> c) {
	ensureCapacity(c.size());
	Iterator<E> i = c.iterator();
	int counter = 0;
	do {
		array[size() + counter] = i.next();
		counter++;
	}
	while(i.hasNext());
	setSize(size() + counter);
}

public Object remove(int index) {
	Object temp = array[index];
	for(int i = index; i < size() - 1; i++) {
		array[i] = array[i + 1];
	}
	setSize(size() - 1);
	return temp;
}

public int size() {
	return size;
}

public void setSize(int s) {
	size = s;
}

public int capacity() {
	return capacity;
}

public void setCapacity() {
	capacity *= 1.5;
}

public void ensureCapacity(int addition) {
	if(addition + size() >= capacity()) {
		setCapacity();
		Object[] temp = new Object[capacity];
		for(int i = 0; i < size(); i++) {
			temp[i] = array[i];
		}
		array = temp;
	}
}

public String toString() {
	String s = "[ ";
	for(int i = 0; i < size(); i++) {
		s+= array[i] + " ";
	}
	s+= "]";
	return s;
	
}

}