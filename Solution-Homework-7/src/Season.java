import java.util.*;

public class Season implements Iterable<Episode> {
    private List<Episode> episodes = new ArrayList<>();

    public void addEpisode(Episode e) {
        episodes.add(e);
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public EpisodeIterator getIterator() {
        return new SeasonIterator(this);
    }

    public EpisodeIterator getReverseIterator() {
        return new ReverseSeasonIterator(this);
    }

    public EpisodeIterator getShuffleIterator(long seed) {
        return new ShuffleSeasonIterator(this, seed);
    }

    @Override
    public Iterator<Episode> iterator() {
        return episodes.iterator();
    }
}
