package com.castroantonio.reservas.acceptance.steps;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;

import com.castroantonio.reservas.model.Cliente;
import com.castroantonio.reservas.model.Quarto;
import com.castroantonio.reservas.model.Reserva;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class RevervandoSteps {

	private Cliente cliente;
	private Quarto quarto;
	private Reserva reserva;

	private final String NUMERO_QUARTO = "007";

	@Dado("um quarto")
	public void um_quarto() {
		quarto = new Quarto(NUMERO_QUARTO);
	}

	@Quando("o cliente {string} faz uma reserva valida")
	public void o_cliente_faz_uma_reserva_valida(String nomeCliente) {
		cliente = new Cliente(nomeCliente);
		Calendar calendario = Calendar.getInstance();
		Date inicio = calendario.getTime();
		calendario.add(Calendar.MONTH, 1);

		Date fim = calendario.getTime();

		reserva = new Reserva(cliente, quarto, inicio, fim);
	}

	@Entao("a reserva e aceita no nome dele")
	public void a_reserva_e_aceita_no_nome_dele() {
		Assert.assertEquals(cliente.getNome(), reserva.getCliente().getNome());
	}

}