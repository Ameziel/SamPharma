package dao;


import exceptions.ErrCommandeConcernee;
import exceptions.ErrIdentifiantDejaExistant;
import exceptions.ErrObjetInexistant;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO<T,I> {

    public void create(T arg_obj) throws ErrIdentifiantDejaExistant;

    public void update(T arg_obj) throws ErrObjetInexistant;

    public void delete(I arg_obj) throws ErrObjetInexistant, ErrCommandeConcernee, SQLException;

    public T find(I arg_id) throws ErrObjetInexistant, SQLException;

    public ArrayList<T> findAll();
}
