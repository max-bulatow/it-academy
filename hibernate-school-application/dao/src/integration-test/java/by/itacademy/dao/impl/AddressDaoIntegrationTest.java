package by.itacademy.dao.impl;

import by.itacademy.address.Address;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static by.itacademy.dao.impl.TestHibernateUtil.SESSION_FACTORY;

public class AddressDaoIntegrationTest extends BaseDaoIntegrationTest {

    protected static AddressDao ADDRESS_DAO;

    private static final Address ADDRESS = new Address();

    @BeforeAll
    public static void createDao() {
        SESSION_FACTORY.openSession();
        ADDRESS_DAO = new AddressDao(SESSION_FACTORY);
    }

    @BeforeAll
    public static void createObject() {
        ADDRESS.setId(1);
        ADDRESS.setCity("Minsk");
        ADDRESS.setStreet("Masherova");
        ADDRESS.setBuildingNumber("38");
    }

    @Test
    void testCreateAddress_happyPath() throws Exception {
        ADDRESS_DAO.create(ADDRESS);

        final ResultSetVerifier verifier = (rs) -> {
            Integer addressId = rs.getInt("id");
            String city = rs.getString("city");
            String street = rs.getString("street");
            String buildingNumber = rs.getString("building_number");

            Assertions.assertEquals(ADDRESS.getId(), addressId);
            Assertions.assertEquals(ADDRESS.getCity(), city);
            Assertions.assertEquals(ADDRESS.getStreet(), street);
            Assertions.assertEquals(ADDRESS.getBuildingNumber(), buildingNumber);
        };

        verifyCreatedRow("address", ADDRESS.getId(), verifier);

    }

    @Test
    void testReadAddress_happyPath() throws Exception {
        ADDRESS_DAO.create(ADDRESS);

        final Address readAddress = new Address();
        readAddress.setId(ADDRESS_DAO.read(1).getId());
        readAddress.setCity(ADDRESS_DAO.read(1).getCity());
        readAddress.setStreet(ADDRESS_DAO.read(1).getStreet());
        readAddress.setBuildingNumber(ADDRESS_DAO.read(1).getBuildingNumber());

        final ResultSetVerifier verifier = (rs) -> {
            Integer addressId = rs.getInt("id");
            String city = rs.getString("city");
            String street = rs.getString("street");
            String buildingNumber = rs.getString("building_number");

            Assertions.assertEquals(readAddress.getId(), addressId);
            Assertions.assertEquals(readAddress.getCity(), city);
            Assertions.assertEquals(readAddress.getStreet(), street);
            Assertions.assertEquals(readAddress.getBuildingNumber(), buildingNumber);
        };

        verifyCreatedRow("address", ADDRESS.getId(), verifier);
    }

    @Test
    void testUpdateAddress_happyPath() throws Exception {
        final Address updateAddress = new Address();
        updateAddress.setId(1);
        updateAddress.setCity("Minsk");
        updateAddress.setStreet("Sovetskaya");
        updateAddress.setBuildingNumber("48");

        ADDRESS_DAO.update(updateAddress);

        final ResultSetVerifier verifier = (rs) -> {
            Integer addressId = rs.getInt("id");
            String city = rs.getString("city");
            String street = rs.getString("street");
            String buildingNumber = rs.getString("building_number");

            Assertions.assertEquals(updateAddress.getId(), addressId);
            Assertions.assertEquals(updateAddress.getCity(), city);
            Assertions.assertEquals(updateAddress.getStreet(), street);
            Assertions.assertEquals(updateAddress.getBuildingNumber(), buildingNumber);
        };

        verifyCreatedRow("address", ADDRESS.getId(), verifier);
    }

    @Test
    void testDeleteAddress_happyPath() throws Exception {
        ADDRESS_DAO.delete(1);

        final ResultSetVerifier verifier = (rs) -> {
            Integer addressId = rs.getInt("id");

            Assertions.assertNull(addressId);
        };

    }
}