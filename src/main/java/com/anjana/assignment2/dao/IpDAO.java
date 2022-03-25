package com.anjana.assignment2.dao;

import com.anjana.assignment2.model.IpData;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * @author Anjana Yadav
 * @Copyright Any portion of this assignment's code are not allowed to use in business or production.
 * <p>
 * IpDAO class used to query and store ip data
 */

public class IpDAO extends AbstractDAO<IpData> {

    SessionFactory factory;

    public IpDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.factory = sessionFactory;
    }

    /**
     * Save ip data
     *
     * @param ipData
     * @return
     */
    public IpData create(IpData ipData) {
        return persist(ipData);
    }

    /**
     * Query based on ip address
     *
     * @param ip
     * @return
     */
    @SuppressWarnings("unchecked")
    public IpData findIpData(String ip) {
        List<IpData> ipData = factory.getCurrentSession().createQuery("FROM IpData WHERE query = :ip")
                .setParameter("ip", ip).getResultList();
        return ipData.size() > 0 ? ipData.get(0) : null;
    }
}
