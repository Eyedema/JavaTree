package Albero;

import java.util.ArrayList;
import java.util.Stack;

public class Albero<T> {

	private int m = 0;
	private int maxNodi = 0;
	private ArrayList<Nodo<T>> albero = null;

	public Albero(int m, int maxNodi) {
		this.m = m;
		this.maxNodi = maxNodi;
		albero = new ArrayList<Nodo<T>>(maxNodi);
		albero.add(null);
	}

	public Nodo<T> inserisciRadice(T info) {
		if (info != null) {
			if (albero.size() > 1 && albero.get(1) != null) {
				return albero.get(1);
			}
			Nodo<T> n = new Nodo<T>(info);
			albero.add(n);
			return n;
		}
		return null;
	}

	public int numeroNodi() {
		int totaleNodi = 0;
		for (int i = 1; i < albero.size(); ++i) {
			Nodo<T> n = albero.get(i);
			if (n != null) {
				totaleNodi++;
			}
		}
		return totaleNodi;
	}

	public Nodo<T> inserisciNodo(T info, int indicePadre, int numFiglio) {
		if (info == null || numFiglio < 1 || numFiglio > m
				|| numeroNodi() >= maxNodi) {
			return null;
		}
		Nodo<T> nodo = null;
		if (indicePadre != -1) {
			int indiceFiglio = (indicePadre - 1) * m + 1 + numFiglio;
			try {
				if (albero.get(indiceFiglio) == null) {
					nodo = new Nodo<T>(info);
					albero.set(indiceFiglio, nodo);
				}
			} catch (IndexOutOfBoundsException e) {
				for (int i = albero.size(); i < indiceFiglio; ++i) {
					albero.add(null);
				}
				nodo = new Nodo<T>(info);
				albero.add(nodo);
			}
		}
		return nodo;
	}

	public Nodo<T> trovaPadre(int indexFiglio) {
		if (indexFiglio <= 1 || indexFiglio >= albero.size()) {
		}
		int temp;
		if (m == 1) {
			temp = -1;
		} else if ((indexFiglio % m) > 1) {
			temp = 1;
		} else {
			temp = 0;
		}
		return albero.get((indexFiglio / m) + temp);
	}

	public int trovaNodo(T info) {
		if (info == null) {
			return -1;
		}
		int indice = -1;
		for (int i = 1; i < albero.size() && indice == -1; ++i) {
			Nodo<T> nodo = albero.get(i);
			if (info.equals(nodo.getInfo())) {
				indice = i;
			}
		}
		return indice;
	}

	public ArrayList<Nodo<T>> creaListaFigli(Nodo<T> padre) {
		int indexPadre = trovaNodo(padre.getInfo());
		if (indexPadre == -1) {
			return null;
		}
		ArrayList<Nodo<T>> lista = new ArrayList<Nodo<T>>(m);
		for (int i = 1; i <= m; i++) {
			int indexFiglio = (indexPadre - 1) * m + 1 + i;
			if (indexFiglio >= albero.size()) {
				lista.add(null);
			} else {
				lista.add(albero.get(indexFiglio));
			}
		}
		return lista;
	}

	public ArrayList<Nodo<T>> visitaAmpiezza() {
		ArrayList<Nodo<T>> ampiezza = new ArrayList<Nodo<T>>();
		for (int i = 0; i < albero.size(); i++) {
			if (albero.get(i) != null) {
				ampiezza.add(albero.get(i));
			}
		}
		return ampiezza;
	}

	public ArrayList<T> visitaProfondita() {
		ArrayList<T> profondita = new ArrayList<T>();
		if (albero.size() > 1) {
			Stack<Nodo<T>> stack = new Stack<>();
			stack.push(albero.get(1));
			while (!stack.isEmpty()) {
				Nodo<T> nodo = stack.pop();
				if (nodo != null) {
					profondita.add(nodo.getInfo());
					ArrayList<Nodo<T>> figli = creaListaFigli(nodo);
					if (figli != null) {
						for (int i = figli.size() - 1; i >= 0; i--) {
							if (figli.get(i) != null) {
								stack.push(figli.get(i));
							}
						}
					}
				}
			}
		}
		return profondita;
	}
}
