/*
 * Developed by Jan Misiorny
 * February A.D. 2019
 */

package com.decisionTree.wiki.dao;

import com.decisionTree.wiki.domain.CustomerDataDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDataRepository extends JpaRepository<CustomerDataDomain, Integer> {

    CustomerDataDomain findByIDCustomerData (int id);
   CustomerDataDomain findByIp (String ip);




}
