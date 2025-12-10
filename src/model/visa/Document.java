package model.visa;

public class Document {
    private DocumentType type;
    private String contentOrPath; // Dosya yolu veya içeriği

    public Document(DocumentType type, String contentOrPath) {
        this.type = type;
        this.contentOrPath = contentOrPath;
    }

    public DocumentType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + ": " + contentOrPath;
    }
}