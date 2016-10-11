package complexlambda;

interface Stuff {
	void doStuff(String s, Integer i);
}

class SimpleStuff implements Stuff {

	@Override
	public void doStuff(String s, Integer i) {
		System.out.println("in SimpleStuff.doStuff");
	}
	
}

class NotQuiteStuff {
	public void banana(String s, Integer i) {
		System.out.println("in NotQuiteStuff.banana");
	}
}

class StuffAdapter implements Stuff {
	private NotQuiteStuff nqs;
	public StuffAdapter(NotQuiteStuff nqs) {
		this.nqs = nqs;
	}
	@Override
	public void doStuff(String s, Integer i) {
		this.nqs.banana(s, i);
	}
}

public class FakeInterface {

	public static void useStuffer(Stuff s) {
		s.doStuff("hello", 1);
	}
	
	public static void main(String[] args) {
		Stuff s = new SimpleStuff();
		useStuffer(s);
		NotQuiteStuff nqs = new NotQuiteStuff();
		useStuffer(nqs::banana);
		useStuffer((x,i)-> nqs.banana(x, i));
		useStuffer(new Stuff(){

			@Override
			public void doStuff(String s, Integer i) {
				nqs.banana(s, i);
			}
		});
		
		useStuffer(new StuffAdapter(nqs));
		
		Stuff xxx = nqs::banana;
		System.out.println("xxx gives: " + xxx.getClass().getName());
	}

}
