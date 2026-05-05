package es.unican.is2.practica5b;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConjuntoOrdenadoTest {

    private ConjuntoOrdenado<Integer> sut;

    @BeforeEach
    void setUp() {
        sut = new ConjuntoOrdenado<>();
    }

    @Test
    void addElementoValidoEnConjuntoVacio() {
        boolean added = sut.add(10);

        assertTrue(added);
        assertEquals(1, sut.size());
        assertEquals(10, sut.get(0));
    }

    @Test
    void addInsertaManteniendoOrdenNatural() {
        sut.add(10);
        sut.add(30);

        sut.add(20);

        assertEquals(3, sut.size());
        assertEquals(10, sut.get(0));
        assertEquals(20, sut.get(1));
        assertEquals(30, sut.get(2));
    }

    @Test
    void addInsertaAlInicio() {
        sut.add(20);
        sut.add(30);

        sut.add(10);

        assertEquals(10, sut.get(0));
        assertEquals(20, sut.get(1));
        assertEquals(30, sut.get(2));
    }

    @Test
    void addInsertaAlFinal() {
        sut.add(10);
        sut.add(20);

        sut.add(30);

        assertEquals(10, sut.get(0));
        assertEquals(20, sut.get(1));
        assertEquals(30, sut.get(2));
    }

    @Test
    void addElementoDuplicado() {
        sut.add(15);

        boolean added = sut.add(15);

        assertFalse(added);
        assertEquals(1, sut.size());
        assertEquals(15, sut.get(0));
    }

    @Test
    void addNull() {
        assertThrows(NullPointerException.class, () -> sut.add(null));
    }

    @Test
    void getIndiceValido() {
        sut.add(3);
        sut.add(1);
        sut.add(2);

        assertEquals(1, sut.get(0));
        assertEquals(2, sut.get(1));
        assertEquals(3, sut.get(2));
    }

    @Test
    void getIndiceNegativo() {
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(-1));
    }

    @Test
    void getIndiceIgualATamano() {
        sut.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(1));
    }

    @Test
    void removeIndiceValido() {
        sut.add(1);
        sut.add(2);
        sut.add(3);

        Integer removed = sut.remove(1);

        assertEquals(2, removed);
        assertEquals(2, sut.size());
        assertEquals(1, sut.get(0));
        assertEquals(3, sut.get(1));
    }

    @Test
    void removeIndiceNegativo() {
        assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(-1));
    }

    @Test
    void removeIndiceIgualATamano() {
        sut.add(5);
        assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(1));
    }

    @Test
    void clearEnConjuntoConElementos() {
        sut.add(1);
        sut.add(2);

        sut.clear();

        assertEquals(0, sut.size());
    }

    @Test
    void clearEnConjuntoVacio() {
        sut.clear();
        assertEquals(0, sut.size());
    }

    @Test
    void sizeTrasSecuenciaDeOperaciones() {
        assertEquals(0, sut.size());
        sut.add(1);
        sut.add(2);
        sut.add(2);
        sut.remove(1);
        assertEquals(1, sut.size());
    }
}
