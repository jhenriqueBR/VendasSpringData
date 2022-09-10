package br.com.taocode.vendas.domain.repository;

import br.com.taocode.vendas.domain.entity.Cliente;

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
	private static final String SELECT = "SELECT ID, NOME FROM CLIENTES";

	public List<Cliente> listarTodos () {
		return this.jdbcTemplate.query(SELECT, new RowMapper<Cliente>() {
			@Override
			public Cliente mapRow(ResultSet resultado, int i) throws SQLException {
				return new Cliente(resultado.getInt("ID"), resultado.getString("NOME"));
			}
		});
	}

	public void salvar (Cliente cliente) {
		this.jdbcTemplate.update(INSERT, new Object[] { cliente.getNome() });
	}
}
