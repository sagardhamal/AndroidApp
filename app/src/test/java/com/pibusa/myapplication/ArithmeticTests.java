package com.pibusa.myapplication;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by AOWSPL on 27/05/2017.
 */

public class ArithmeticTests {

    Arithmetic a;

    @Before
    public void beforeEachTest() {
        a = new Arithmetic();
    }


    @Test
    public void testAdd() {
        Arithmetic a = new Arithmetic();
        assertTrue("addition logic is wrong", a.add(0, 0) == 0);
        assertTrue("addition logic is wrong", a.add(1, 3) == 4);
        assertTrue("addition logic is wrong", a.add(-10, 5) == -5);
        assertTrue("addition logic is wrong", a.add(-3, -4) == -7);
    }

    @Test
    public void testMul() {
        Arithmetic a = new Arithmetic();
        assertTrue("addition logic is wrong", a.mul(0, 0) == 0);
        assertTrue("addition logic is wrong", a.mul(1, 3) == 3);
        assertTrue("addition logic is wrong", a.mul(-10, 5) == -50);
        assertTrue("addition logic is wrong", a.mul(-3, -4) == 12);
    }

}
