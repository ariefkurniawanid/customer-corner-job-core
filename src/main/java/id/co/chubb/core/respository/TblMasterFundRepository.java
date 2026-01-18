package id.co.chubb.core.respository;

import id.co.chubb.core.model.entity.TblMasterFund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TblMasterFundRepository extends JpaRepository<TblMasterFund, Long>, QuerydslPredicateExecutor<TblMasterFund> {
    List<TblMasterFund> findByCodeIn(Set<String> codes);
}
