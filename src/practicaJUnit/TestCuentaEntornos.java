package practicaJUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class TestCuentaEntornos {
    Cuenta objCuenta;

    @Before
    public void before() {
        objCuenta = new Cuenta("1234", "Pepe");
    }

    // Ejercicio 1
    @Test
    public void testEjercicio1() throws Exception {
        objCuenta.ingresar(500);
        assertEquals(500, objCuenta.getSaldo());
    }

    // Ejercicio 2
    @Test
    public void testEjercicio2() throws Exception {
        objCuenta.ingresar(500);
        objCuenta.retirar(objCuenta.getSaldo());
        assertEquals(0, objCuenta.getSaldo());
    }

    // Ejercicio 3
    @Test
    public void testEjercicio3() throws Exception {
        objCuenta.ingresar(500);
        objCuenta.retirar(300);
        assertEquals(200, objCuenta.getSaldo());
    }

    // Ejercicio 4
    @Test
    public void testEjercio4() throws Exception {
        objCuenta.ingresar(500);

        Date today = new Date();
        Debito objDebito = new Debito("1234", "Aitor", today);
        objDebito.setCuenta(objCuenta);

        objDebito.retirar(500);

        assertTrue(objCuenta.getSaldo() == 0);
    }

    // Ejercicio 5
    @Test
    public void testEjercicio5() throws Exception {

        Date today = new Date();
        Credito objCredito = new Credito("12334", "MrPotato", today, 1000);
        objCredito.setCuenta(objCuenta);

        objCuenta.ingresar(500);
        objCredito.ingresar(500);

        assertEquals(1000, objCuenta.getSaldo());
        assertEquals(1000, objCredito.credito);
    }
    //Ejercicio 6
    //ParameterizedTest

    //Ejercicio 7
    @Test
    public void ejercicio7() throws Exception {
        Date today = new Date();
        Credito objCredito = new Credito("12334", "MrPotato", today, 1000);
        objCredito.setCuenta(objCuenta);

        objCuenta.ingresar(500);
        objCredito.retirar(100);
        objCredito.retirar(200);

        objCredito.liquidar(1,2021);

        assertEquals(200, objCuenta.getSaldo());
    }
}
