package by.itacademy.dao.impl;

import by.itacademy.address.Address;
import by.itacademy.dao.GenericDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AddressDao extends GenericDao<Address> {

    private final SessionFactory sessionFactory;

    public AddressDao(SessionFactory sessionFactory) {
        super(Address.class);
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected Session getSession() {
        return sessionFactory.openSession();
    }
}
