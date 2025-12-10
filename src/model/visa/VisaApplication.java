package model.visa;

import exception.MissingDocumentException;
import java.util.ArrayList;
import java.util.List;

public abstract class VisaApplication implements Exportable {
    protected String applicantName;
    protected List<Document> documents;

    public VisaApplication(String applicantName) {
        this.applicantName = applicantName;
        this.documents = new ArrayList<>();
    }

    public void addDocument(Document doc) {
        this.documents.add(doc);
    }

    public List<Document> getDocuments() {
        return documents;
    }

    // --- Abstract Metotlar (Alt sınıflar doldurmak ZORUNDA) ---
    public abstract void validateDocuments() throws MissingDocumentException;
    public abstract double calculateFee();

    // Exportable Interface'inden gelen metot
    @Override
    public String exportToText() {
        return "Başvuru Sahibi: " + applicantName + ", Ücret: " + calculateFee();
    }
}