import java.util.*;
import java.util.stream.Collectors;

public class JurnalPencarian {

    protected List<Jurnal> daftarJurnal;
    private Stack<String> historiKataKunci;
    private Queue<Jurnal> daftarBacaNanti;
    private Set<String> kataKunciUnik;
    private Map<String, List<Jurnal>> mappingKataKunciJurnal;
    private Map<String, List<String>> hierarkiTopik;
    private TreeNode rootTahunBST;
    private Map<Jurnal, List<Edge>> graph;

    public JurnalPencarian() {
        daftarJurnal = new ArrayList<>();
        historiKataKunci = new Stack<>();
        daftarBacaNanti = new LinkedList<>();
        kataKunciUnik = new HashSet<>();
        mappingKataKunciJurnal = new HashMap<>();
        hierarkiTopik = new HashMap<>();
        rootTahunBST = null;
        graph = new HashMap<>();
    }

    public void tambahJurnal(Jurnal jurnal) {
        daftarJurnal.add(jurnal);
        rootTahunBST = insertIntoYearBST(rootTahunBST, jurnal);
        graph.put(jurnal, new ArrayList<>());
    }

    public void tampilkanDaftarJurnalLengkap() {
        System.out.println("\n--- Semua Jurnal dalam Sistem ---");
        if (daftarJurnal.isEmpty()) {
            System.out.println("Belum ada jurnal yang ditambahkan.");
        } else {
            daftarJurnal.forEach(System.out::println);
        }
    }

    public List<Jurnal> cariJurnal(String keyword) {
        historiKataKunci.push(keyword);
        kataKunciUnik.add(keyword);
        List<Jurnal> hasilPencarian = new ArrayList<>();
        for (Jurnal jurnal : daftarJurnal) {
            if (jurnal.getJudul().toLowerCase().contains(keyword.toLowerCase()) ||
                    jurnal.getPenulis().toLowerCase().contains(keyword.toLowerCase()) ||
                    jurnal.getTopik().toLowerCase().contains(keyword.toLowerCase()) ||
                    jurnal.getKonten().toLowerCase().contains(keyword.toLowerCase())) {
                hasilPencarian.add(jurnal);
            }
        }
        mappingKataKunciJurnal.put(keyword, hasilPencarian);
        return hasilPencarian;
    }

    public void tambahKeDaftarBacaNanti(Jurnal jurnal) {
        if (!daftarBacaNanti.contains(jurnal)) {
            daftarBacaNanti.offer(jurnal);
            System.out.println("Jurnal '" + jurnal.getJudul() + "' ditambahkan ke 'Daftar Baca Nanti'.");
        } else {
            System.out.println("Jurnal '" + jurnal.getJudul() + "' sudah ada di 'Daftar Baca Nanti'.");
        }
    }

    public void tandaiJurnalSudahDibacaByNomor(int nomor) {
        int index = nomor - 1;
        if (index >= 0 && index < daftarBacaNanti.size()) {
            List<Jurnal> tempList = new ArrayList<>(daftarBacaNanti);
            Jurnal jurnalDihapus = tempList.remove(index);
            daftarBacaNanti.clear();
            daftarBacaNanti.addAll(tempList);
            System.out.println("Berhasil menandai jurnal sebagai sudah dibaca: '" + jurnalDihapus.getJudul() + "'");
        } else {
            System.out.println("Nomor tidak valid. Tidak ada jurnal yang ditandai.");
        }
    }

    public boolean isDaftarBacaNantiKosong() {
        return daftarBacaNanti.isEmpty();
    }

    public void tampilkanDaftarBacaNanti() {
        System.out.println("\n--- Daftar Baca Nanti Anda ---");
        if (daftarBacaNanti.isEmpty()) {
            System.out.println("Daftar Baca Nanti Anda masih kosong. Tambahkan jurnal menarik!");
        } else {
            int i = 1;
            for (Jurnal jurnal : daftarBacaNanti) {
                System.out.println(i++ + ". " + jurnal.getJudul() + " (Penulis: " + jurnal.getPenulis() + ")");
            }
        }
    }

