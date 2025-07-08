import java.util.*;
import java.time.LocalDate;

class User {
    String nama;
    boolean isJoin;
    boolean sudahBayar;
    int tunggakan;
    Stack<String> riwayatStatus = new Stack<>();

    public User(String nama) {
        this.nama = nama;
        this.isJoin = false;
        this.sudahBayar = false;
        this.tunggakan = 35000; // Tunggakan awal
    }
}

class TreeNode {
    String tanggal;
    int nominal;
    TreeNode left, right;

    TreeNode(String tanggal, int nominal) {
        this.tanggal = tanggal;
        this.nominal = nominal;
    }
}
class BinaryTree {
    class Node {
        String nama; // Nama pengguna
        Node left, right;

        Node(String nama) {
            this.nama = nama;
            this.left = null;
            this.right = null;
        }
    }

    private Node root; // Pindahkan root ke dalam kelas BinaryTree

    public BinaryTree() {
        root = null;
    }

     // Menambahkan pengguna ke dalam Binary Tree
    public void tambahNode(String nama) {
        root = tambahNode(root, nama);
    }

    private Node tambahNode(Node root, String nama) {
        if (root == null) {
            root = new Node(nama);
            return root;
        }


        if (nama.compareTo(root.nama) < 0) {
            root.left = tambahNode(root.left, nama);
        } else if (nama.compareTo(root.nama) > 0) {
            root.right = tambahNode(root.right, nama);
        }

        return root;
    }

    // Menampilkan pengguna yang join WiFi
    public void tampilkanyangJoin() {
        if (root == null) {
            System.out.println("Tidak ada pengguna yang join WiFi.");
        } else {
            tampilkanYangJoin(root);
        }
    }
   private void tampilkanYangJoin(Node root) {
        if (root != null) {
            tampilkanYangJoin(root.left); // Tampilkan subtree kiri
            System.out.println(root.nama); // Tampilkan nama pengguna yang belum join WiFi
            tampilkanYangJoin(root.right); // Tampilkan subtree kanan
        }
    }
}


class BSTNode {
    String nama;
    int totalBayar;
    String tanggal;
    BSTNode left, right;

    BSTNode(String nama, int totalBayar, String tanggal) {
        this.nama = nama;
        this.totalBayar = totalBayar;
        this.tanggal = tanggal;
        this.left = null;
        this.right = null;
    }
}

class Santri {
    int nomor;
    String nama;
    List<String> pembayaran;

    public Santri(int nomor, String nama, List<String> pembayaran) {
        this.nomor = nomor;
        this.nama = nama;
        this.pembayaran = pembayaran;
    }

    public void tampilkanData() {
        System.out.println(nomor + ". " + nama + " → " + pembayaran);
    }
}

public class PSDA_Responsi_L0124005_AyuSaniatusS {
    static List<User> userList = new ArrayList<>();
    static Queue<User> antrianKonfirmasi = new LinkedList<>();
    static Set<String> konfirmasiBulanIni = new HashSet<>();
    static Map<String, Integer> tunggakanMap = new HashMap<>();
    static TreeNode transaksiTree = null;
    static BinaryTree binaryTreeYangJoin = new BinaryTree();
    static BSTNode pembayaranBST = null;
    static Stack<User> riwayatPenambahanUser = new Stack<>();
    static Scanner scanner = new Scanner(System.in);
    static List<Santri> daftarSantri = new ArrayList<>();

