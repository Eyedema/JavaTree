package Albero;

public class Nodo<T> {

	private T info;

	@Override
	public String toString() {
		return "" + this.info;
	}

	public Nodo(T info) {
		this.info = info;
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}
}
