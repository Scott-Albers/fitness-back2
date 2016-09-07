package com.chyld.repositories;


import com.chyld.entities.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IDeviceRepository extends PagingAndSortingRepository<Role, Integer> {
}
