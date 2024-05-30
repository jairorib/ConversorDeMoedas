package excecao;

public class ErroDeDisponibilidadeException extends RuntimeException {
  private String mensagem;

  public ErroDeDisponibilidadeException(String mensagem) {
     this.mensagem = mensagem;
  }

  @Override
  public String getMessage() {
     return this.mensagem;
  }

}
