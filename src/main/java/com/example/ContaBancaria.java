package com.example;

public class ContaBancaria {

    private double saldo;
    private boolean bancoAtivo;

    public ContaBancaria() {
        this.saldo = 0;
        this.bancoAtivo = false;
        System.out.println("ContaBancaria criada: saldo inicial 0, banco inativo.");
    }

    public void ativarBanco() {
        this.bancoAtivo = true;
        System.out.println("Banco ativado.");
    }

    public void desativarBanco() {
        this.bancoAtivo = false;
        System.out.println("Banco desativado.");
    }

    public boolean isBancoAtivo() {
        return bancoAtivo;
    }

    public void depositar(double valor) {
        if (bancoAtivo) {
            saldo += valor;
            System.out.println("Depósito de " + valor + " realizado. Saldo atual: " + saldo);
        } else {
            System.out.println("Tentativa de depósito com banco inativo. Depósito não realizado.");
        }
    }

    public boolean sacar(double valor) {
        if (bancoAtivo && saldo >= valor) {
            saldo -= valor;
            System.out.println("Saque de " + valor + " realizado. Saldo atual: " + saldo);
            return true;
        } else {
            System.out.println("Tentativa de saque de " + valor + " falhou. Saldo atual: " + saldo);
            return false;
        }
    }

    public double consultarSaldo() {
        System.out.println("Consulta de saldo: " + saldo);
        return saldo;
    }
}
