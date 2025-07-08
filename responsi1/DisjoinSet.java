import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DisjointSet {
    private Map<Jurnal, Jurnal> parent;
    private Map<Jurnal, Integer> rank;

    public DisjointSet(List<Jurnal> journals) {
        parent = new HashMap<>();
        rank = new HashMap<>();
        for (Jurnal j : journals) {
            parent.put(j, j);
            rank.put(j, 0);
        }
    }

    public Jurnal find(Jurnal j) {
        if (!parent.get(j).equals(j)) {
            parent.put(j, find(parent.get(j)));
        }
        return parent.get(j);
    }

    public void union(Jurnal j1, Jurnal j2) {
        Jurnal root1 = find(j1);
        Jurnal root2 = find(j2);

        if (!root1.equals(root2)) {
            if (rank.get(root1) < rank.get(root2)) {
                parent.put(root1, root2);
            } else if (rank.get(root1) > rank.get(root2)) {
                parent.put(root2, root1);
            } else {
                parent.put(root2, root1);
                rank.put(root1, rank.get(root1) + 1);
            }
        }
    }
}