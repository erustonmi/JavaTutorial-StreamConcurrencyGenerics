package genericclass;

//public class Pair<E, F> {
public class Pair<E extends Colored & Sized> {
	private E left;
	private E right;
//	private F middle;

	public Pair(E left, E right) {
		super();
		this.left = left;
		this.right = right;
	}

	public E getLeft() {
		return left;
	}

	public void setLeft(E left) {
		this.left = left;
	}

	public E getRight() {
		return right;
	}

	public void setRight(E right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "Pair [left=" + left + ", right=" + right + "]";
	}
	
	public boolean isMatched() {
		return left.getColor().equals(right.getColor())
				&& left.getSize() == right.getSize();
	}

}
