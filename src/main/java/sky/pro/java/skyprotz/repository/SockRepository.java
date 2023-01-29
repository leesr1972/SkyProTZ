package sky.pro.java.skyprotz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sky.pro.java.skyprotz.model.Sock;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface SockRepository extends JpaRepository<Sock, Long> {
    List<Sock> findByColorIgnoreCaseAndCottonPart(String color, int cottonPart);
    List<Sock> findByColorIgnoreCase(String color);
}
