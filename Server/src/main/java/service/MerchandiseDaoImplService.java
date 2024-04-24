package service;

import dao.MerchandiseDao;
import dao.impl.MerchandiseDaoImpl;
import models.Merchandise;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public class MerchandiseDaoImplService implements Remote {
    private final MerchandiseDao merchandiseDao = new MerchandiseDaoImpl();

    public MerchandiseDaoImplService() throws RemoteException {
    }

    public boolean addMerchandise(Merchandise merchandise) throws RemoteException {
        return merchandiseDao.addMerchandise(merchandise);
    }

    public List<Merchandise> getAllMerchandise() throws RemoteException {
        return merchandiseDao.getAllMerchandise();
    }

    public boolean checkIdExist(String id) throws RemoteException {
        return merchandiseDao.checkIdExist(id);
    }

    public boolean updateMerchandise(Merchandise merchandise) throws RemoteException {
        return merchandiseDao.updateMerchandise(merchandise);
    }

    public boolean deleteMerchandise(String id) throws RemoteException {
        return merchandiseDao.deleteMerchandise(id);
    }

    public List<Merchandise> findSupplierByNameMerchandise(String name) throws RemoteException {
        return merchandiseDao.findSupplierByNameMerchandise(name);
    }

    public List<Merchandise> findByProductTypeMerchandise(String name) throws RemoteException {
        return merchandiseDao.findProductTypeMerchandise(name);
    }


}
