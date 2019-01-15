package cz.muni.fi.pa165.skupina06.team02.rms.app.dto;

import java.util.*;

/**
 * @author Vojtech Prusa
 * <p>
 * DTO class of Household to transfer data between services
 */
public class HouseholdDTO {

    private Long id;

    private String street;

    private String buildingNumber;

    private String zipCode;

    private String state;

    private Set<UserPublicDTO> tenants = new HashSet<>();

    private List<ShoppingListDTO> shoppingLists = new ArrayList<>();

    /**
     * Default Constructor
     */
    public HouseholdDTO() {
    }

    /**
     * Constructor
     *
     * @param id
     */
    public HouseholdDTO(Long id) {
        super();
        this.id = id;
    }

    public HouseholdDTO(Long id, String street, String buildingNumber, String zipCode, String state,
                        Set<UserPublicDTO> tenants, List<ShoppingListDTO> shoppingLists) {
        this.id = id;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.zipCode = zipCode;
        this.state = state;
        this.tenants = tenants;
        this.shoppingLists = shoppingLists;
    }

    /**
     * Getter
     *
     * @return tenants instance
     */
    public Set<UserPublicDTO> getTenants() {
        return tenants;
    }

    /**
     * Setter
     *
     * @param tenants instance
     */
    public void setTenants(Set<UserPublicDTO> tenants) {
        this.tenants = tenants;
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

    public List<ShoppingListDTO> getShoppingLists() {
        return shoppingLists;
    }

    public void setShoppingLists(List<ShoppingListDTO> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    /**
     * @return street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street value
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return building number
     */
    public String getBuildingNumber() {
        return buildingNumber;
    }

    /**
     * @param buildingNumber value
     */
    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    /**
     * @return zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode value
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state value
     */
    public void setState(String state) {
        this.state = state;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        HouseholdDTO household = (HouseholdDTO) o;
        return Objects.equals(id, household.id) && Objects.equals(street, household.street)
                && Objects.equals(buildingNumber, household.buildingNumber)
                && Objects.equals(zipCode, household.zipCode) && Objects.equals(state, household.state);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, street, buildingNumber, zipCode, state);
    }
}
