import java.io.OutputStreamWriter; // Veri yazmak için kullanılır
import java.io.PrintWriter; // Çıktı işlemleri için
import java.net.ServerSocket; // Sunucu oluşturmak için
import java.net.Socket;  // İstemciyle bağlantı kurmak için
import java.io.IOException; // IOException'u ekliyoruz

class MiniWebSunucu {
    public static void main(String[] args) throws IOException { // IOException ekleniyor
        // Sunucunun dinleyeceği port numarası yazılır
        int port = 1989;

        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        PrintWriter out = null;

        // Sunucuyu başlat
        serverSocket = new ServerSocket(port);
        System.out.println("Sunucu " + port + " portundan dinleniyor...");

        while (true) {
            clientSocket = serverSocket.accept();
            System.out.println("Yeni bir bağlantı: " + clientSocket.getInetAddress());

            // İstemciden gelen verileri yazmak için PrintWriter oluşturuyoruz.
            // OutputStreamWriter, istemcinin soketinden gelen akışı yazmamıza olanak tanır.
            out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            // HTTP yanıtı ve içeriği gönderiyoruz
            // Başarılı bir yanıt gönderdiğimizi belirtiriz.
            out.println("HTTP/1.1 200 OK");
            // İçeriğin türünü belirtiriz; burada HTML formatında olduğunu belirtiyoruz.
            out.println("Content-Type: text/html; charset=UTF-8");
            // Boş bir satır, HTTP başlıklarının bittiğini belirtir.
            out.println();

            // HTML içeriğini başlatıyoruz.
            out.println("<html>");
            // başlık bölümünü oluşturuyoruz.
            out.println("<head><title>Basit Web Sunucusu</title></head>");
            // sayfanın gövde bölümünü başlatıyoruz ve bazı stil ayarları ekliyoruz.
            out.println("<body style='font-family: Arial, sans-serif; background-color: #f4f4f9;'>");
            // İsim Soyisim
            out.println("<h1 style='color:  rgb(13, 113, 80);'>Emel CANSU</h1>");
            out.println("<h2 style='color:  rgb(13, 113, 80)'>Öğrenci Numarası: 1230505018</h2>");
            // Biyografi
            out.println("<h3 style='color:red;'><u>Biyografi</u></h3>");
            out.println("<p style='font-size: 20px;  color: black;font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;'>");
            out.println("<em>Temel Reis, Elzie Crisler Segar tarafından yaratılan ve ıspanak yiyerek güçlenen ünlü bir çizgi roman karakteridir. " +
                    "İlk kez 17 Ocak 1929’da yayımlanan Thimble Theater'da tanıtılmıştır. 1933’te Betty Boop ile sinemaya adım atan Temel Reis, dönemin en sevilen karakterlerinden biri olmuştur. " +
                    "Sevgilisi Olive Oyl (Türkçesi Safinaz) ve düşmanı Bluto (Kabasakal) ile birçok macera yaşamıştır. 1942’den itibaren Famous Studios tarafından canlandırılarak sinemaya taşınmıştır. " +
                    "Bugüne kadar çeşitli animasyon ve müzikal filmlerde yer alarak popülerliğini sürdürmüştür. </em>");
            out.println("</p>");
            out.println("</body>");
            out.println("</html>");

            //: Tüm verilerin istemciye gönderilmesini sağlar.
            out.flush();
            //Çıktı akışını ve istemci bağlantısını kapatır.
            out.close();
            clientSocket.close();
        }
    }
}
