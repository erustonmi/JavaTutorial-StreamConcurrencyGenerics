package defaultmethods;

public interface MemoryLocation {
	default String getAddress() {
		return "0xCAFEBABE";
	}
}
