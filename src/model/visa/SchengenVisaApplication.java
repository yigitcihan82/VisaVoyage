package model.visa;

import exception.MissingDocumentException;

public class SchengenVisaApplication extends VisaApplication {

    public SchengenVisaApplication(String applicantName) {
        super(applicantName);
    }

    @Override
    public double calculateFee() {
        return 80.0; // Schengen vize ücreti (Euro bazlı düşünülebilir)
    }

    @Override
    public void validateDocuments() throws MissingDocumentException {
        // Schengen için Pasaport, Fotoğraf ve Sigorta ŞART olsun.
        boolean hasPassport = false;
        boolean hasInsurance = false;

        for (Document doc : documents) {
            if (doc.getType() == DocumentType.PASSPORT) hasPassport = true;
            if (doc.getType() == DocumentType.INSURANCE) hasInsurance = true;
        }

        if (!hasPassport) {
            throw new MissingDocumentException("Schengen vizesi için PASAPORT eksik!");
        }
        if (!hasInsurance) {
            throw new MissingDocumentException("Schengen vizesi için SEYAHAT SİGORTASI eksik!");
        }

        System.out.println("Schengen evrakları tam!");
    }
}
