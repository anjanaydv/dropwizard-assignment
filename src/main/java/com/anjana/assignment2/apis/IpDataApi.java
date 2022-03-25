package com.anjana.assignment2.apis;

import com.anjana.assignment2.cache.CacheConfigManager;
import com.anjana.assignment2.dao.IpDAO;
import com.anjana.assignment2.service.IPService;
import com.anjana.assignment2.validation.ValidIP;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.validation.Validated;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @Copyright Any portion of this assignment's code are not allowed to use in business or production.
 *
 * This controller class is used to create rest api. The api like http(s)://host/ip-details/ip=1.2.3.4 is used to get ip info.
 *
 * @author Anjana Yadav
 */
@Path("/ip-details/")
public class IpDataApi {

    private IpDAO ipDAO;
    private IPService ipService;

    public IpDataApi(IpDAO ipDAO, IPService ipService) {
        this.ipDAO = ipDAO;
        this.ipService = ipService;
    }

    /**
     * Calls cache, if missed, calls service to get from db if that missed then finally calls api
     *
     * @param ip
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{ip}")
    @Timed
    @UnitOfWork
    public Object get(@Validated @PathParam("ip") @ValidIP String ip) {
        return CacheConfigManager.getInstance().getIpDataFromCache(ip);
    }
}
