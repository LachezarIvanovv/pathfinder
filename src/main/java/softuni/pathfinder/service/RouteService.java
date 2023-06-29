package softuni.pathfinder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.pathfinder.model.Route;
import softuni.pathfinder.repository.RouteRepository;

import java.util.List;

@Service
public class RouteService {


    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> getMostCommented() {
        return routeRepository.findMostCommented();
    }
}
