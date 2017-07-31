package com.beeva.banco.impl;
import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.banco.dao.CuentaDAO;
import com.beeva.banco.model.Cuenta;
@Repository
public class CuentaDAOImp extends CuentaDAO {
	@PersistenceContext
    EntityManager em;
    @Transactional
	@Override
	public void save(Cuenta cu) {
		em.persist(cu);
		
	}

	@Override
	public Cuenta getCuenta(int id) {
		Cuenta c = em.find(Cuenta.class, id);
		return c;
	}

	@Override
	@Transactional
	public boolean retiro(BigDecimal monto, int id) {
		boolean bandera= false;
		Query q = em.createQuery("update Cuenta c set c.balance = c.balance - :monto where c.idcliente= :id").setParameter("monto", monto).setParameter("id", id);
		int n = q.executeUpdate();
		if(n>0){
			bandera =true;
		}else{
			bandera = false;
		}
		return bandera;
	}

}
