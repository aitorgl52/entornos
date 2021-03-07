package practicaJUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

class CuentaTest {
	Cuenta objCuenta;
	
	@Before public void before() {
		System.out.println("before");
		objCuenta=new Cuenta("1234","Pepe");
	}
	
	//Comprobar si tras hacer un ingreso inicial a una cuenta, por ejemplo de 500€, 
	//la cuenta tiene ese saldo
	
	 @Test
	    public void testIngreso() throws Exception {
		 
	        objCuenta.ingresar(500);
	        assertEquals(500, objCuenta.getSaldo());
	    }
	 
	 //Comprobar si una cuenta con un saldo, por ejemplo de 500€,
	 //tras retirar ese saldo, la cuenta se encuentra con un saldo 0
	 
	 @Test
	 public void testRetiro() throws Exception {
		 
	        objCuenta.ingresar(500);
	        objCuenta.retirar(500);
	        assertEquals(0, objCuenta.getSaldo());
	    }
	 
	 //Comprobar que al ingresar un saldo de 500€ y retirar 300€,
	 //la cuenta tiene un saldo de 200€
	 
	 @Test
	 public void refactorizacion() throws Exception {
		 
		 objCuenta.ingresar(500);
		 objCuenta.retirar(300);
		 assertEquals(200, objCuenta.getSaldo());
	 }
	 
	 //Comprobar si una cuenta con un saldo, por ejemplo de 500€, 
	 //tras retirar ese saldo con la tarjeta, el
	 //saldo de la tarjeta debe ser igual al de la cuenta que será 0

	 @Test
	 public void testRetiro3() throws Exception {
		 
		 objCuenta.ingresar(500);
		 Date fecha = new Date();
		 Debito objDebito = new Debito("12345789", "Aitor", fecha);
	     objDebito.setCuenta(objCuenta);
	     objDebito.retirar(500);

	     assertTrue(objCuenta.getSaldo() == 0);
	 }
	 
	 //Comprobar que al ingresar (por ejemplo de 500€) en una cuenta
	 //(con un saldo por ejemplo de
	 //500€) con una tarjeta de crédito, el salgo es igual al anterior de la
	 //cuenta más lo ingresado con la
	 //tarjeta (por ejemplo de 1000€). El saldo de la tarjeta y el crédito de la misma,
	 //no debe haber variado con esta operación.
	 
	 @Test
	 public void testIngreso2() throws Exception{
		 
		 Date fecha = new Date();
	     Credito objCredito = new Credito("123456789", "Aitor", fecha, 1000);
	     objCredito.setCuenta(objCuenta);

	     objCuenta.ingresar(500);
	     objCredito.ingresar(500);
	     assertEquals(1000, objCuenta.getSaldo());
	     assertEquals(1000, objCredito.credito);
	 }
	 
	 //Comprobar que al retirar dinero con la tarjeta me cobra un 5% del
	 //importe y como mínimo 3€. (Retirar 500€ → 15€ y 50€ → 3€)
	 
	 @Test
	 public void testImporte() throws Exception{
		 
		 Date fecha = new Date();
		 objCuenta.ingresar(1000);
		 Debito objDebito = new Debito("12345789", "Aitor", fecha);
	     objDebito.setCuenta(objCuenta);
	     objDebito.retirar(500);
	     assertEquals(15, actual);
	 }
	 
	 //Comprobar que al liquidar una tarjeta sobre han hecho varios pagos,
	 //el saldo de la cuenta baja la suma de esos pagos. 
	 //(Cuenta con 500€. Pagos con tarjeta 100€ y 200€. Saldo después de liquidar 200€)
	 
	 @Test
	 public void testPagos() throws Exception {
	     Date fecha = new Date();
	     Credito objCredito = new Credito("123456789", "Aitor", fecha, 500);
	     objCredito.setCuenta(objCuenta);

	     objCuenta.ingresar(500);
	     objCredito.retirar(100);
	     objCredito.retirar(200);

	     objCredito.liquidar(7,2021);
	     assertEquals(200, objCuenta.getSaldo());
	    }
	 
	 
	 
	 
	 
	 
}
