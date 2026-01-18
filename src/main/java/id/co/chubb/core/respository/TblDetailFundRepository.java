package id.co.chubb.core.respository;

import id.co.chubb.core.model.entity.TblDetailFund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TblDetailFundRepository extends JpaRepository<TblDetailFund, Long>, QuerydslPredicateExecutor<TblDetailFund> {

}
