package sky.pro.java.skyprotz.service;

import org.springframework.stereotype.Service;
import sky.pro.java.skyprotz.model.Sock;
import sky.pro.java.skyprotz.repository.SockRepository;

import java.util.List;

@Service
public class SockService {

    private final SockRepository sockRepository;

    public SockService(SockRepository sockRepository) {
        this.sockRepository = sockRepository;
    }

    /**
     * Добавление одной пары носков.
     */
    public void addSock(Sock sock) {
        sockRepository.save(sock);
    }

    /**
     * Удаление одной пары носков.
     */
    public void deleteSock(Sock sock) {
        List<Sock> findSocks = sockRepository.findByColorIgnoreCaseAndCottonPart(sock.getColor(),
                sock.getCottonPart());
        sockRepository.delete(findSocks.get(0));
    }

    /**
     * Добавление требуемого количества пар носков.
     */
    public String incomeSocks(Sock sock, int quantity) {
        for (int i = 0; i < quantity; i++) {
            addSock(sock);
        }
        return "Партия носков оприходована.";
    }

    /**
     * Удаление требуемого количества пар носков.
     */
    public String outcomeSocks(Sock sock, int quantity) {
        List<Sock> findSocks = sockRepository.findByColorIgnoreCaseAndCottonPart(sock.getColor(),
                sock.getCottonPart());
        if (findSocks.size() < quantity) {
            return "На складе нет столько носков.";
        }
        for (int i = 0; i < quantity; i++) {
            sockRepository.deleteById(findSocks.get(i).getId());
        }
        return "Партия носков отпущена.";
    }

    /**
     * Получение количества пар носков по цвету и минимальному содержанию хлопка.
     */
    public int getSocksWithCottonPartMoreThan(String color, int cottonPartMin) {
        List<Sock> socksByColor = sockRepository.findByColorIgnoreCase(color);
        List<Sock> needSocks = socksByColor.stream()
                .filter(s -> s.getCottonPart() >= cottonPartMin)
                .toList();
        return needSocks.size();
    }

    /**
     * Получение количества пар носков по цвету и максимальному содержанию хлопка.
     */
    public int getSockWithCottonPartLessThan(String color, int cottonPartMax) {
        List<Sock> socksByColor = sockRepository.findByColorIgnoreCase(color);
        List<Sock> needSocks = socksByColor.stream()
                .filter(s -> s.getCottonPart() <= cottonPartMax)
                .toList();
        return needSocks.size();
    }

    /**
     * Получение количества пар носков по цвету и содержанию хлопка.
     */
    public int getSockWithCottonPartEqual(String color, int cottonPart) {
        List<Sock> socksByColor = sockRepository.findByColorIgnoreCase(color);
        List<Sock> needSocks = socksByColor.stream()
                .filter(s -> s.getCottonPart() == cottonPart)
                .toList();
        return needSocks.size();
    }
}
