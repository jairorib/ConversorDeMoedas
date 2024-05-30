package modelos;

import modelos.Conversor;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Conversao {
  private double valor;
  private double valorConvertido;
  private List<Moeda> moedas;

  public Conversao (double valor) {
    this.valor = valor;
    this.moedas = new ArrayList<>();
  }

  public boolean pegaMoeda (Moeda moeda) {
    moedas.add(0, moeda);
    try {
      this.valorConvertido = new Conversor().paresMoedas(getValor(), moeda);
      return true;
    } catch (IOException | InterruptedException e) {
      System.err.println("Erro ao converter moeda: " + e.getMessage());
      return false;
    }
  }

  public Double getValor () {
    return valor;
  }

  public Double getValorConvertido () {
    return valorConvertido;
  }

  public List<Moeda> getMoedas () {
    return moedas;
  }
}
