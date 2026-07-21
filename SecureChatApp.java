import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.*;
import java.security.*;
import java.util.Base64;

public class SecureChatApp {

    // ---------- Encryption Utilities ----------
    public static class EncryptionUtils {
        public static SecretKey generateAESKey() throws Exception {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            return keyGen.generateKey();
        }

        public static String encryptAES(String message, SecretKey key) throws Exception {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes()));
        }

        public static String decryptAES(String encryptedMsg, SecretKey key) throws Exception {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedMsg)));
        }

        public static KeyPair generateRSAKeyPair() throws Exception {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            return keyGen.generateKeyPair();
        }

        public static String encryptRSA(SecretKey aesKey, PublicKey publicKey) throws Exception {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(aesKey.getEncoded()));
        }

        public static SecretKey decryptRSA(String encryptedKey, PrivateKey privateKey) throws Exception {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedKey));
            return new SecretKeySpec(decrypted, "AES");
        }
    }

    // ---------- Chat Server ----------
    public static class ChatServer {
        public static void startServer() throws Exception {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started on port 5000...");

            Socket client1 = serverSocket.accept();
            System.out.println("Client 1 connected.");
            Socket client2 = serverSocket.accept();
            System.out.println("Client 2 connected.");

            new Thread(() -> forwardMessages(client1, client2)).start();
            new Thread(() -> forwardMessages(client2, client1)).start();
        }

        private static void forwardMessages(Socket from, Socket to) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(from.getInputStream()));
                PrintWriter writer = new PrintWriter(to.getOutputStream(), true);
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // ---------- Chat Client ----------
    public static class ChatClient {
        public static void startClient() throws Exception {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to server.");

            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Generate RSA keys
            KeyPair rsaKeys = EncryptionUtils.generateRSAKeyPair();
            SecretKey aesKey = EncryptionUtils.generateAESKey();

            // Encrypt AES key with RSA (simulate exchange)
            String encryptedAESKey = EncryptionUtils.encryptRSA(aesKey, rsaKeys.getPublic());
            SecretKey decryptedAESKey = EncryptionUtils.decryptRSA(encryptedAESKey, rsaKeys.getPrivate());

            // Thread to listen for incoming messages
            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        String decrypted = EncryptionUtils.decryptAES(msg, decryptedAESKey);
                        System.out.println("Friend: " + decrypted);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            // Sending encrypted messages
            String input;
            while ((input = console.readLine()) != null) {
                String encrypted = EncryptionUtils.encryptAES(input, aesKey);
                out.println(encrypted);
            }
        }
    }

    // ---------- Main ----------
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Usage: java SecureChatApp server|client");
            return;
        }
        if (args[0].equalsIgnoreCase("server")) {
            ChatServer.startServer();
        } else if (args[0].equalsIgnoreCase("client")) {
            ChatClient.startClient();
        }
    }
}
