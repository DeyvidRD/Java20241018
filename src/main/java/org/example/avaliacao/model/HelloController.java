package org.example.avaliacao.model;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class HelloController {

  private final List<Conta> contas = new ArrayList<>();

  @FXML
  private TextField txtConta;

  @FXML
  private TextField txtTitular;

  @FXML
  private RadioButton rbCorrente;

  @FXML
  private RadioButton rbEspecial;

  @FXML
  private RadioButton rbPoupanca;

  @FXML
  private TextField txtLimite;

  @FXML
  private TextField txtVencimento;

  @FXML
  private Button btnRegistrar;

  @FXML
  private Button btnDepositar;

  @FXML
  private TextField txtValor;

  @FXML
  private Button btnSacar;

  @FXML
  private TextArea txtAreaDados;

  @FXML
  private TextField txtPesquisar;

  @FXML
  private Button btnPesquisar;

  @FXML
  private Label lblSaldo;

  private Conta contaAtual;

  @FXML
  protected void onSelecionarTipo() {
    boolean isEspecial = rbEspecial.isSelected();
    txtLimite.setDisable(!isEspecial);
    txtVencimento.setDisable(rbCorrente.isSelected());
  }

  @FXML
  protected void onCadastrarConta() {
    try {
      Integer numero = Integer.parseInt(txtConta.getText());
      String titular = txtTitular.getText();
      Conta novaConta;

      if (rbCorrente.isSelected()) {
        novaConta = new Conta(numero, titular);
      } else if (rbEspecial.isSelected()) {
        Double limite = Double.parseDouble(txtLimite.getText());
        novaConta = new Especial(numero, titular, limite);
      } else {
        Integer vencimento = Integer.parseInt(txtVencimento.getText());
        novaConta = new Poupanca(numero, titular, vencimento);
      }

      contas.add(novaConta);
      atualizarAreaDados();
      limparCamposCadastro();
    } catch (NumberFormatException e) {
      alert("Dados inválidos. Verifique os campos numéricos.");
    }
  }

  @FXML
  protected void onDepositar() {
    realizarOperacao("deposito");
  }

  @FXML
  protected void onSacar() {
    realizarOperacao("saque");
  }

  private void realizarOperacao(String tipo) {
    if (contaAtual != null) {
      try {
        double valor = Double.parseDouble(txtValor.getText());
        if (tipo.equals("deposito")) {
          contaAtual.depositar(valor);
        } else {
          if (!contaAtual.sacar(valor)) {
            alert("Saldo insuficiente!");
            return;
          }
        }
        atualizarAreaDados();
        lblSaldo.setText(String.format("%.2f", contaAtual.getSaldo()));
        limparCamposOperacao();
      } catch (NumberFormatException e) {
        alert("Valor inválido.");
      }
    } else {
      alert("Selecione uma conta primeiro!");
    }
  }

  @FXML
  protected void onPesquisar() {
    try {
      int numeroConta = Integer.parseInt(txtPesquisar.getText());
      for (Conta conta : contas) {
        if (conta.getNumero().equals(numeroConta)) {
          contaAtual = conta;
          lblSaldo.setText(String.format("%.2f", conta.getSaldo()));
          txtAreaDados.setText(conta.toString());
          return;
        }
      }
      alert("Conta não encontrada!");
    } catch (NumberFormatException e) {
      alert("Número da conta inválido.");
    }
  }

  private void atualizarAreaDados() {
    StringBuilder dados = new StringBuilder();
    for (Conta conta : contas) {
      dados.append(conta.toString()).append("\n");
    }
    txtAreaDados.setText(dados.toString());
  }

  private void limparCamposCadastro() {
    txtConta.clear();
    txtTitular.clear();
    txtLimite.clear();
    txtVencimento.clear();
    rbCorrente.setSelected(false);
    rbEspecial.setSelected(false);
    rbPoupanca.setSelected(false);
    lblSaldo.setText("0.00");
    txtConta.requestFocus();
  }

  private void limparCamposOperacao() {
    txtValor.clear();
    txtValor.requestFocus();
  }

  private void alert(String message) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Aviso");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
}
