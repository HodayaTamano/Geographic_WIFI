import java.util.Comparator;

public interface Compare extends Comparator<Wifi> {
	boolean filtrated(Wifi a, Wifi b, Wifi c);
}