    static {
        daftarSantri.add(new Santri(1, "ADILA", Arrays.asList("")));
        daftarSantri.add(new Santri(2, "AIFA ZAHDA", Arrays.asList("35000")));
        daftarSantri.add(new Santri(3, "ANDINA DEWI ", Arrays.asList("35000")));
        daftarSantri.add(new Santri(4, "AYU SANIATUS", Arrays.asList("")));
        daftarSantri.add(new Santri(5, "FATWA ISNAYA", Arrays.asList("")));
        daftarSantri.add(new Santri(6, "RANIA", Arrays.asList("35000")));
        daftarSantri.add(new Santri(7, "ULFI", Arrays.asList("35000")));
        daftarSantri.add(new Santri(8, "ANNA LIYA", Arrays.asList("")));
        daftarSantri.add(new Santri(9, "KARTIKA", Arrays.asList("35000")));
        daftarSantri.add(new Santri(10, "RINA", Arrays.asList("")));
        daftarSantri.add(new Santri(11, "NANA", Arrays.asList("35000")));
        daftarSantri.add(new Santri(12, "NALA", Arrays.asList("")));
        daftarSantri.add(new Santri(13, "SALWA", Arrays.asList("35000")));
        daftarSantri.add(new Santri(14, "SALMA", Arrays.asList("35000")));
        daftarSantri.add(new Santri(15, "SANIA", Arrays.asList("")));

    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String perintah;

        System.out.println("Selamat datang di sistem manajemen WiFi.");

        while (true) {
            System.out.println("\n================ Menu ================");
            System.out.println("1. Join WiFi");
            System.out.println("2. Bayar WiFi");
            System.out.println("3. Informasi password wifi untuk bulan berikutnya");
            System.out.println("4. Yang sudah konfirmsi join wifi bulan ini");
            System.out.println("5. Tampilkan tunggakan");
            System.out.println("6. Tampilkan Riwayat Pembayaran Per Orang");
            System.out.println("7. Tampilkan Pengguna yang Join WiFi");
            System.out.println("8. Keluar");
            System.out.print("Pilih menu (1-8): ");
            perintah = scanner.nextLine();

            switch (perintah) {
                case "1":
                    System.out.print("Masukkan nama: ");
                    String namaJoin = scanner.nextLine();
                    joinWifi(namaJoin);
                    break;
                case "2":
                    System.out.print("Masukkan nama: ");
                    String namaBayar = scanner.nextLine();
                    System.out.print("Masukkan jumlah bayar: ");
                    int jumlahBayar = Integer.parseInt(scanner.nextLine());
                    bayar(namaBayar, jumlahBayar);
                    break;
                case "3":
                    System.out.print("Masukkan nama: ");
                    String namaCekPassword = scanner.nextLine();
                    berikanPasswordWiFi(namaCekPassword);
                    break;
                case "4":
                    System.out.println("===== Yang Sudah Konfirmasi Join WiFi Bulan Ini =====");
                    if (konfirmasiBulanIni.isEmpty()) {
                        System.out.println("Belum ada yang konfirmasi join WiFi bulan ini.");
                    } else {
                        for (String nama : konfirmasiBulanIni) {
                            System.out.println(nama);
                        }
                    }
                    break;
                case "5":
                    tampilkanTunggakan();
                    break;
                case "6":
                    if (pembayaranBST == null) {
                        System.out.println("Belum ada riwayat pembayaran yang tercatat.");
                    } else {
                        System.out.println("===== Riwayat Pembayaran Per Orang (BST) =====");
                        tampilkanRiwayatPembayaranPerOrang(pembayaranBST);
                    }
                    break;
                case "7":
                        System.out.println("===== Pengguna yang Join WiFi =====");
                        binaryTreeYangJoin.tampilkanyangJoin();
                        break;
                case "8":
                    System.out.println("Terima kasih telah menggunakan sistem manajemen WiFi.");
                    return;
                
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    public static void joinWifi(String nama) {
        boolean terdaftar = false;
        for (Santri s : daftarSantri) {
            if (s.nama.equalsIgnoreCase(nama)) {
                terdaftar = true;
                break;
            }
        }
        if(!terdaftar) {
            System.out.println("Nama tidak terdaftar di sistem.");
            return;
        }
        User user = cariUser(nama);
        if (user == null) {
            user = new User(nama);
            userList.add(user);
        }
        user.isJoin = true;
        user.riwayatStatus.push("Join WiFi");
        antrianKonfirmasi.add(user);
        konfirmasiBulanIni.add(nama);
        tunggakanMap.put(nama, user.tunggakan);

        // Menambahkan ke binary tree jika belum join
        binaryTreeYangJoin.tambahNode(nama);

        System.out.println(nama + " telah join WiFi dan ditambahkan ke antrian konfirmasi.");
    }
    public static void tampilkanYangJoin() {
    System.out.println("===== Pengguna yang Join WiFi =====");
    binaryTreeYangJoin.tampilkanyangJoin();
}

    public static void bayar(String nama, int jumlah) {
        User user = cariUser(nama);
        if (user == null || !user.isJoin) {
            System.out.println("User belum join WiFi.");
            return;
        }

        int tunggakan = tunggakanMap.getOrDefault(nama, 35000);
        if (jumlah >= tunggakan) {
            user.sudahBayar = true;
            tunggakanMap.put(nama, 0);
            System.out.println("Pembayaran berhasil. Tunggakan lunas.");
        } else {
            int sisa = tunggakan - jumlah;
            tunggakanMap.put(nama, sisa);
            System.out.println("Pembayaran belum cukup. Sisa tunggakan: " + sisa);
        }
         // Dapatkan tanggal hari ini
    LocalDate tanggalHariIni = LocalDate.now();
    String tanggal = tanggalHariIni.toString();
    
        transaksiTree = tambahKeTree(transaksiTree, tanggal, jumlah);
        pembayaranBST = tambahKeBST(pembayaranBST, nama, jumlah, tanggal);
    }

    public static User cariUser(String nama) {
        for (User u : userList) {
            if (u.nama.equalsIgnoreCase(nama)) return u;
        }
        return null;
    }

    public static TreeNode tambahKeTree(TreeNode root, String tanggal, int nominal) {
        if (root == null) return new TreeNode(tanggal, nominal);
        if (tanggal.compareTo(root.tanggal) < 0)
            root.left = tambahKeTree(root.left, tanggal, nominal);
        else
            root.right = tambahKeTree(root.right, tanggal, nominal);
        return root;
    }

    public static BSTNode tambahKeBST(BSTNode root, String nama, int totalBayar, String tanggal) {
    if (root == null) return new BSTNode(nama, totalBayar, tanggal);
    if (nama.compareTo(root.nama) < 0)
        root.left = tambahKeBST(root.left, nama, totalBayar, tanggal);
    else
        root.right = tambahKeBST(root.right, nama, totalBayar, tanggal);
    return root;
}


    
    public static void berikanPasswordWiFi(String nama) {
        if (konfirmasiBulanIni.contains(nama)) {
            System.out.println("Password WiFi bulan ini: akucintainformatika");
            System.out.println("Hai " + nama + ", kamu sudah bergabung dengan WiFi bulan ini.");
        } else {
            System.out.println("Hai " + nama + ", kamu belum konfirmasi join WiFi bulan ini. Silakan join terlebih dahulu.");
        }
    }

    

    public static void tampilkanTunggakan() {
    Scanner input = new Scanner(System.in);
    System.out.print("Masukkan nama yang ingin dicek tunggakannya: ");
    String namaCek = input.nextLine();

    // Cek di daftar user yang sudah pernah join
    User user = cariUser(namaCek);
    if (user != null) {
        int tunggakan = tunggakanMap.getOrDefault(user.nama, 0); // Ambil tunggakan dari tunggakanMap
        if (tunggakan > 0) {
            System.out.println("Tunggakan " + user.nama + ": Rp" + tunggakan + " (Belum Bayar)");
        } else {
            System.out.println("Tunggakan " + user.nama + ": Rp0 (Sudah Bayar)");
        }
        return;
    }

    // Cek di daftar santri
    for (Santri s : daftarSantri) {
    // Trim nama untuk menghindari masalah spasi tambahan
    if (s.nama.trim().equalsIgnoreCase(namaCek.trim())) {
        // Pastikan pembayaran memiliki setidaknya satu elemen
        if (s.pembayaran.isEmpty()) {
            System.out.println("Data pembayaran tidak ditemukan untuk " + s.nama);
            return;
        }

        String statusBayar = s.pembayaran.get(0).trim();

        // Jika data kosong, berarti sudah bayar (tunggakan 0)
        if (statusBayar.isEmpty()) {
            System.out.println("Tunggakan " + s.nama + ": Rp0 (Sudah Bayar)");
        } else {
            // Jika tertulis 35000, berarti belum bayar
            System.out.println("Tunggakan " + s.nama + ": Rp" + statusBayar + " (Belum Bayar)");
        }
        return;
    }
}

// Jika nama tidak ditemukan
System.out.println("Nama tidak ditemukan di sistem.");

    
}

    
    public static void tampilkanRiwayatPembayaranPerOrang(BSTNode root) {
    if (root == null) return;
    // Traversal inorder: left, root, right
    tampilkanRiwayatPembayaranPerOrang(root.left);
    System.out.println("Nama: " + root.nama + " → Total Bayar: Rp" + root.totalBayar + " → Tanggal: " + root.tanggal);
    tampilkanRiwayatPembayaranPerOrang(root.right);
}

}

