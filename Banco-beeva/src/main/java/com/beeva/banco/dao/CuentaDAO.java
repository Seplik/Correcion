package com.beeva.banco.dao;

import java.math.BigDecimal;

import com.beeva.banco.model.Cuenta;

public abstract class CuentaDAO {
	public abstract void  save(Cuenta cu);
	public abstract Cuenta getCuenta(int id);
	public abstract boolean retiro(BigDecimal monto, int id);

}
