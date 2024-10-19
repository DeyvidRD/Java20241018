package org.example.avaliacao.model;

public class Especial extends Conta {
    private Double limite;

    public Especial(Integer numero, String titular, Double limite) {
        super(numero, titular);
        this.limite = limite;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        if (limite >= 0) {
            this.limite = limite;
        } else {
            throw new IllegalArgumentException("Limite não pode ser negativo.");
        }
    }

    @Override
    public Boolean sacar(Double valor) {
        // Permite saque até o saldo + limite
        if (valor > 0 && valor <= getSaldo() + limite) {
            setSaldo(getSaldo() - valor);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("Especial{numero=%d, titular='%s', limite=%.2f, saldo=%.2f}",
                getNumero(), getTitular(), limite, getSaldo());
    }
}
