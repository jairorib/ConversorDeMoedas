package modelos;

public class Moeda {
  private String moedaOrigem;
  private String moedaDestino;

  public Moeda (String moedaOrigem, String moedaDestino) {
    this.moedaOrigem = moedaOrigem;
    this.moedaDestino = moedaDestino;
  }

  public String getMoedaOrigem() {
    return moedaOrigem;
  }

  public String getMoedaDestino() {
    return moedaDestino;
  }

  @Override
  public String toString () {
    return getMoedaOrigem() + " ==> " + getMoedaDestino();
  }
}
