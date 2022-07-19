import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

   
    public static void main(String[] args) throws Exception {
       
        // fazer uma conexão htpp 
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI endereço = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereço).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // pegar dados de interesse (título, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        

        // exibir dados e manipular 
        for (Map<String,String> filme : listaDeFilmes) {
          
             System.out.println("\u001b[43m Filme...: " + "\u001b[m" + "\u001b[33m"+ filme.get("title") + "\u001b[m");
             System.out.println("\u001b[43m Poster...:" + "\u001b[m" + filme.get("image")+ "\u001b[m");
             System.out.println("\u001b[41m Nota...: " + "\u001b[m" + "\u001b[31m"+filme.get("imDbRating")+ "\u001b[m" );
             System.out.println("\u001b[35m>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> \u001b[m");
        }
    }
}
