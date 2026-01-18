package id.co.chubb.core.respository;

import id.co.chubb.core.model.entity.TblTokenStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TblTokenStoreRepository extends JpaRepository<TblTokenStore, Long>, QuerydslPredicateExecutor<TblTokenStore> {
    public TblTokenStore findBySessionName(String name);
}
