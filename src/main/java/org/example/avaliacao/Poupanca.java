package org.example.avaliacao;

public class Poupanca extends Conta {
    private Integer aniversario;

    public Poupanca(Integer numero, String titular, Integer aniversario) {
        super(numero, titular);
        this.aniversario = aniversario;
    }

    public Integer getAniversario() {
        return aniversario;
    }

    public void setAniversario(Integer aniversario) {
        if (aniversario < 1 || aniversario > 31) {
            throw new IllegalArgumentException("O aniversário deve ser um valor entre 1 e 31.");
        }
        this.aniversario = aniversario;
    }

    @Override
    public String toString() {
        return String.format("Poupança{numero=%d, titular='%s', aniversario=%d, saldo=%.2f}",
                getNumero(), getTitular(), aniversario, getSaldo());
    }
}
