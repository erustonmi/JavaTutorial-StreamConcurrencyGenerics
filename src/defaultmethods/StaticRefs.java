package defaultmethods;

interface StaticTwo {
	static String doStuff() {
		return "StaticTwo.doStuff";
	}
}

class Stat implements StaticTwo {
	
}


public class StaticRefs {
	public static int VALUE = 99;

	public static void main(String[] args) {
		System.out.println("StaticRefs.VALUE is " + StaticRefs.VALUE);
		StaticRefs target = new StaticRefs();
		System.out.println("target.VALUE is " + target.VALUE);
		
		System.out.println("StaticTwo.doStuff() gives " + StaticTwo.doStuff());
		StaticTwo t2 = new Stat();
//		System.out.println("t2.doStuff() gives " + t2.doStuff());
		
	}

}
