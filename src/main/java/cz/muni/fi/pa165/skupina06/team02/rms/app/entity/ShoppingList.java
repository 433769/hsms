package cz.muni.fi.pa165.skupina06.team02.rms.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * @author Vojtech Prusa
 *
 */
@Entity
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @OneToMany
    // @OrderBy("tableColumnName DESC")
    // @JoinColumn(name="tableFK")
    private List<ShoppingItem> shoppingItems = new ArrayList<ShoppingItem>();

    /**
     * @return shopping list instance
     */
    public List<ShoppingItem> getShoppingItems() {
        return shoppingItems;
    }

    /**
     * @param shoppingItems instance
     */
    public void setShoppingItems(List<ShoppingItem> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }

    /**
     * Add ShoppingItem to list
     * 
     * @param shoppingItem instance
     */
    public void addToShoppingList(ShoppingItem shoppingItem) {
        shoppingItems.add(shoppingItem);
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id value
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name value
     */
    public void setName(String name) {
        this.name = name;
    }

}
