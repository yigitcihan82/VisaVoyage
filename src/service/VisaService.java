package service;

import model.visa.SchengenVisaApplication;
import model.visa.UsVisaApplication;
import model.visa.VisaApplication;

public class VisaService {

    public VisaApplication createApplication(String countryCode, String applicantName) {
        // Factory Mantığı: Girdiye göre doğru nesneyi döndürür
        if (countryCode.equalsIgnoreCase("FR") || countryCode.equalsIgnoreCase("DE")) {
            return new SchengenVisaApplication(applicantName);
        } else if (countryCode.equalsIgnoreCase("US")) {
            return new UsVisaApplication(applicantName);
        } else {
            System.out.println("Bu ülke için otomatik vize sistemi henüz yok.");
            return null;
        }
    }
}