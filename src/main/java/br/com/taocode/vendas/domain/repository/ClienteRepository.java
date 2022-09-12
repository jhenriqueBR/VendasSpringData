package br.com.taocode.vendas.domain.repository;

import br.com.taocode.vendas.domain.entity.Cliente;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClienteRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String INSERT = "INSERT INTO CLIENTES (NOME) VALUES (?)";
	private static final String DELETE = "DELETE FROM CLIENTES WHERE ID=?";
	private static final String SELECT = "SELECT ID, NOME FROM CLIENTES";
	private static final String UPDATE = "UPDATE CLIENTES SET NOME=? WHERE ID=?";

	public void apagar (Cliente cliente) {
		this.apagar(cliente.getId());
	}

	public void apagar (Integer id) {
		this.jdbcTemplate.update(DELETE, new Object[] { id });
	}

	public void atualizar (Cliente cliente) {
		this.jdbcTemplate.update(UPDATE, new Object[] { cliente.getNome(), cliente.getId() });
	}

	public Cliente ler (Integer id) {
		var clienteBanco = this.jdbcTemplate.query(SELECT, obterClienteMapper());

		return new Cliente(clienteBanco.get(0).getId(), clienteBanco.get(0).getNome());
	}


	public List<Cliente> listarTodos () {
		return this.jdbcTemplate.query(SELECT, obterClienteMapper());
	}

	@NotNull
	private RowMapper<Cliente> obterClienteMapper() {
		return new RowMapper<Cliente>() {
			@Override
			public Cliente mapRow(ResultSet resultado, int i) throws SQLException {
				return new Cliente(resultado.getInt("ID"), resultado.getString("NOME"));
			}
		};
	}

	public void salvar (Cliente cliente) {
		this.jdbcTemplate.update(INSERT, new Object[] { cliente.getNome() });
	}
}
