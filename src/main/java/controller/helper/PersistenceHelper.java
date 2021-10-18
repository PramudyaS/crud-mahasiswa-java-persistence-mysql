package controller.helper;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceHelper {
    private static final EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("Tm6MhsaPU");
    }

    public static EntityManagerFactory getFactory() {
        return factory;
    }
}
