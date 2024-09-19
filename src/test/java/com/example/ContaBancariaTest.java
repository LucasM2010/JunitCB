package com.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

class ContaBancariaTest {

    static ContaBancaria contaBancaria;

    @BeforeAll
    static void setup() {
        contaBancaria = new ContaBancaria();
        contaBancaria.ativarBanco();
        contaBancaria.depositar(100);
        System.out.println("Setup concluído: Banco ativado e saldo inicial de 100 depositado.");
    }

    @AfterAll
    static void teardown() {
        System.out.println("Todos os testes foram executados.");
    }

    @Test
    void testDepositoBancoAtivo() {
        System.out.println("Iniciando testDepositoBancoAtivo");
        assumeTrue(contaBancaria.isBancoAtivo(), "Banco deve estar ativo");
        contaBancaria.depositar(100);
        double saldo = contaBancaria.consultarSaldo();
        System.out.println("Saldo após depósito: " + saldo);
        assertEquals(200, saldo, "O saldo deveria ser 200 após o depósito.");
    }

    @Test
    void testSaqueBancoAtivoSaldoSuficiente() {
        System.out.println("Iniciando testSaqueBancoAtivoSaldoSuficiente");
        assumeTrue(contaBancaria.isBancoAtivo(), "Banco deve estar ativo");
        boolean sucessoSaque = contaBancaria.sacar(100);
        System.out.println("Sucesso do saque: " + sucessoSaque);
        double saldo = contaBancaria.consultarSaldo();
        System.out.println("Saldo após saque: " + saldo);
        assertTrue(sucessoSaque, "O saque deveria ser permitido.");
        assertEquals(100, saldo, "O saldo deveria ser 100 após o saque.");
    }

    @Test
    void testSaqueBancoAtivoSaldoInsuficiente() {
        System.out.println("Iniciando testSaqueBancoAtivoSaldoInsuficiente");
        assumeTrue(contaBancaria.isBancoAtivo(), "Banco deve estar ativo");
        boolean sucessoSaque = contaBancaria.sacar(500);
        System.out.println("Sucesso do saque: " + sucessoSaque);
        double saldo = contaBancaria.consultarSaldo();
        System.out.println("Saldo após tentativa de saque: " + saldo);
        assertFalse(sucessoSaque, "O saque não deveria ser permitido com saldo insuficiente.");
        assertEquals(100, saldo, "O saldo deveria continuar 100.");
    }

    @Test
    void testOperacoesBancoInativo() {
        System.out.println("Iniciando testOperacoesBancoInativo");
        contaBancaria.desativarBanco();
        assumeFalse(contaBancaria.isBancoAtivo(), "Banco deve estar inativo");

        contaBancaria.depositar(50);
        double saldo = contaBancaria.consultarSaldo();
        System.out.println("Saldo após tentativa de depósito com banco inativo: " + saldo);
        assertEquals(100, saldo, "O saldo não deveria mudar com o banco inativo.");

        boolean sucessoSaque = contaBancaria.sacar(50);
        System.out.println("Sucesso do saque com banco inativo: " + sucessoSaque);
        assertFalse(sucessoSaque, "O saque não deveria ser permitido com o banco inativo.");

        saldo = contaBancaria.consultarSaldo();
        System.out.println("Saldo final com banco inativo: " + saldo);
        assertEquals(100, saldo, "O saldo não deveria mudar com o banco inativo.");
    }
}
