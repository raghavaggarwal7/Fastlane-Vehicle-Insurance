package com.fastlane.service;

import java.time.LocalDate;
import java.util.Calendar;

import com.fastlane.bean.BikeDetailsStore;
import com.fastlane.bean.CarDetailsStore;

public class Validator {
	public Float validate(CarDetailsStore car) {
		LocalDate today=LocalDate.now();
		Float idv=null;
		Calendar c1=car.getDateOfPurchase();
		LocalDate dateOfPurchase=LocalDate.of(c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DATE));
		if(today.isBefore(dateOfPurchase.plusMonths(6)) || today.isEqual(dateOfPurchase.plusMonths(6)))
		{
			//System.out.println("before 6 months");
			idv=(95*(car.getCarPrice()))/100;
		}
		else if(today.isBefore(dateOfPurchase.plusYears(1)) || today.isEqual(dateOfPurchase.plusYears(1)))
		{
			//System.out.println("btw 6 months and 1 year");
			idv=(85*(car.getCarPrice()))/100;
		}
		else if(today.isBefore(dateOfPurchase.plusYears(2)) || today.isEqual(dateOfPurchase.plusYears(2)))
		{
			//System.out.println("btw 1 year and 2 years");
			idv=(80*(car.getCarPrice()))/100;
		}
		else if(today.isBefore(dateOfPurchase.plusYears(3)) || today.isEqual(dateOfPurchase.plusYears(3)))
		{
			//System.out.println("btw 2 year and 3 years");
			idv=(70*(car.getCarPrice()))/100;
		}
		else if(today.isBefore(dateOfPurchase.plusYears(4)) || today.isEqual(dateOfPurchase.plusYears(4)))
		{
			//System.out.println("btw 3 year and 4 years");
			idv=(60*(car.getCarPrice()))/100;
		}
		else if(today.isBefore(dateOfPurchase.plusYears(5)) || today.isEqual(dateOfPurchase.plusYears(5)))
		{
			//System.out.println("btw 4 year and 5 years");
			idv=(50*(car.getCarPrice()))/100;
		}
		else
		{
			//System.out.println("exceeding 5 years");
			idv=(40*(car.getCarPrice()))/100;
		}
		return idv;
	}
	
	public Float validateBike(BikeDetailsStore bike) {
		LocalDate today=LocalDate.now();
		Float idv=null;
		Calendar c1=bike.getDateOfPurchase();
		LocalDate dateOfPurchase=LocalDate.of(c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DATE));
		if(today.isBefore(dateOfPurchase.plusMonths(6)) || today.isEqual(dateOfPurchase.plusMonths(6)))
		{
			//System.out.println("before 6 months");
			idv=(95*(bike.getBikePrice()))/100;
		}
		else if(today.isBefore(dateOfPurchase.plusYears(1)) || today.isEqual(dateOfPurchase.plusYears(1)))
		{
			//System.out.println("btw 6 months and 1 year");
			idv=(85*(bike.getBikePrice()))/100;
		}
		else if(today.isBefore(dateOfPurchase.plusYears(2)) || today.isEqual(dateOfPurchase.plusYears(2)))
		{
			//System.out.println("btw 1 year and 2 years");
			idv=(80*(bike.getBikePrice()))/100;
		}
		else if(today.isBefore(dateOfPurchase.plusYears(3)) || today.isEqual(dateOfPurchase.plusYears(3)))
		{
			//System.out.println("btw 2 year and 3 years");
			idv=(70*(bike.getBikePrice()))/100;
		}
		else if(today.isBefore(dateOfPurchase.plusYears(4)) || today.isEqual(dateOfPurchase.plusYears(4)))
		{
			//System.out.println("btw 3 year and 4 years");
			idv=(60*(bike.getBikePrice()))/100;
		}
		else if(today.isBefore(dateOfPurchase.plusYears(5)) || today.isEqual(dateOfPurchase.plusYears(5)))
		{
			//System.out.println("btw 4 year and 5 years");
			idv=(50*(bike.getBikePrice()))/100;
		}
		else
		{
			//System.out.println("exceeding 5 years");
			idv=(40*(bike.getBikePrice()))/100;
		}
		return idv;
	}
}