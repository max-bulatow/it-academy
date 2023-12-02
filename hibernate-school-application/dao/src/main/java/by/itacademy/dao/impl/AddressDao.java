package by.itacademy.dao.impl;

import by.itacademy.address.Address;
import by.itacademy.dao.DaoException;
import by.itacademy.dao.GenericDao;
import org.hibernate.SessionFactory;

public class AddressDao extends GenericDao<Address> {

    public AddressDao(final SessionFactory sessionFactory) {
        super(Address.class, DaoException.AddressDaoException::new, sessionFactory);
    }

}
