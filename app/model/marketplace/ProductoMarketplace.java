package model.marketplace;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class ProductoMarketplace extends Model {
    @Id
    public Long id;

    @Constraints.Required
    public String descripcion;

    @Constraints.Required
    public Double precio;

    public static final Finder<Long, ProductoMarketplace> find = new Finder<>(ProductoMarketplace.class);
}
