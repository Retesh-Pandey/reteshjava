import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.zip.*;

// ------------------ LRU Cache ------------------
class LRUCache<K, V> {
    private final int capacity;
    private final LinkedHashMap<K, V> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new LinkedHashMap<>(capacity, 0.75f, true);
    }

    public V get(K key) {
        return map.getOrDefault(key, null);
    }

    public void put(K key, V value) {
        if (map.size() >= capacity) {
            K oldest = map.keySet().iterator().next();
            map.remove(oldest);
        }
        map.put(key, value);
    }

    public String display() {
        return "Cache: " + map.toString();
    }
}

// ------------------ Resource Monitor ------------------
class ResourceMonitor {
    public static String logUsage() {
        long memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        int cores = Runtime.getRuntime().availableProcessors();
        return "Memory Used: " + (memory / 1024) + " KB | CPU Cores: " + cores;
    }
}

// ------------------ File Compressor ------------------
class FileCompressor {
    public static void compress(String inputFile, String outputFile) throws IOException {
        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            ZipEntry entry = new ZipEntry(new File(inputFile).getName());
            zos.putNextEntry(entry);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }
            zos.closeEntry();
        }
    }
}

// ------------------ Dashboard (Swing UI) ------------------
public class AutodeskOptimizer extends JFrame {
    private JTextArea logArea;
    private LRUCache<Integer, String> cache;

    public AutodeskOptimizer() {
        setTitle("Autodesk Server Optimizer");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        logArea = new JTextArea();
        logArea.setEditable(false);
        cache = new LRUCache<>(3);

        JButton monitorBtn = new JButton("Check Resources");
        JButton cacheBtn = new JButton("Test Cache");
        JButton compressBtn = new JButton("Compress File");

        monitorBtn.addActionListener(e -> {
            String usage = ResourceMonitor.logUsage();
            logArea.append(usage + "\n");
        });

        cacheBtn.addActionListener(e -> {
            cache.put(1, "CAD File A");
            cache.put(2, "CAD File B");
            cache.put(3, "CAD File C");
            cache.get(1); // Access File A
            cache.put(4, "CAD File D"); // Evicts oldest
            logArea.append(cache.display() + "\n");
        });

        compressBtn.addActionListener(e -> {
            try {
                FileCompressor.compress("sample.txt", "compressed.zip");
                logArea.append("File compressed: sample.txt → compressed.zip\n");
            } catch (IOException ex) {
                logArea.append("Compression failed: " + ex.getMessage() + "\n");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(monitorBtn);
        buttonPanel.add(cacheBtn);
        buttonPanel.add(compressBtn);

        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(logArea), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AutodeskOptimizer().setVisible(true));
    }
}
