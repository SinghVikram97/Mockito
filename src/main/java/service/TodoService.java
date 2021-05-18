package service;

import java.util.List;

// TodoService can be talking to a database
// This is only the interface no implementation

public interface TodoService {

    // Retrieves Todos for a particular user
    public List<String> retrieveTodos(String user);
}
