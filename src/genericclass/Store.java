package genericclass;

import java.awt.Color;
import java.time.LocalDate;

public class Store {
/*
	public static void main(String[] args) {
//		Pair<String> pstring = new Pair<>("Fred", LocalDate.of(2016, 11, 11));
		Pair<String> pstring = new Pair<>("Fred", "Jones");
		String firstName = pstring.getLeft();
		System.out.println("left is " + firstName);
//		pstring.setRight(LocalDate.of(1066, 11, 11));
		Object o = pstring.getRight();
		System.out.println("right is " + o);
	}
*/
	
	public static void main(String [] args) {
		Pair<Shoe> shoes = new Pair<>(new Shoe(Color.BLACK, 10), new Shoe(Color.BLACK, 10));
		Pair<Shoe> oddShoes = new Pair<>(new Shoe(Color.BLACK, 10), new Shoe(Color.RED, 9));
//		Pair<String> ps;
		
		System.out.println("shoes is matched? " + shoes.isMatched());
		System.out.println("oddShoes is matched? " + oddShoes.isMatched());
	}

}
