package generic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HRBlock {

	public static void computeManyTaxes(Taxable[] lt) {
		for (Taxable t : lt) {
			computeTaxes(t);
		}
	}

	public static void computeManyTaxes(List<? extends Taxable> lt) {
		for (Taxable t : lt) {
			computeTaxes(t);
		}
	}
	
	
	public static void computeTaxes(Taxable t) {
		// ...
	}
	
	public static void breakIt(List l) {
		l.add("Hello");
		l.add(LocalDate.of(2016, 11, 11));
	}
	
	public static void collectClients(List<? super Taxable> lt) {
		Taxable t = null;
		lt.add(t);
	}
	
	public static void main(String[] args) {
		List<Taxable> clients = new LinkedList<>();
		computeManyTaxes(clients);
		
		breakIt(clients);
		
		for (Object o : clients)
			System.out.println(o);
		Taxable[] ta = null;
		computeManyTaxes(ta);

		Individual[] ia = null;
		computeManyTaxes(ia);

		List<Individual> friends = new ArrayList<>();
//		collectClients(friends);
		computeManyTaxes(friends);

		List<Object> lo = null;
		collectClients(lo);
		
	}

}
