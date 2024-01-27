package de.fhws.fiw.fds.sutton.AdministrationSystem.database;

public class DaoFactory {
    private static DaoFactory INSTANCE;

    public static final DaoFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DaoFactory();
        }
        return INSTANCE;
    }

    private final SeriesDao seriesDao;
    private final SeasonDao seasonDao;
    private final EpisodeDao episodeDao;
    private final ActorDao actorDao;
    private final SeriesSeasonDao seriesSeasonDao;
    private final SeasonEpisodeDao seasonEpisodeDao;
    private final EpisodeActorDao episodeActorDao;

    private DaoFactory() {
        this.seriesDao = new SeriesInMemoryStorage();
        this.seasonDao = new SeasonInMemoryStorage();
        this.episodeDao = new EpisodeInMemoryStorage();
        this.actorDao = new ActorInMemoryStorage();
        this.seriesSeasonDao = new SeriesToSeasonRelationStorage();
        this.seasonEpisodeDao = new SeasonToEpisodeRelationStorage();
        this.episodeActorDao = new EpisodeToActorRelationStorage();
    }

    public SeriesDao getSeriesDao() {
        return this.seriesDao;
    }

    public SeasonDao getSeasonDao() {
        return this.seasonDao;
    }

    public EpisodeDao getEpisodeDao(){
        return this.episodeDao;
    }

    public ActorDao getActorDao(){
        return this.actorDao;
    }

    public SeriesSeasonDao getSeriesSeasonDao() {return seriesSeasonDao;}

    public SeasonEpisodeDao getSeasonEpisodeDao() {return seasonEpisodeDao;}

    public EpisodeActorDao getEpisodeActorDao() {return episodeActorDao;}
}
