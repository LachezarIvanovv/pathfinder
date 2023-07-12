package softuni.pathfinder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.pathfinder.model.Route;
import softuni.pathfinder.model.view.RouteIndexView;
import softuni.pathfinder.repository.RouteRepository;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<RouteIndexView> getAllRoutes(){
        return routeRepository.findAll()
                .stream()
                .map(route -> new RouteIndexView(
                        route.getId(),
                        route.getName(),
                        route.getDescription(),
                        route.getPictures().stream().findFirst().get().getUrl()
                )).collect(Collectors.toList());
    }
}
