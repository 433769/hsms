package cz.muni.fi.pa165.skupina06.team02.rms.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.ShoppingListDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.UserDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.HouseholdDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingList;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User;

/**
 * @author Vojtech Prusa
 * 
 *         Implementation of the {@link HouseholdService}. This class is part of
 *         the service module of the application that provides the
 *         implementation of the business logic (main logic of the application).
 * 
 */
@Service
public class HouseholdServiceImpl implements HouseholdService {

    @Autowired
    private ShoppingListDao shoppingListDao;

    @Autowired
    private HouseholdDao householdDao;

    @Autowired
    private UserDao userDao;

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService#
     * createHousehold(cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household)
     */
    @Override
    public void createHousehold(Household household) {
        householdDao.create(household);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService#
     * findHouseholdById(java.lang.Long)
     */
    @Override
    public Household findHouseholdById(Long id) {
        return householdDao.findById(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService#
     * findAllHouseholds()
     */
    @Override
    public List<Household> findAll() {
        return householdDao.findAll();
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService#
     * getHouseholdsByUser(cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User)
     */
    @Override
    public List<Household> getHouseholdsByUser(User user) {
        // user.getHouseholds()
        // return householdDao.findByUser(user);
        // TODO more db queies
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService#
     * getHouseholdsByShoppingList(cz.muni.fi.pa165.skupina06.team02.rms.app.entity.
     * ShoppingList)
     */
    @Override
    public List<ShoppingList> getHouseholdsByShoppingList(ShoppingList shoppingList) {
        // TODO idk if like this of to pass it through transactions?
        return shoppingList.getHousehold().getShoppingLists();
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService#
     * updateHousehold(cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household)
     */
    @Override
    public void updateHousehold(Household household) {
        householdDao.update(household);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService#
     * deleteHousehold(cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household)
     */
    @Override
    public void deleteHousehold(Household household) {
        householdDao.delete(household);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService#
     * removeTenant(cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User,
     * cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household)
     */
    @Override
    public void removeTenant(User tenant, Household housheold) {
        userDao.findById(tenant.getId()).leaveHousehold(householdDao.findById(housheold.getId()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService#addTenant(
     * cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User,
     * cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household)
     */
    @Override
    public void addTenant(User tenant, Household housheold) {
        userDao.findById(tenant.getId()).joinHousehold(householdDao.findById(housheold.getId()));
    }

}
