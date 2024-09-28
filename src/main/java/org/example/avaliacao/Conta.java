package org.example.avaliacao;

public class Conta {
    private Integer numero;
    private String titular;
    private Double saldo;

    public Conta(Integer numero, String titular) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = 0.0;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        if (saldo >= 0) {
            this.saldo = saldo;
        } else {
            throw new IllegalArgumentException("Saldo não pode ser negativo.");
        }
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public void depositar(Double valor) {
        if (valor > 0) {
            this.saldo += valor;
        } else {
            throw new IllegalArgumentException("Valor do depósito deve ser positivo.");
        }
    }

    public Boolean sacar(Double valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("Conta{numero=%d, titular='%s', saldo=%.2f}", numero, titular, saldo);
    }
}
