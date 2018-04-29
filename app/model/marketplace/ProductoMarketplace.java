package model.marketplace;

import io.ebean.Finder;
import model.BaseModel;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class ProductoMarketplace extends BaseModel {

    @Constraints.Required
    public String descripcion;

    @Constraints.Required
    public Double precio;

    public static final Finder<Long, ProductoMarketplace> find = new Finder<>(ProductoMarketplace.class);
}