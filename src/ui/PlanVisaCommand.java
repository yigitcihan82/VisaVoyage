package ui;

import exception.MissingDocumentException;
import model.visa.Document;
import model.visa.DocumentType;
import model.visa.VisaApplication;
import service.VisaService;

public class PlanVisaCommand implements Command {
    private VisaService visaService;

    public PlanVisaCommand(VisaService visaService) {
        this.visaService = visaService;
    }

    @Override
    public void execute() {
        InputHelper.printSeparator();
        System.out.println("       VİZE BAŞVURU SİMÜLASYONU");
        InputHelper.printSeparator();

        String country = InputHelper.readString("Başvuru yapılacak ülke kodu (US / FR / DE)");
        String name = InputHelper.readString("Başvuru sahibinin adı");

        VisaApplication app = visaService.createApplication(country, name);

        if (app == null) {
            return; // Desteklenmeyen ülke
        }

        System.out.println("\n>> Başvuru taslağı oluşturuldu: " + app.exportToText());
        System.out.println(">> Şimdi evrakları kontrol edeceğiz.\n");

        // İnteraktif Belge Ekleme
        boolean addPassport = InputHelper.readString("Pasaport belgesini sisteme yükleyelim mi? (E/H)").equalsIgnoreCase("E");
        if (addPassport) {
            app.addDocument(new Document(DocumentType.PASSPORT, "TR-U12345"));
            System.out.println("+ Pasaport eklendi.");
        }

        boolean addForm = false;
        if (country.equalsIgnoreCase("US")) {
            addForm = InputHelper.readString("DS-160 Formunu yükleyelim mi? (E/H)").equalsIgnoreCase("E");
            if (addForm) {
                app.addDocument(new Document(DocumentType.DS160_FORM, "Form-X"));
                System.out.println("+ DS-160 Formu eklendi.");
            }
        } else {
            // Schengen için sigorta soralım
            boolean addInsurance = InputHelper.readString("Seyahat Sigortasını yükleyelim mi? (E/H)").equalsIgnoreCase("E");
            if (addInsurance) {
                app.addDocument(new Document(DocumentType.INSURANCE, "Allianz Sigorta"));
                System.out.println("+ Sigorta eklendi.");
            }
        }

        InputHelper.printSeparator();
        System.out.println("SONUÇ:");

        try {
            app.validateDocuments(); // Exception ihtimali olan yer
            System.out.println("✅ TEBRİKLER! Başvurunuz eksiksiz ve onaya hazır.");
        } catch (MissingDocumentException e) {
            System.err.println("❌ VİZE REDDİ: " + e.getMessage());
            System.out.println("   (İpucu: Belgeleri eksik yüklediğiniz için bu hatayı aldınız.)");
        }
    }
}