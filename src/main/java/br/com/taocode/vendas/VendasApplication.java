package br.com.taocode.vendas;


import br.com.taocode.vendas.domain.entity.Cliente;
import br.com.taocode.vendas.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

	@Bean
	public CommandLineRunner init(@Autowired ClienteRepository clienteRepository) {
		return args -> {
			Cliente cliente = new Cliente();
			cliente.setNome("J.Henrique");
			clienteRepository.salvar(cliente);
			clienteRepository.salvar(new Cliente("Iwana"));

			List<Cliente> clientes = clienteRepository.listarTodos();

			System.out.println("");
			clientes.forEach(c -> {
				System.out.printf("Cliente { id=%d, nome='%s' } %n", c.getId(), c.getNome());
			});

			// Outra forma de listar tudo, mas tem que ter o método toString na classe cliente
			clientes.forEach(System.out::println);

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}
}
