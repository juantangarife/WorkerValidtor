package dao.marketplace;

import dao.DatabaseExecutionContext;
import io.ebean.Ebean;
import io.ebean.EbeanServer;
import model.marketplace.ProductoMarketplace;
import play.db.ebean.EbeanConfig;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;
import static java.util.concurrent.CompletableFuture.supplyAsync;

public class ProductoMarketplaceDAO {
    private final EbeanServer ebeanServer;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public ProductoMarketplaceDAO(DatabaseExecutionContext executionContext, EbeanConfig ebeanConfig){
        this.ebeanServer = Ebean.getServer("marketplace");
        this.executionContext = executionContext;
    }

    public CompletionStage<List<ProductoMarketplace>> getProductosMarketplace(){
        return supplyAsync(() -> {
            return ebeanServer.find(ProductoMarketplace.class).findList();
        }, executionContext);
    }
}
