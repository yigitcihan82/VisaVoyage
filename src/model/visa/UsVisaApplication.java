package model.visa;

import exception.MissingDocumentException;

public class UsVisaApplication extends VisaApplication {

    public UsVisaApplication(String applicantName) {
        super(applicantName);
    }

    @Override
    public double calculateFee() {
        return 185.0; // ABD vize ücreti (Dolar bazlı)
    }

    @Override
    public void validateDocuments() throws MissingDocumentException {
        // ABD için Pasaport ve DS160 Formu ŞART olsun.
        boolean hasPassport = false;
        boolean hasForm = false;

        for (Document doc : documents) {
            if (doc.getType() == DocumentType.PASSPORT) hasPassport = true;
            if (doc.getType() == DocumentType.DS160_FORM) hasForm = true;
        }

        if (!hasPassport) {
            throw new MissingDocumentException("ABD vizesi için PASAPORT eksik!");
        }
        if (!hasForm) {
            throw new MissingDocumentException("ABD vizesi için DS-160 FORMU eksik!");
        }

        System.out.println("ABD vizesi evrakları tam!");
    }
}