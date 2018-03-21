package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
/*import org.springframework.data.jpa.repository.Query;
  import org.springframework.data.repository.query.Param;*/

@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String> {
    //@Query("SELECT * from Compensation where employee.employeeId = :id")
    Compensation findCompensationByEmployeeEmployeeId(/*@Param("id")*/ String employeeId);
}
