package instance;

import java.util.Iterator;

public class FakeList<E> implements Iterable<E> {

	@SuppressWarnings("unchecked")
	private E[] data = (E[]) (new Object[10]);
	private int count = 0;

	public void add(E e) {
		data[count++] = e;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("FakeList: [");
		for (int i = 0; i < count; i++) {
			sb.append(data[i]).append(", ");
		}
		sb.setLength(sb.length() - 2);
		sb.append(" ]");
		return sb.toString();
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private int progress = 0;

			@Override
			public boolean hasNext() {
				return progress < count;
			}

			@Override
			public E next() {
				return data[progress++];
			}
		};
	}

	public static void main(String[] args) {
		FakeList<String> fls = new FakeList<>();
		fls.add("Fred");
		fls.add("Jim");
		fls.add("Sheila");

		System.out.println("List is " + fls);
		fls.forEach(System.out::println);
		System.out.println("--------");
		for (String s : fls) {
			System.out.println(s);
		}
		
		Iterator<String> i1 = fls.iterator();
		Iterator<String> i2 = fls.iterator();
		
		System.out.println("i1 has " + i1.next());
		System.out.println("i2 has                        " + i2.next());
		System.out.println("i2 has                        " + i2.next());
		System.out.println("i1 has " + i1.next());
		System.out.println("i1 has " + i1.next());
		System.out.println("i2 has                        " + i2.next());
		
	}
}
