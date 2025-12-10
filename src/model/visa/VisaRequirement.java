package model.visa;

public class VisaRequirement implements Requirement {
    private DocumentType requiredType;

    public VisaRequirement(DocumentType requiredType) {
        this.requiredType = requiredType;
    }

    @Override
    public boolean isSatisfiedBy(VisaApplication app) {
        // Başvurunun içindeki dokümanları gez, istenen tip var mı bak
        for (Document doc : app.getDocuments()) {
            if (doc.getType() == requiredType) {
                return true;
            }
        }
        return false;
    }
}