import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

   
    public static void main(String[] args) throws Exception {
       
        // fazer uma conexão htpp e buscar top 250 filmes
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI endereço = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereço).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // pegar só os dados que interessam (título, postes, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        

        // exibir e manipular os dados 
        for (Map<String,String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";

            var gerador = new GeradorDeStickers();
            gerador.criar(inputStream, nomeArquivo);
          
             System.out.println("\u001b[43m Filme...: " + "\u001b[m" + "\u001b[33m"+ filme.get("title") + "\u001b[m");
             System.out.println("Poster...:" + filme.get("image"));
             System.out.println("\u001b[41m Nota...: " + "\u001b[m" + "\u001b[31m"+filme.get("imDbRating")+ "\u001b[m" );
             System.out.println("\u001b[35m>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> \u001b[m");
        }
    }
}


