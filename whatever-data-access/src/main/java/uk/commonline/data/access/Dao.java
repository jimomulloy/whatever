package uk.commonline.data.access;

import java.util.List;

import uk.commonline.data.model.EI;

public interface Dao<T extends EI> {

    long count();

    /**
     * <p>
     * If the passed object has a <code>setDateCreated(Date)</code> method then
     * we call it, passing in the current timestamp.
     * </p>
     * 
     * @param t
     */
    T create(T t);

    /**
     * Updates the EI if there aren't any errors. Can potentially generate new
     * errors (e.g. constraint violations).
     * 
     * @param ei
     *            EI to update
     * @param errors
     *            errors object
     */
    // void update(T ei, Errors errors);

    void delete(T t);

    void deleteAll();

    void deleteById(Long id);

    boolean exists(Long id);

    /**
     * Finds the requested object in the repository and returns it, or null if
     * there is no such persistent instance.
     * 
     * @param id
     *            ID
     * @return requested object, or null
     */
    T get(Long id);

    List<T> getAll();

    /**
     * <p>
     * Returns either a proxy for the requested object (one having the right
     * class and ID), or else the actual object if it's available without
     * hitting the repository (e.g. in cache). The basic idea behind this method
     * is to allow apps establish references to the requested object without
     * requiring a call to the repository.
     * </p>
     * <p>
     * Use this method only if you assume the instance actually exists; i.e.,
     * non-existence is an exception.
     * </p>
     * 
     * @param id
     * @return
     */
    T load(Long id);

    T update(T t);
}
