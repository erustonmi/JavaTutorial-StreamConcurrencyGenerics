package defaultmethods;

public class Person implements Addressable, MemoryLocation {
	private String city;
	private String street;
	
	public Person(String city, String street) {
		super();
		this.city = city;
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}
	
	@Override
	public String getAddress() {
		return "Under a bench at " + Addressable.super.getAddress();
	}

	public static void main(String[] args) {
		Addressable a = new Person("OverHere", "Hometown");
		System.out.println(a.getStreet() + ", " + a.getCity());
		System.out.println(a.getAddress());
	}
}
