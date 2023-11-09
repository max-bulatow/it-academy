package by.itacademy.dao.impl;

import by.itacademy.dao.GenericDao;
import by.itacademy.grouproom.GroupRoom;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class GroupRoomDao extends GenericDao<GroupRoom> {

    private final SessionFactory sessionFactory;

    public GroupRoomDao(SessionFactory sessionFactory) {
        super(GroupRoom.class);
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected Session getSession() {
        return sessionFactory.openSession();
    }
}
