class Pair<A, B> {
    private A a;
    private B b;

    Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }

    A getFirst() {
        return a;
    }

    B getSecond() {
        return b;
    }

    public boolean equals(Object o) {
        if (o instanceof Pair) {
            Pair that = (Pair) o;
            return a.equals(that.a) && b.equals(that.b);
        }
        return false;
    }

    public int hashCode() {
        return a.hashCode() + b.hashCode();
    }
}