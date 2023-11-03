package com.br.AdHome.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Utils {

	static NumberFormat numberFormat = new DecimalFormat("R$ #,##0.00",
			new DecimalFormatSymbols(new Locale("pt", "BR")));

	public static String doubleToString(Double value) {
		return numberFormat.format(value);
	}

	public static Double stringToDouble(String formattedValue) {
		try {
			Number number = numberFormat.parse(formattedValue);
			return number.doubleValue();
		} catch (ParseException e) {
			// Trate a exceção, se a conversão falhar
			return null; // ou outra ação apropriada
		}
	}
}
