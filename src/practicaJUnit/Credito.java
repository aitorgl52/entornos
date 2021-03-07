package practicaJUnit;

import java.util.Vector;
import java.util.Date;

public class Credito extends Tarjeta
{
	protected double credito;
	protected Vector movimientos;
	public Credito(String _numero, String _titular, Date _fechaCaducidad, double _credito)
	{
		super(_numero, _titular, _fechaCaducidad);
		credito=_credito;
		movimientos=new Vector();
	}
	public void retirar(double x) throws Exception
	{
		Movimiento m=new Movimiento();
		m.setConcepto("Retirada en cajero autom∑tico");
		x=(x*0.05<3.0 ? 3 : x*0.05); // A“adimos una comisi€n de un 5%, mÃnimo de 3 euros.
		m.setImporte(x);
		movimientos.addElement(m);
		if (x>getCreditoDisponible())
			throw new Exception("Cr»dito insuficiente");
	}
	public void ingresar(double x) throws Exception
	{
		Movimiento m=new Movimiento();
		m.setConcepto("Ingreso en cuenta asociada (cajero autom∑tico)");
		m.setImporte(x);
		movimientos.addElement(m);
		cuentaAsociada.ingresar(x);
	}
	public void pagoEnEstablecimiento(String datos, double x) throws Exception
	{
		Movimiento m=new Movimiento();
		m.setConcepto("Compra a cr»dito en: " + datos);
		m.setImporte(x);
		movimientos.addElement(m);
	}
	public double getSaldo()
	{
		double r=0.0;
		for (int i=0; i<this.movimientos.size(); i++)
		{
			Movimiento m=(Movimiento) movimientos.elementAt(i);
			r+=m.getImporte();
		}
		return r;
	}
	public double getCreditoDisponible()
	{
		return credito-getSaldo();
	}

	public void liquidar(int mes, int a“o)
	{
		Movimiento liq=new Movimiento();
		liq.setConcepto("Liquidaci€n de operaciones tarj. cr»dito, " + (mes+1) + " de " + (a“o+1900));
		double r=0.0;
		for (int i=0; i<this.movimientos.size(); i++)
		{
			Movimiento m=(Movimiento) movimientos.elementAt(i);
			if (m.getFecha().getMonth()+1==mes && m.getFecha().getYear()+1900==a“o)
				r+=m.getImporte();
		}
		liq.setImporte(r);
		if (r!=0)
			cuentaAsociada.addMovimiento(liq);
	}
}
