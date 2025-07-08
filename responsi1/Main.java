import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JurnalPencarian sistem = new JurnalPencarian();

        System.out.println("Memuat data jurnal awal secara manual...");
        sistem.tambahJurnal(new Jurnal("Deep Learning for Natural Language Processing", "John Doe", "AI", "This paper explores advanced deep learning techniques for natural language processing, focusing on recurrent neural networks and transformers.", 2023));
        sistem.tambahJurnal(new Jurnal("Blockchain in Supply Chain Management", "Jane Smith", "Blockchain", "Investigating the application of blockchain technology in supply chain management for enhanced transparency and traceability.", 2022));
        sistem.tambahJurnal(new Jurnal("Reinforcement Learning in Robotics Control", "Alice Brown", "Robotics", "A study on applying reinforcement learning algorithms to robotic control, including path planning and object manipulation.", 2024));
        sistem.tambahJurnal(new Jurnal("Optimizing SQL Queries for Large Databases", "Bob White", "Database", "Techniques for improving the performance of SQL database queries in large-scale data environments, covering indexing and query optimization.", 2021));
        sistem.tambahJurnal(new Jurnal("Natural Language Understanding and Generation", "Charlie Green", "NLP", "Research on methods to enable computers to understand and generate human language, including sentiment analysis and text summarization.", 2023));
        sistem.tambahJurnal(new Jurnal("Ethical Implications of Artificial Intelligence", "Alice Brown", "Ethics", "Discussing the ethical implications and societal challenges posed by the rapid development of artificial intelligence.", 2024));
        sistem.tambahJurnal(new Jurnal("Big Data Analytics for Business Intelligence", "David Lee", "Data Science", "Analyzing large datasets to discover patterns and insights for strategic business intelligence and decision-making.", 2022));
        sistem.tambahJurnal(new Jurnal("Quantum Computing Fundamentals and Applications", "Dr. Lena Hansen", "Physics", "An introduction to the principles and applications of quantum computing in cryptography and materials science.", 2025));
        sistem.tambahJurnal(new Jurnal("Cybersecurity Threats and Defense Strategies", "Mark Johnson", "Cybersecurity", "Exploring modern cybersecurity threats and effective defense strategies for individuals and organizations.", 2024));
        sistem.tambahJurnal(new Jurnal("Sustainable Energy Solutions for Urban Areas", "Sarah Connor", "Environment", "Research on renewable energy sources like solar and wind power, and their integration into urban infrastructure for sustainable development.", 2023));
        sistem.tambahJurnal(new Jurnal("The Impact of Social Media on Political Discourse", "Emily Watson", "Sociology", "An analysis of how social media platforms influence political discourse, public opinion, and civic engagement.", 2022));
        sistem.tambahJurnal(new Jurnal("Climate Change Adaptation in Coastal Regions", "Dr. Alex Kim", "Climate Science", "Strategies and case studies for adapting to the impacts of climate change in vulnerable coastal regions, including sea-level rise.", 2025));
        sistem.tambahJurnal(new Jurnal("Advances in Medical Imaging Technologies", "Dr. Maria Rossi", "Biomedical Engineering", "Recent advancements in medical imaging technologies such as MRI, CT scans, and ultrasound for enhanced diagnostics.", 2024));
        sistem.tambahJurnal(new Jurnal("Behavioral Economics and Decision Making", "Daniel Clark", "Economics", "Exploring principles of behavioral economics and their application in understanding human decision-making processes under uncertainty.", 2023));
        sistem.tambahJurnal(new Jurnal("Biodiversity Conservation in Tropical Ecosystems", "Prof. Ben Carter", "Biology", "Research on methods and challenges in conserving biodiversity within unique and fragile tropical rainforest ecosystems.", 2025));
        sistem.tambahJurnal(new Jurnal("AI in Healthcare Diagnostics", "Dr. Alice Brown", "Medical AI", "Applications of artificial intelligence in medical imaging analysis and disease diagnosis for improved patient outcomes.", 2024));
        sistem.tambahJurnal(new Jurnal("Advanced Robotics for Manufacturing", "Alice Brown", "Robotics", "Applications of advanced robotics and automation in modern manufacturing processes for increased precision, speed, and safety.", 2025));
        sistem.tambahJurnal(new Jurnal("Cyber-Physical Systems Security", "Mark Johnson", "Cybersecurity", "Addressing security challenges and vulnerabilities in cyber-physical systems, including critical infrastructure protection.", 2022));
        sistem.tambahJurnal(new Jurnal("Big Data Engineering for IoT", "David Lee", "Data Science", "Focuses on scalable data ingestion and processing for Internet of Things applications.", 2023));
        sistem.tambahJurnal(new Jurnal("Privacy-Preserving Machine Learning", "John Doe", "AI", "Exploring techniques for training machine learning models while preserving data privacy.", 2024));
        sistem.tambahJurnal(new Jurnal("Social Impact of AI", "Emily Watson", "Sociology", "Investigating the broader societal implications and ethical considerations of AI technologies.", 2023));

        sistem.tambahTopikHierarki("Ilmu Komputer", "AI");
        sistem.tambahTopikHierarki("AI", "NLP");
        sistem.tambahTopikHierarki("AI", "Reinforcement Learning");
        sistem.tambahTopikHierarki("Ilmu Komputer", "Blockchain");
        sistem.tambahTopikHierarki("Ilmu Komputer", "Database");
        sistem.tambahTopikHierarki("Sains", "Fisika");
        sistem.tambahTopikHierarki("Sains", "Biologi");
        sistem.tambahTopikHierarki("Sains", "Ilmu Lingkungan");
        sistem.tambahTopikHierarki("Sains", "Climate Science");
        sistem.tambahTopikHierarki("Teknologi", "Cybersecurity");
        sistem.tambahTopikHierarki("Sosial", "Sosiologi");
        sistem.tambahTopikHierarki("Sosial", "Ekonomi");
        sistem.tambahTopikHierarki("Kesehatan", "Biomedical Engineering");
        sistem.tambahTopikHierarki("Kesehatan", "Medical AI");
        sistem.tambahTopikHierarki("Robotika", "Kontrol Robot");

        sistem.bangunGraf(); 

        System.out.println("Sistem siap digunakan!\n");

        int pilihan;
        do {
            System.out.println("\n--- Sistem Pencarian Jurnal ---");
            System.out.println("1. Cari Jurnal");
            System.out.println("2. Urutkan Hasil Pencarian (Relevansi, Tahun, Penulis)");
            System.out.println("3. Temukan Jurnal Terdekat");
            System.out.println("4. Daftar Baca Nanti Anda");
            System.out.println("5. Fitur Analisis Jurnal"); 
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            try {
                pilihan = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Mohon masukkan angka.");
                pilihan = -1;
                continue;
            }

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan kata kunci pencarian: ");
                    String keyword = scanner.nextLine();
                    List<Jurnal> hasil = sistem.cariJurnal(keyword);
                    System.out.println("\n--- Hasil Pencarian untuk '" + keyword + "' ---");
                    if (hasil.isEmpty()) {
                        System.out.println("Tidak ditemukan jurnal yang cocok.");
                    } else {
                        
                        for (int i = 0; i < hasil.size(); i++) {
                            System.out.println((i + 1) + ". " + hasil.get(i).toString());
                        }

                        System.out.print("\nMasukkan nomor jurnal yang ingin ditambahkan ke 'Daftar Baca Nanti' (pisahkan dengan koma jika lebih dari satu, ketik 'semua' untuk semua, atau '0' untuk tidak menambahkan): ");
                        String pilihanTambah = scanner.nextLine().trim();

                        if (pilihanTambah.equalsIgnoreCase("semua")) {
                            hasil.forEach(sistem::tambahKeDaftarBacaNanti);
                        } else if (pilihanTambah.equals("0")) {
                            System.out.println("Tidak ada jurnal yang ditambahkan ke 'Daftar Baca Nanti'.");
                        } else {
                            try {
                                String[] nomorStr = pilihanTambah.split(",");
                                for (String s : nomorStr) {
                                    int nomorJurnal = Integer.parseInt(s.trim());
                                    if (nomorJurnal > 0 && nomorJurnal <= hasil.size()) {
                                        sistem.tambahKeDaftarBacaNanti(hasil.get(nomorJurnal - 1));
                                    } else {
                                        System.out.println("Nomor jurnal '" + s.trim() + "' tidak valid.");
                                    }
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Input tidak valid. Mohon masukkan nomor, 'semua', atau '0'.");
                            }
                        }
                    }
                    break;
                    

                case 2: 
                    System.out.println("\n--- Urutkan Hasil Pencarian Berdasarkan ---");
                    System.out.println("  a. Relevansi Kata Kunci");
                    System.out.println("  b. Tahun Publikasi (Terbaru)");
                    System.out.println("  c. Nama Penulis (A-Z)");
                    System.out.print("Pilih opsi pengurutan (a/b/c): ");
                    String sortOption = scanner.nextLine().toLowerCase();
                    List<Jurnal> sortedJurnals = new ArrayList<>();
                    switch (sortOption) {
                        case "a":
                            System.out.print("Masukkan kata kunci relevansi: ");
                            String sortKeyword = scanner.nextLine();
                            sortedJurnals = sistem.urutkanJurnalByRelevansi(sortKeyword);
                            System.out.println("\n--- Jurnal Terurut Berdasarkan Relevansi ('" + sortKeyword + "') ---");
                            break;
                        case "b":
                            sortedJurnals = sistem.urutkanJurnalByTahun();
                            System.out.println("\n--- Jurnal Terurut Berdasarkan Tahun Publikasi ---");
                            break;
                        case "c":
                            sortedJurnals = sistem.urutkanJurnalByPenulis();
                            System.out.println("\n--- Jurnal Terurut Berdasarkan Penulis ---");
                            break;
                        default:
                            System.out.println("Opsi pengurutan tidak valid.");
                            break;
                    }
                    if (!sortedJurnals.isEmpty()) {
                        sortedJurnals.forEach(System.out::println);
                    } else if (sortOption.matches("[abc]")) {
                        System.out.println("Tidak ada jurnal untuk diurutkan.");
                    }
                    break;

                case 3: 
                    System.out.print("Masukkan judul jurnal acuan untuk mencari yang terdekat: ");
                    String judulAcuan = scanner.nextLine();
                    List<Jurnal> jurnalTerdekat = sistem.temukanJurnalTerdekat(judulAcuan);
                    System.out.println("\n--- Jurnal Terdekat dengan '" + judulAcuan + "' ---");
                    if (jurnalTerdekat.isEmpty()) {
                        System.out.println("Tidak ditemukan jurnal terdekat.");
                    } else {
                        jurnalTerdekat.forEach(System.out::println);
                    }
                    break;

                case 4: 
                    System.out.println("\n--- Manajemen Daftar Baca Nanti ---");
                    System.out.println(" a. Tampilkan Daftar Baca Nanti");
                    System.out.println(" b. Tandai Jurnal Sebagai Sudah Dibaca (pilih dari daftar)");
                    System.out.print("Pilih opsi (a/b): ");
                    String readListOption = scanner.nextLine().toLowerCase();
    
                    if (readListOption.equals("a")) {
                        sistem.tampilkanDaftarBacaNanti();
                    } else if (readListOption.equals("b")) {
                    if (sistem.isDaftarBacaNantiKosong()) {
                        System.out.println("Daftar Baca Nanti Anda kosong. Tidak ada yang bisa ditandai.");
                    break;
                    }
                    sistem.tampilkanDaftarBacaNanti(); 
        
                    System.out.print("\nMasukkan nomor jurnal yang ingin ditandai sebagai sudah dibaca: ");
                    try {
                    int nomorJurnal = Integer.parseInt(scanner.nextLine());
                        sistem.tandaiJurnalSudahDibacaByNomor(nomorJurnal);
                    } catch (NumberFormatException e) {
                        System.out.println("Input tidak valid. Harap masukkan angka.");
                    }
                    } else {
                        System.out.println("Opsi tidak valid.");
                    }
                    break;
                case 5: 
                    System.out.println("\n--- Fitur Analisis Jurnal Lanjutan ---");
                    System.out.println("  a. Urutkan Jurnal by Judul"); 
                    System.out.println("  b. Cari Jurnal by Judul"); 
                    System.out.println("  c. Cari Jurnal Berdasarkan Tahun");
                    System.out.println("  d. Analisis Jaringan Jurnal Mirip");
                    System.out.println("  e. Rekomendasikan Jurnal Terkait");
                    System.out.println("  f. Tampilkan Riwayat Pencarian Anda");
                    System.out.println("  g. Tampilkan Struktur Topik");
                    System.out.println("  h. Tampilkan Semua Jurnal");
                    System.out.print("Pilih opsi analisis (a/b/c/d/e/f/g/h): ");
                    String analysisOption = scanner.nextLine().toLowerCase();
                    switch (analysisOption) {
                        case "a": 
                            sistem.quickSortJurnalByJudul(); 
                            sistem.tampilkanDaftarJurnalLengkap();
                            break;
                        case "b": 
                            System.out.println("Peringatan: Binary Search hanya efektif pada data yang sudah terurut.");
                            System.out.print("Pastikan Anda sudah mengurutkan jurnal berdasarkan judul (Opsi 5.a) terlebih dahulu.\n");
                            System.out.print("Masukkan judul jurnal yang ingin dicari: ");
                            String targetJudul = scanner.nextLine();
                            Jurnal foundJurnalBinary = sistem.binarySearchJurnalByJudul(targetJudul); 
                            if (foundJurnalBinary != null) {
                                System.out.println("Jurnal '" + targetJudul + "' ditemukan: " + foundJurnalBinary);
                            } else {
                                System.out.println("Jurnal '" + targetJudul + "' tidak ditemukan.");
                            }
                            break;
                        case "c":
                            try {
                            System.out.print("Masukkan tahun mulai: ");
                            int tahunMulai = Integer.parseInt(scanner.nextLine());
                            System.out.print("Masukkan tahun selesai: ");
                            int tahunSelesai = Integer.parseInt(scanner.nextLine());

                            if (tahunMulai > tahunSelesai) {
                                System.out.println("Tahun mulai tidak boleh lebih besar dari tahun selesai.");
                            } else {
                                sistem.tampilkanJurnalByRentangTahun(tahunMulai, tahunSelesai);
                            }
                            } catch (NumberFormatException e) {
                            System.out.println("Input tidak valid. Harap masukkan angka tahun yang benar.");
                            }
                            break;
                        case "d":
                            System.out.print("Masukkan judul jurnal acuan untuk melihat koneksi terdekatnya: ");
                            String judulAcuanKoneksi = scanner.nextLine();
                            sistem.tampilkanJurnalTerhubungMST(judulAcuanKoneksi);
                            break;
                        case "e":
                            System.out.print("Masukkan kata kunci awal untuk rekomendasi: ");
                            String spKeyword = scanner.nextLine();
                            sistem.temukanShortestPathJurnal(spKeyword); 
                        case "f":
                            sistem.tampilkanHistoriKataKunci();
                            break;
                        case "g":
                            sistem.tampilkanHierarkiTopik();
                            break;
                        case "h":
                            sistem.tampilkanDaftarJurnalLengkap();
                            break;
                        default:
                            System.out.println("Opsi analisis tidak valid.");
                            break;
                    }
                    break;

                case 0:
                    System.out.println("Terima kasih telah menggunakan Sistem Pencarian Jurnal.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 0);

        scanner.close();
    }
}