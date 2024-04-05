package com.swp.birthdaybooking.services;

import com.swp.birthdaybooking.Dtos.Request.HostRq;
import com.swp.birthdaybooking.entities.Host;
import com.swp.birthdaybooking.repositories.HostRepository;
import org.springframework.stereotype.Service;

@Service
public class HostService extends BaseService<Host,Integer>{

    private final HostRepository hostRepository;

    public HostService(HostRepository hostRepository) {
        super(hostRepository);
        this.hostRepository = hostRepository;
    }

    public Host editProfile(HostRq hostRq) {
        var host = getById(hostRq.getHostId());
        host.setPhone(hostRq.getPhone());
        host.setName(hostRq.getName());
        return hostRepository.save(host);
    }

}