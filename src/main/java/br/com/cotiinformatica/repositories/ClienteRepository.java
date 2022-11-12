package br.com.cotiinformatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class ClienteRepository {

	public void create(Cliente cliente) throws Exception {

		Connection cn = ConnectionFactory.createConnection();

		PreparedStatement ps = cn.prepareStatement("insert into cliente(nome, cpf, email) values(?,?,?)");
		ps.setString(1, cliente.getNome());
		ps.setString(2, cliente.getCpf());
		ps.setString(3, cliente.getEmail());
		ps.execute();

		cn.close();
	}

	public void update(Cliente cliente) throws Exception {

		Connection cn = ConnectionFactory.createConnection();

		PreparedStatement ps = cn.prepareStatement("update cliente set nome=?, cpf=?, email=? where idcliente=?");
		ps.setString(1, cliente.getNome());
		ps.setString(2, cliente.getCpf());
		ps.setString(3, cliente.getEmail());
		ps.setInt(4, cliente.getIdCliente());
		ps.execute();

		cn.close();

	}

	public void delete(Integer idCliente) throws Exception {

		Connection cn = ConnectionFactory.createConnection();

		PreparedStatement ps = cn.prepareStatement("delete from cliente where idcliente=?");
		ps.setInt(1, idCliente);
		ps.execute();

		cn.close();
	}

	public List<Cliente> findAll() throws Exception {

		Connection cn = ConnectionFactory.createConnection();

		PreparedStatement ps = cn.prepareStatement("select * from cliente");
		ResultSet rs = ps.executeQuery();

		List<Cliente> lista = new ArrayList<Cliente>();

		while (rs.next()) {

			Cliente cliente = new Cliente();

			cliente.setIdCliente(rs.getInt("idcliente"));
			cliente.setNome(rs.getString("nome"));
			cliente.setEmail(rs.getString("email"));
			cliente.setCpf(rs.getString("cpf"));

			lista.add(cliente);

		}

		cn.close();
		return lista;

	}

	public Cliente findById(Integer idCliente) throws Exception {

		Connection cn = ConnectionFactory.createConnection();

		PreparedStatement ps = cn.prepareStatement("select * from cliente where idcliente=?");
		ps.setInt(1, idCliente);
		ResultSet rs = ps.executeQuery();

		Cliente cliente = null;

		if (rs.next()) {

			cliente = new Cliente();

			cliente.setIdCliente(rs.getInt("idcliente"));
			cliente.setNome(rs.getString("nome"));
			cliente.setEmail(rs.getString("email"));
			cliente.setCpf(rs.getString("cpf"));

		}

		cn.close();
		return cliente;

	}

}
