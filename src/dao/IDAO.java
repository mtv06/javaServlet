package dao;

import java.util.List;
import java.util.Optional;

public interface IDAO<T> {
	
	Optional<T> get(int id);
	    
    List<T> getAll();
     
    void add(T t);
     
    void update(T t);
     
    void delete(int id);
}
