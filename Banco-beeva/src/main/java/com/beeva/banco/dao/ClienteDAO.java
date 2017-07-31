package com.beeva.banco.dao;

import java.math.BigDecimal;
import java.util.List;

import com.beeva.banco.model.Cliente;
import com.beeva.banco.model.Cuenta;
import com.beeva.banco.model.TipoCuenta;

public abstract class ClienteDAO {
	public abstract void save(Cliente c);
	public abstract Cliente getCliente(int id);
	public abstract Cuenta getCuenta(int idcliente);
	public abstract boolean deposito(BigDecimal monto , int id);
	public abstract List<Cliente> getClientes();
	public abstract TipoCuenta getTipoCuenta(int idC);

}
