class Edge implements Comparable<Edge> {
    Jurnal source;
    Jurnal destination;
    double weight;

    public Edge(Jurnal source, Jurnal destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Jurnal getSource() { return source; }
    public Jurnal getDestination() { return destination; }
    public double getWeight() { return weight; }

    @Override
    public int compareTo(Edge other) {
        return Double.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return source.getJudul() + " --(" + String.format("%.2f", weight) + ")--> " + destination.getJudul();
    }
}

