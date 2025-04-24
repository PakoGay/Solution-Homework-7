import java.util.*;

public class BingeIterator implements EpisodeIterator {
    private List<Season> seasons;
    private int seasonIndex = 0;
    private EpisodeIterator current;

    public BingeIterator(List<Season> seasons) {
        this.seasons = seasons;
        if (!seasons.isEmpty()) {
            current = seasons.get(0).getIterator();
        }
    }

    public boolean hasNext() {
        while (current != null && !current.hasNext()) {
            seasonIndex++;
            if (seasonIndex < seasons.size()) {
                current = seasons.get(seasonIndex).getIterator();
            } else {
                current = null;
                break;
            }
        }
        return current != null && current.hasNext();
    }

    public Episode next() {
        return current.next();
    }
}
