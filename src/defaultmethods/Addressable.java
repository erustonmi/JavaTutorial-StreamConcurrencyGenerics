package defaultmethods;

public interface Addressable {
	String getCity();
	String getStreet();
//	String getAddress();
	default String getAddress() {
		return getCity() + "\n" + getStreet();
	}
}
