package model.visa;

public interface Requirement {
    // Vize başvurusu bu kuralı karşılıyor mu?
    boolean isSatisfiedBy(VisaApplication app);
}