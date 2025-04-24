public class Main {
    public static void main(String[] args) {
        Series series = new Series();
        for (int i = 1; i <= 2; i++) {
            Season season = new Season();
            for (int j = 1; j <= 3; j++) {
                season.addEpisode(new Episode("S" + i + "E" + j, 1200 + i * 100 + j * 10));
            }
            series.addSeason(season);
        }
        System.out.println("Normal Season Iterator:");
        EpisodeIterator normal = series.getSeasons().get(0).getIterator();
        while (normal.hasNext()) {
            System.out.println(normal.next());
        }
        System.out.println("Reverse Season Iterator:");
        EpisodeIterator reverse = series.getSeasons().get(0).getReverseIterator();
        while (reverse.hasNext()) {
            System.out.println(reverse.next());
        }
        System.out.println("Shuffle Season Iterator:");
        EpisodeIterator shuffle = series.getSeasons().get(0).getShuffleIterator(42);
        while (shuffle.hasNext()) {
            System.out.println(shuffle.next());
        }
        System.out.println("Binge Watch (All Seasons):");
        EpisodeIterator binge = series.getBingeIterator();
        while (binge.hasNext()) {
            System.out.println(binge.next());
        }
    }
}
