package modelos;

import excecao.ErroDeDisponibilidadeException;
import java.io.IOException;
import java.net.URI;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class Conversor {

  private static final String api_key = ""; // edite aqui com sua chave da API.

  private static final String url = "https://v6.exchangerate-api.com/v6/" + api_key + "/latest/USD";

  public static Map<String, Double> obterCotacao() throws IOException, InterruptedException {

            // Construindo o Cliente para solicitações (HttpClient)
            HttpClient client = HttpClient.newHttpClient();

            // Construindo a Requisição (HttpRequest)
            HttpRequest request = HttpRequest.newBuilder()
             .uri(URI.create(url))
             .build();

            // Construindo a Resposta (HttpResponse)
            HttpResponse<String> response = client
             .send(request, HttpResponse.BodyHandlers.ofString());


            String json = response.body();

            /* Com a biblioteca Gson, realizamos o mapeamento eficiente dos dados JSON para objetos Java,
               facilitando assim a extração e manipulação das informações necessárias. */
            Gson gson = new Gson();

            /* utilizamos a classe JsonObject fornecida pelo Gson,
               que representa um tipo de objeto em JSON. */
            JsonObject conversionRates  = gson.fromJson(json, JsonObject.class);

            JsonObject jsonObject = conversionRates.getAsJsonObject("conversion_rates");

            return gson.fromJson(jsonObject, Map.class);
  }

  public static Double paresMoedas(double valor, Moeda moeda) throws IOException, InterruptedException {
            Map<String, Double> conversaoDeCotacao = obterCotacao();

            if (!conversaoDeCotacao.containsKey(moeda.getMoedaOrigem()) || !conversaoDeCotacao.containsKey(moeda.getMoedaDestino())) {
                throw new ErroDeDisponibilidadeException("Currency Code não encontrado!");
            }

            // Agora que já temos os valores das taxas de câmbio em mãos, realizaremos as conversões entre as moedas.
            double taxaMoedaOrigem = conversaoDeCotacao.get(moeda.getMoedaOrigem());

            double taxaMoedaDestino = conversaoDeCotacao.get(moeda.getMoedaDestino());

            /* Na lógica de conversão utilizamos as taxas obtidas para calcular
               os valores convertidos entre as moedas desejadas. */
            double valorConvertido = valor * taxaMoedaDestino / taxaMoedaOrigem;

            // exibimos os resultados finais.
            return valorConvertido;
  }
}
