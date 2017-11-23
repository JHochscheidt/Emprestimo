package br.com.cooperalfa.emprestimo.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

public class Teste {

	public static void main(String[] args) {
		Calendar gc = new GregorianCalendar();
		int numPar = Integer.parseInt(JOptionPane.showInputDialog("Quantidade de parcelas"));
		Date diaAtual = new Date();
		for (int e = 0; e < numPar; e++) {
			gc.setTime(diaAtual);
//			System.out.println("gc A " + gc.getTime());
			gc.add(Calendar.MONTH, 1);
//			System.out.println("gc D " + gc.getTime());
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date d = gc.getTime();
			
			
//			System.out.print(gc.get(GregorianCalendar.DAY_OF_MONTH));
//			System.out.print("/"+gc.get(GregorianCalendar.MONTH));
//			System.out.println("/"+gc.get(GregorianCalendar.YEAR));
			System.out.println(df.format(d));
			diaAtual = d;
		}
	}
}
