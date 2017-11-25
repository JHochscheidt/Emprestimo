package br.com.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class DateTest {

	
	@Test
	public void dateTest() {
		GregorianCalendar calendar = new GregorianCalendar();
		
		
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
//		calendar.add(GregorianCalendar.DAY_OF_YEAR, 6);
		System.out.println(dt.format(calendar.getTime()));
//		int dia = 0;
//		int mes = 0;
//		int ano = 0;
		System.out.println("DIA " + calendar.get(GregorianCalendar.DAY_OF_MONTH));
		System.out.println("DIA " + (calendar.get(GregorianCalendar.MONTH)+1));
		System.out.println("DIA " + calendar.get(GregorianCalendar.YEAR));
		
		
		
		for (int i = 0; i < 20; i++) {
			if(calendar.get(Calendar.DAY_OF_MONTH) > 28) {
				System.out.println("HUE");
			}			
			calendar. roll(GregorianCalendar.DAY_OF_MONTH, 1);
			System.out.println(dt.format(calendar.getTime()));
			
		}
	}
}
