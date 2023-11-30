package by.itacademy.dao.impl;

import by.itacademy.address.Address;
import by.itacademy.dao.DaoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static by.itacademy.dao.impl.BaseDaoIntegrationTest.SESSION_FACTORY;

public class AddressDaoIntegrationTest {

    protected static final AddressDao ADDRESS_DAO = new AddressDao(SESSION_FACTORY);

    private static final Address ADDRESS = new Address();

    @BeforeAll
    public static void createObject() {
        ADDRESS.setCity("Minsk");
        ADDRESS.setStreet("Masherova");
        ADDRESS.setBuildingNumber("38");
    }

    @Test
    void testCreateAddress_happyPath() throws DaoException {
        ADDRESS_DAO.create(ADDRESS);

        Assertions.assertEquals("Minsk", ADDRESS_DAO.read(3).getCity());
        Assertions.assertEquals("Masherova", ADDRESS_DAO.read(3).getStreet());
        Assertions.assertEquals("38", ADDRESS_DAO.read(3).getBuildingNumber());

    }

    @Test
    void testReadAddress_happyPath() throws DaoException {
        Assertions.assertEquals("Brest", ADDRESS_DAO.read(1).getCity());
        Assertions.assertEquals("Dzerjinskogo", ADDRESS_DAO.read(1).getStreet());
        Assertions.assertEquals("50", ADDRESS_DAO.read(1).getBuildingNumber());
    }

    @Test
    void testUpdateAddress_happyPath() throws DaoException {
        ADDRESS_DAO.create(ADDRESS);

        final Address addressUpdate = new Address();
        addressUpdate.setId(3);
        addressUpdate.setCity("Minsk");
        addressUpdate.setStreet("Masherova");
        addressUpdate.setBuildingNumber("36");

        ADDRESS_DAO.update(addressUpdate);

        Assertions.assertEquals("Minsk", ADDRESS_DAO.read(3).getCity());
        Assertions.assertEquals("Masherova", ADDRESS_DAO.read(3).getStreet());
        Assertions.assertEquals("36", ADDRESS_DAO.read(3).getBuildingNumber());
    }

    @Test
    void testDeleteAddress_happyPath() throws DaoException {
        ADDRESS_DAO.create(ADDRESS);

        ADDRESS_DAO.delete(3);

        Assertions.assertNull(ADDRESS_DAO.read(3));
    }

}
