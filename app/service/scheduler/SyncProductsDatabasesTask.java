package service.scheduler;
import javax.inject.Inject;

import akka.actor.ActorSystem;
import scala.concurrent.ExecutionContext;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

public class SyncProductsDatabasesTask {
    private final ActorSystem actorSystem;
    private final ExecutionContext executionContext;
    private final SyncDatabasesService syncDatabasesService;

    @Inject
    public SyncProductsDatabasesTask(ActorSystem actorSystem, ExecutionContext executionContext, SyncDatabasesService syncDatabasesService) {
        this.actorSystem = actorSystem;
        this.executionContext = executionContext;
        this.syncDatabasesService = syncDatabasesService;
        this.initialize();
    }

    private void initialize() {
        this.actorSystem.scheduler().schedule(
                Duration.create(0, TimeUnit.SECONDS), // initialDelay
                Duration.create(5, TimeUnit.SECONDS), // interval
                () -> {
                    this.syncDatabasesService.syncProductsDatabases();
                },
                this.executionContext
        );
    }
}
