package br.com.fiap.cp1.repository;

import br.com.fiap.cp1.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
