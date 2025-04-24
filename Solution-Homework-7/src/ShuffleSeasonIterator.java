import java.util.*;

public class ShuffleSeasonIterator implements EpisodeIterator {
    private List<Episode> shuffled;
    private int index = 0;

    public ShuffleSeasonIterator(Season season, long seed) {
        this.shuffled = new ArrayList<>(season.getEpisodes());
        Collections.shuffle(shuffled, new Random(seed));
    }

    public boolean hasNext() {
        return index < shuffled.size();
    }

    public Episode next() {
        return shuffled.get(index++);
    }
}
