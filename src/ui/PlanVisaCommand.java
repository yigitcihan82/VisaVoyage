package ui;

import exception.MissingDocumentException;
import model.visa.Document;
import model.visa.DocumentType;
import model.visa.VisaApplication;
import service.VisaService;
import java.util.Scanner;

public class PlanVisaCommand implements Command {
    private VisaService visaService;
    private Scanner scanner;

    public PlanVisaCommand(VisaService visaService) {
        this.visaService = visaService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        System.out.print("Hangi ülke kodu? (US / FR): ");
        String country = scanner.next();

        VisaApplication app = visaService.createApplication(country, "Örnek Kullanıcı");

        if (app != null) {
            try {
                System.out.println("Vize başvurusu oluşturuldu. Evraklar kontrol ediliyor...");
                // Kasıtlı olarak evrak eklemiyoruz ki hatayı görelim (veya test için ekleyebilirsin)

                // app.addDocument(new Document(DocumentType.PASSPORT, "TR123")); // Bunu açarsan hata azalır

                app.validateDocuments(); // C Kişisinin yazdığı metod

                System.out.println("Tebrikler! Evraklar tam.");
            } catch (MissingDocumentException e) {
                System.err.println("VİZE HATASI: " + e.getMessage());
                System.out.println("Lütfen eksik evrakları tamamlayıp tekrar deneyin.");
            }
        }
    }
}