package com.beeva.banco.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.beeva.banco.dao.ClienteDAO;
import com.beeva.banco.model.Cliente;
import com.beeva.banco.model.Cuenta;
import com.beeva.banco.model.TipoCuenta;
@Repository

public class ClienteDAOImp extends ClienteDAO{
	@PersistenceContext
	EntityManager em;
	@Override
	@Transactional
	public void save(Cliente c) {
		em.persist(c);
		
	}

	@Override
	public Cliente getCliente(int id) { 
		Cliente c = em.find(Cliente.class, id);
		return c;
	}
	@Override
	public Cuenta getCuenta(int idcliente) {
		
		Cuenta cu = (Cuenta) em.createQuery("SELECT c FROM Cuenta c WHERE c.idcliente = :id").setParameter("id",idcliente).getSingleResult();
		return cu;
	}

	@Override
	@Transactional
	public  boolean deposito(BigDecimal monto, int id) {
	boolean bandera = false;
	
	Query q = em.createQuery("update Cuenta c set c.balance = :monto + c.balance where c.idcliente = :id").setParameter("monto", monto).setParameter("id", id);
	int n = q.executeUpdate();
	if(n>0 ){
		bandera = true;
	}else{
		bandera = false;
	}
		return bandera;
	}

	@Override
	public List<Cliente> getClientes() {
	       Query q = em.createQuery("select c from Cliente c");
	       List<Cliente> clientes = (List) q.getResultList();
		return clientes;
	}

	@Override
	public TipoCuenta getTipoCuenta(int idC) {
		TipoCuenta tp = (TipoCuenta)em.createQuery("select ct from TipoCuenta ct, Cuenta c where ct.idtipo_cuenta = c.idtipo_cuenta and(c.idcliente= :idc)").setParameter("idc", idC).getSingleResult();
		
		return tp;
	}

	
}
