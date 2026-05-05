package es.unican.is2.practica5b;

import java.util.ArrayList;
import java.util.List;

import es.unican.is2.adt.IConjuntoOrdenado;

/**
 * Implementacion del ADT ConjuntoOrdenado.
 */
public class ConjuntoOrdenado<E extends Comparable<E>> implements IConjuntoOrdenado<E> {

    private final List<E> lista = new ArrayList<>();

    @Override
    public E get(int index) {
        return lista.get(index);
    }

    @Override
    public boolean add(E elemento) {
        if (elemento == null) {
            throw new NullPointerException();
        }

        int index = 0;
        while (index < lista.size() && elemento.compareTo(lista.get(index)) > 0) {
            index++;
        }

        if (index < lista.size() && elemento.compareTo(lista.get(index)) == 0) {
            return false;
        }

        lista.add(index, elemento);
        return true;
    }

    @Override
    public E remove(int index) {
        return lista.remove(index);
    }

    @Override
    public int size() {
        return lista.size();
    }

    @Override
    public void clear() {
        lista.clear();
    }
}

