/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;

/**
 *
 * @author blade
 * @param <T>
 */
public interface InterfaceDao<T> {

    public boolean save(T objeto);

    public ArrayList<T> getAll();

    public boolean update(T objeto);

    public boolean delete(int id);

    public T get(int id);
}