    public List<Jurnal> urutkanJurnalByRelevansi(String keyword) {
        List<Jurnal> jurnalUntukDiurutkan = new ArrayList<>(daftarJurnal);
        jurnalUntukDiurutkan.sort((j1, j2) -> Integer.compare(hitungRelevansi(j2, keyword), hitungRelevansi(j1, keyword)));
        System.out.println("Jurnal diurutkan berdasarkan relevansi untuk kata kunci '" + keyword + "'.");
        return jurnalUntukDiurutkan;
    }

    private int hitungRelevansi(Jurnal jurnal, String keyword) {
        int score = 0;
        String lowerKeyword = keyword.toLowerCase();
        if (jurnal.getJudul().toLowerCase().contains(lowerKeyword)) score += 3;
        if (jurnal.getTopik().toLowerCase().contains(lowerKeyword)) score += 2;
        if (jurnal.getPenulis().toLowerCase().contains(lowerKeyword)) score += 1;
        if (jurnal.getKonten().toLowerCase().contains(lowerKeyword)) score += 1;
        return score;
    }

    public List<Jurnal> urutkanJurnalByTahun() {
        List<Jurnal> jurnalUntukDiurutkan = new ArrayList<>(daftarJurnal);
        jurnalUntukDiurutkan.sort((j1, j2) -> Integer.compare(j2.getTahunPublikasi(), j1.getTahunPublikasi()));
        System.out.println("Jurnal diurutkan berdasarkan tahun publikasi (terbaru ke terlama).");
        return jurnalUntukDiurutkan;
    }

    public List<Jurnal> urutkanJurnalByPenulis() {
        List<Jurnal> jurnalUntukDiurutkan = new ArrayList<>(daftarJurnal);
        jurnalUntukDiurutkan.sort(Comparator.comparing(Jurnal::getPenulis));
        System.out.println("Jurnal diurutkan berdasarkan nama penulis (A-Z).");
        return jurnalUntukDiurutkan;
    }

    public void quickSortJurnalByJudul() {
        if (daftarJurnal.isEmpty()) {
            System.out.println("Tidak ada jurnal untuk diurutkan.");
            return;
        }
        quickSortRecursive(daftarJurnal, 0, daftarJurnal.size() - 1);
        System.out.println("Jurnal diurutkan berdasarkan judul menggunakan Quick Sort.");
    }

    private void quickSortRecursive(List<Jurnal> journals, int low, int high) {
        if (low < high) {
            int pi = partition(journals, low, high);
            quickSortRecursive(journals, low, pi - 1);
            quickSortRecursive(journals, pi + 1, high);
        }
    }

