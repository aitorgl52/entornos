package practicaJUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class TestCuenta {
	Cuenta objCuenta;
	
	@Before public void before() {
		System.out.println("before");
		objCuenta=new Cuenta("1234","Pepe");
	}
	@Test public void testIngreso() throws Exception {
		objCuenta.ingresar(500);
		assertEquals(500,objCuenta.getSaldo());
	}

}
