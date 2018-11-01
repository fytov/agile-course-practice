package ru.unn.agile.quadraticequation.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuadraticEquationTest {
    @Test
    public void canGetQuadraticCoefficient() {
        QuadraticEquation func = new QuadraticEquation(5.1, 6.1, 7.1);

        double a = func.getA();

        assertEquals(5.1, a, QuadraticEquation.EPSILON);
    }

    @Test
    public void canGetLinearCoefficient() {
        QuadraticEquation func = new QuadraticEquation(5.1, 6.1, 7.1);

        double b = func.getB();

        assertEquals(6.1, b, QuadraticEquation.EPSILON);
    }

    @Test
    public void canGetFreeTerm() {
        QuadraticEquation func = new QuadraticEquation(5.1, 6.1, 7.1);

        double c = func.getC();

        assertEquals(7.1, c, QuadraticEquation.EPSILON);
    }
}