    private int partition(List<Jurnal> journals, int low, int high) {
        Jurnal pivot = journals.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (journals.get(j).getJudul().compareToIgnoreCase(pivot.getJudul()) < 0) {
                i++;
                Collections.swap(journals, i, j);
            }
        }
        Collections.swap(journals, i + 1, high);
        return (i + 1);
    }
    
    public Jurnal binarySearchJurnalByJudul(String targetJudul) {
        int low = 0;
        int high = daftarJurnal.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            String midJudul = daftarJurnal.get(mid).getJudul();
            int cmp = midJudul.compareToIgnoreCase(targetJudul);

            if (cmp == 0) {
                return daftarJurnal.get(mid);
            }
            if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    private TreeNode insertIntoYearBST(TreeNode root, Jurnal jurnal) {
        int tahun = jurnal.getTahunPublikasi();
        if (root == null) {
            root = new TreeNode(tahun);
            root.daftarJurnalDiTahunIni.add(jurnal);
            return root;
        }

        if (tahun < root.tahun) {
            root.left = insertIntoYearBST(root.left, jurnal);
        } else if (tahun > root.tahun) {
            root.right = insertIntoYearBST(root.right, jurnal);
        } else {
            root.daftarJurnalDiTahunIni.add(jurnal);
        }
        return root;
    }

    public void tampilkanJurnalByRentangTahun(int tahunMulai, int tahunSelesai) {
        System.out.printf("\n--- Menampilkan Jurnal dari Tahun %d sampai %d (Optimasi BST) ---\n", tahunMulai, tahunSelesai);
        List<Jurnal> hasil = new ArrayList<>();
        cariDalamRentang(rootTahunBST, tahunMulai, tahunSelesai, hasil);

        if (hasil.isEmpty()) {
            System.out.println("Tidak ditemukan jurnal dalam rentang tahun tersebut.");
        } else {
            hasil.forEach(System.out::println);
        }
    }

    private void cariDalamRentang(TreeNode root, int mulai, int selesai, List<Jurnal> hasil) {
        if (root == null) {
            return;
        }
        if (root.tahun > mulai) {
            cariDalamRentang(root.left, mulai, selesai, hasil);
        }
        if (root.tahun >= mulai && root.tahun <= selesai) {
            hasil.addAll(root.daftarJurnalDiTahunIni);
        }
        if (root.tahun < selesai) {
            cariDalamRentang(root.right, mulai, selesai, hasil);
        }
    }


public void bangunMSTKruskal() {
    System.out.println("\n--- Membangun Maximum Spanning Tree (MST) dengan Algoritma Kruskal ---");
    if (graph.isEmpty()) {
        System.out.println("Graf belum dibangun. Bangun graf terlebih dahulu.");
        return;
    }

    List<Edge> semuaSisi = new ArrayList<>();
    Set<Set<Jurnal>> sisiSudahDitambahkan = new HashSet<>();
    for (Jurnal j : graph.keySet()) {
        for (Edge e : graph.get(j)) {
            Set<Jurnal> pasangan = new HashSet<>(Arrays.asList(e.getSource(), e.getDestination()));
            if (!sisiSudahDitambahkan.contains(pasangan)) {
                semuaSisi.add(e);
                sisiSudahDitambahkan.add(pasangan);
            }
        }
    }
    
    semuaSisi.sort(Comparator.comparingDouble(Edge::getWeight).reversed());
    DisjointSet ds = new DisjointSet(daftarJurnal);
    List<Edge> hasilMST = new ArrayList<>();
    double totalBobot = 0;

    for (Edge sisi : semuaSisi) {
        Jurnal source = sisi.getSource();
        Jurnal destination = sisi.getDestination();

        if (!ds.find(source).equals(ds.find(destination))) {
            hasilMST.add(sisi); 
            ds.union(source, destination); 
            totalBobot += sisi.getWeight();
        }
    }

    System.out.println("Kerangka Jurnal Paling Relevan (Maximum Spanning Tree):");
    if (hasilMST.isEmpty()) {
        System.out.println("Tidak ada koneksi yang dapat membentuk MST.");
    } else {
        for (Edge edge : hasilMST) {
            System.out.printf(" - %s <--> %s (Kemiripan: %.2f%%)\n",
                    edge.getSource().getJudul(),
                    edge.getDestination().getJudul(),
                    edge.getWeight() * 100);
        }
        System.out.printf("\nTotal Skor Kemiripan MST: %.2f\n", totalBobot);
    }
}

    public List<Jurnal> temukanJurnalTerdekat(String judulJurnalAcuan) {
        Jurnal jurnalAcuan = daftarJurnal.stream()
                .filter(j -> j.getJudul().equalsIgnoreCase(judulJurnalAcuan))
                .findFirst()
                .orElse(null);

        if (jurnalAcuan == null) {
            System.out.println("Jurnal acuan '" + judulJurnalAcuan + "' tidak ditemukan.");
            return new ArrayList<>();
        }

        System.out.println("Mencari jurnal terdekat dengan '" + jurnalAcuan.getJudul() + "'...");
        List<Jurnal> jurnalTerdekat = new ArrayList<>();
        String acuanTopik = jurnalAcuan.getTopik().toLowerCase();
        String acuanKonten = jurnalAcuan.getKonten().toLowerCase();
        String acuanPenulis = jurnalAcuan.getPenulis().toLowerCase();

        for (Jurnal j : daftarJurnal) {
            if (j.equals(jurnalAcuan)) {
                continue;
            }
            int kemiripanScore = 0;
            if (j.getTopik().toLowerCase().equals(acuanTopik)) {
                kemiripanScore += 10;
            }
            if (j.getPenulis().toLowerCase().equals(acuanPenulis)) {
                kemiripanScore += 5;
            }
            for (String word : acuanKonten.split("\\s+")) {
                if (word.length() > 2 && j.getKonten().toLowerCase().contains(word)) {
                    kemiripanScore++;
                }
            }
            if (kemiripanScore > 5) {
                jurnalTerdekat.add(j);
            }
        }

        jurnalTerdekat.sort((j1, j2) -> {
            int score1 = 0;
            if (j1.getTopik().toLowerCase().equals(acuanTopik)) score1 += 10;
            if (j1.getPenulis().toLowerCase().equals(acuanPenulis)) score1 += 5;
            for (String word : acuanKonten.split("\\s+")) {
                if (word.length() > 2 && j1.getKonten().toLowerCase().contains(word)) score1++;
            }
            int score2 = 0;
            if (j2.getTopik().toLowerCase().equals(acuanTopik)) score2 += 10;
            if (j2.getPenulis().toLowerCase().equals(acuanPenulis)) score2 += 5;
            for (String word : acuanKonten.split("\\s+")) {
                if (word.length() > 2 && j2.getKonten().toLowerCase().contains(word)) score2++;
            }
            return Integer.compare(score2, score1);
        });

        return jurnalTerdekat;
    }

    public void tambahTopikHierarki(String parentTopik, String childTopik) {
        hierarkiTopik.computeIfAbsent(parentTopik, k -> new ArrayList<>()).add(childTopik);
    }

    public void tampilkanHierarkiTopik() {
        System.out.println("\n--- Struktur Hierarki Topik ---");
        if (hierarkiTopik.isEmpty()) {
            System.out.println("Belum ada hierarki topik yang diatur.");
            return;
        }
        for (Map.Entry<String, List<String>> entry : hierarkiTopik.entrySet()) {
            System.out.println("Topik Utama: " + entry.getKey());
            if (!entry.getValue().isEmpty()) {
                entry.getValue().forEach(subTopik -> System.out.println("  - " + subTopik));
            }
        }
    }

    public void tampilkanHistoriKataKunci() {
        System.out.println("\n--- Riwayat Pencarian Anda ---");
        if (historiKataKunci.isEmpty()) {
            System.out.println("Anda belum melakukan pencarian apapun.");
            return;
        }
        List<String> tempHistory = new ArrayList<>(historiKataKunci);
        Collections.reverse(tempHistory);
        tempHistory.forEach(keyword -> System.out.println("- " + keyword));
    }

    private double hitungKemiripanJurnal(Jurnal j1, Jurnal j2) {
        double commonalityScore = 0;
        if (j1.getTopik().equalsIgnoreCase(j2.getTopik())) commonalityScore += 0.5;
        if (j1.getPenulis().equalsIgnoreCase(j2.getPenulis())) commonalityScore += 0.3;

        Set<String> words1 = Arrays.stream(j1.getKonten().toLowerCase().split("\\W+"))
                .filter(word -> word.length() > 2)
                .collect(Collectors.toSet());
        Set<String> words2 = Arrays.stream(j2.getKonten().toLowerCase().split("\\W+"))
                .filter(word -> word.length() > 2)
                .collect(Collectors.toSet());

        Set<String> intersection = new HashSet<>(words1);
        intersection.retainAll(words2);
        Set<String> union = new HashSet<>(words1);
        union.addAll(words2);

        double jaccardSimilarity = 0;
        if (!union.isEmpty()) {
            jaccardSimilarity = (double) intersection.size() / union.size();
        }

        return Math.min(1.0, commonalityScore + jaccardSimilarity * 0.7);
    }

    public void bangunGraf() {
        if (daftarJurnal.size() < 2) {
            System.out.println("Tidak cukup jurnal untuk membangun graf (minimal 2 jurnal diperlukan).");
            return;
        }
        for (Jurnal j : daftarJurnal) {
            graph.put(j, new ArrayList<>());
        }

        System.out.println("Membangun graf hubungan antar jurnal...");
        for (int i = 0; i < daftarJurnal.size(); i++) {
            for (int j = i + 1; j < daftarJurnal.size(); j++) {
                Jurnal j1 = daftarJurnal.get(i);
                Jurnal j2 = daftarJurnal.get(j);
                double kemiripan = hitungKemiripanJurnal(j1, j2);
                if (kemiripan > 0.01) {
                    graph.get(j1).add(new Edge(j1, j2, kemiripan));
                    graph.get(j2).add(new Edge(j2, j1, kemiripan));
                }
            }
        }
        System.out.println("Graf hubungan antar jurnal berhasil dibangun.");
    }

    public void tampilkanJurnalTerhubungMST(String judulJurnalAcuan) {
        System.out.println("\n--- Jurnal Terhubung dalam Jaringan MST untuk '" + judulJurnalAcuan + "' ---");

        Jurnal jurnalAcuan = daftarJurnal.stream()
                .filter(j -> j.getJudul().equalsIgnoreCase(judulJurnalAcuan))
                .findFirst()
                .orElse(null);

        if (jurnalAcuan == null) {
            System.out.println("Jurnal acuan '" + judulJurnalAcuan + "' tidak ditemukan dalam daftar.");
            return;
        }

        if (graph.isEmpty() || graph.values().stream().allMatch(List::isEmpty)) {
            System.out.println("Graf hubungan antar jurnal belum dibangun atau tidak ada koneksi. Harap bangun graf terlebih dahulu.");
            return;
        }

        List<Edge> connectedEdges = graph.getOrDefault(jurnalAcuan, Collections.emptyList());

        if (connectedEdges.isEmpty()) {
            System.out.println("Jurnal '" + judulJurnalAcuan + "' tidak memiliki koneksi langsung yang signifikan dalam graf.");
            System.out.println("Mungkin karena kemiripannya di bawah ambang batas atau tidak ada jurnal lain yang mirip.");
            return;
        }

        connectedEdges.sort(Comparator.comparingDouble(Edge::getWeight).reversed());

        System.out.println("Jurnal yang terhubung langsung:");
        for (Edge edge : connectedEdges) {
            System.out.printf("  - %s (Kemiripan: %.2f%%)\n",
                    edge.getDestination().getJudul(),
                    edge.getWeight() * 100);
        }
    }

    public void temukanShortestPathJurnal(String kataKunciSumber) {
        System.out.println("\n--- Rekomendasi Jurnal Terkait (Shortest Path - Algoritma Dijkstra) ---");
        if (daftarJurnal.isEmpty()) {
            System.out.println("Tidak ada jurnal dalam sistem.");
            return;
        }
        if (graph.isEmpty() || graph.values().stream().allMatch(List::isEmpty)) {
            System.out.println("Graf belum dibangun atau tidak ada edge yang relevan. Membangun graf sekarang...");
            bangunGraf();
            if (graph.isEmpty() || graph.values().stream().allMatch(List::isEmpty)) {
                System.out.println("Gagal membangun graf atau tidak ada edge yang relevan.");
                return;
            }
        }

        Jurnal sumberJurnal = daftarJurnal.stream()
                .filter(j -> j.getJudul().toLowerCase().contains(kataKunciSumber.toLowerCase()) ||
                        j.getTopik().toLowerCase().contains(kataKunciSumber.toLowerCase()) ||
                        j.getPenulis().toLowerCase().contains(kataKunciSumber.toLowerCase()) ||
                        j.getKonten().toLowerCase().contains(kataKunciSumber.toLowerCase()))
                .findFirst()
                .orElse(null);

        if (sumberJurnal == null) {
            System.out.println("Tidak dapat menemukan jurnal sumber untuk kata kunci '" + kataKunciSumber + "'.");
            return;
        }
        if (!graph.containsKey(sumberJurnal) || graph.get(sumberJurnal).isEmpty()) {
            System.out.println("Jurnal sumber '" + sumberJurnal.getJudul() + "' tidak memiliki koneksi yang cukup dalam graf untuk dianalisis.");
            return;
        }

        System.out.println("Mencari jalur rekomendasi dari jurnal: " + sumberJurnal.getJudul());

        Map<Jurnal, Double> distances = new HashMap<>();
        Map<Jurnal, Jurnal> predecessors = new HashMap<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble(Edge::getWeight));

        for (Jurnal j : daftarJurnal) {
            distances.put(j, Double.POSITIVE_INFINITY);
        }
        distances.put(sumberJurnal, 0.0);
        pq.add(new Edge(null, sumberJurnal, 0.0));

        while (!pq.isEmpty()) {
            Edge currentEdge = pq.poll();
            Jurnal currentJurnal = currentEdge.getDestination();
            double currentDistance = currentEdge.getWeight();

            if (currentDistance > distances.get(currentJurnal)) {
                continue;
            }

            for (Edge edge : graph.getOrDefault(currentJurnal, Collections.emptyList())) {
                double weight = 1.0 - edge.getWeight();
                if (weight < 0) weight = 0;
                if (weight == 0 && !currentJurnal.equals(edge.getDestination())) {
                    weight = 0.001;
                }

                double newDistance = distances.get(currentJurnal) + weight;

                if (newDistance < distances.get(edge.getDestination())) {
                    distances.put(edge.getDestination(), newDistance);
                    predecessors.put(edge.getDestination(), currentJurnal);
                    pq.add(new Edge(currentJurnal, edge.getDestination(), newDistance));
                }
            }
        }

        System.out.println("Jalur rekomendasi dari '" + sumberJurnal.getJudul() + "':");
        boolean foundRecommendation = false;
        List<Jurnal> recommendedJurnals = new ArrayList<>();
        for (Jurnal destJurnal : daftarJurnal) {
            if (destJurnal.equals(sumberJurnal)) continue;
            if (distances.get(destJurnal) < Double.POSITIVE_INFINITY) {
                recommendedJurnals.add(destJurnal);
            }
        }

        recommendedJurnals.sort(Comparator.comparingDouble(distances::get));

        int recommendationsToShow = 0;
        for (Jurnal recJurnal : recommendedJurnals) {
            if (recommendationsToShow >= 5 || distances.get(recJurnal) <= 0.001) break;

            foundRecommendation = true;
            System.out.println("  - Jurnal: " + recJurnal.getJudul() + " (Jarak: " + String.format("%.2f", distances.get(recJurnal)) + ")");
            List<Jurnal> path = new LinkedList<>();
            Jurnal step = recJurnal;
            while (step != null && !step.equals(sumberJurnal)) {
                path.add(0, step);
                step = predecessors.get(step);
            }
            if (step != null && step.equals(sumberJurnal)) {
                path.add(0, sumberJurnal);
            }
            System.out.println("    Jalur: " + path.stream().map(Jurnal::getJudul).collect(Collectors.joining(" -> ")));
            recommendationsToShow++;
        }

        if (!foundRecommendation) {
            System.out.println("  Tidak ditemukan jalur rekomendasi yang signifikan dari jurnal ini.");
        }
    }
}