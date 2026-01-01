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
        System.out.println("       VISA APPLICATION SIMULATION");
        InputHelper.printSeparator();

        String country = InputHelper.readString("Country code for application (US / FR / DE)");
        String name = InputHelper.readString("Applicant name");

        VisaApplication app = visaService.createApplication(country, name);

        if (app == null) {
            return;
        }

        System.out.println("\n>> Application draft created: " + app.exportToText());
        System.out.println(">> Now we will check the documents.\n");

        boolean addPassport = InputHelper.readString("Upload passport document to the system? (Y/N)").equalsIgnoreCase("Y");
        if (addPassport) {
            app.addDocument(new Document(DocumentType.PASSPORT, "TR-U12345"));
            System.out.println("+ Passport added.");
        }

        if (country.equalsIgnoreCase("US")) {
            boolean addForm = InputHelper.readString("Upload DS-160 Form? (Y/N)").equalsIgnoreCase("Y");
            if (addForm) {
                app.addDocument(new Document(DocumentType.DS160_FORM, "Form-X"));
                System.out.println("+ DS-160 Form added.");
            }
        } else {
            boolean addInsurance = InputHelper.readString("Upload Travel Insurance? (Y/N)").equalsIgnoreCase("Y");
            if (addInsurance) {
                app.addDocument(new Document(DocumentType.INSURANCE, "Allianz Insurance"));
                System.out.println("+ Insurance added.");
            }
        }

        InputHelper.printSeparator();
        System.out.println("RESULT:");

        try {
            app.validateDocuments();
            System.out.println("✅ CONGRATULATIONS! Your application is complete and ready for approval.");
        } catch (MissingDocumentException e) {
            System.err.println("❌ VISA REJECTION: " + e.getMessage());
            System.out.println("   (Tip: You received this error because documents were missing.)");
        }
    }
}