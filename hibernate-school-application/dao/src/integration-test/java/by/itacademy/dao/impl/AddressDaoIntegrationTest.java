package by.itacademy.dao.impl;

import by.itacademy.address.Address;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import static by.itacademy.dao.impl.TestHibernateUtil.SESSION_FACTORY;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddressDaoIntegrationTest extends BaseDaoIntegrationTest {

    private Session session;

    protected static AddressDao ADDRESS_DAO;

    protected static final Address ADDRESS = new Address();

    @BeforeAll
    public static void createDao() {
        ADDRESS_DAO = new AddressDao(SESSION_FACTORY);
    }

    @BeforeAll
    public static void createObject() {
        ADDRESS.setId(1);
        ADDRESS.setCity("Minsk");
        ADDRESS.setStreet("Masherova");
        ADDRESS.setBuildingNumber("38");
    }

    @BeforeEach
    public void openSession() {
        session = SESSION_FACTORY.openSession();
    }

    @AfterEach
    public void closeSession() {
        if (session != null) {
            session.close();
        }
    }

    @Test
    @Order(1)
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
    @Order(2)
    void testReadAddress_happyPath() throws Exception {
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
    @Order(3)
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

        verifyCreatedRow("address", updateAddress.getId(), verifier);
    }

    @Test
    @Order(4)
    void testDeleteAddress_happyPath() throws Exception {
        ADDRESS_DAO.delete(1);

        final ResultSetVerifier verifier = (rs) -> {
            Integer addressId = rs.getInt("id");

            Assertions.assertNull(addressId);
        };
    }
}