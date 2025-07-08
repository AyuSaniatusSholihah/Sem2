import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int tahun;
    List<Jurnal> daftarJurnalDiTahunIni;
    TreeNode left;
    TreeNode right;

    public TreeNode(int tahun) {
        this.tahun = tahun;
        this.daftarJurnalDiTahunIni = new ArrayList<>();
        this.left = null;
        this.right = null;
    }
}