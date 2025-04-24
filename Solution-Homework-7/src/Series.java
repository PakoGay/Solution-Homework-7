import java.util.*;

public class Series {
    private List<Season> seasons = new ArrayList<>();

    public void addSeason(Season s) {
        seasons.add(s);
    }

    public EpisodeIterator getBingeIterator() {
        return new BingeIterator(seasons);
    }

    public List<Season> getSeasons() {
        return seasons;
    }
}
