package com.example.jukes.services;


import com.example.jukes.exceptions.ResourceNotFoundException;
import com.example.jukes.models.Component;
import com.example.jukes.models.Jukebox;
import com.example.jukes.models.Setting;
import com.example.jukes.models.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JukeboxService {

    private final String uri = "http://my-json-server.typicode.com/touchtunes/tech-assignment";
    private RestTemplate restTemplate;

    @Autowired
    public JukeboxService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Jukebox> getJukeboxes(UUID uuid, Optional<String> model) {
        Setting setting = fetchSettingByUuid(uuid);
        List<Jukebox> jukes = fetchAllJukeboxes();
        return jukes.stream().filter(j -> matchJukeboxesAndSettings(j, setting)).filter(j -> matchJukeboxModels(j, model)).collect(Collectors.toList());
    }

    private Setting fetchSettingByUuid(UUID uuid) {
        Settings settings = restTemplate.getForObject(uri + "/settings", Settings.class);
        return settings.getSettings().stream().filter(s -> s.getId().equals(uuid)).findFirst().orElseThrow(() -> new ResourceNotFoundException("No Setting found with uuid : " + uuid));
    }

    private List<Jukebox> fetchAllJukeboxes() {
        ResponseEntity<List<Jukebox>> jukeboxes = restTemplate.exchange(uri + "/jukes", HttpMethod.GET, null, new ParameterizedTypeReference<List<Jukebox>>() {});
        if (jukeboxes.getStatusCode() != HttpStatus.OK) {
            throw new ResourceNotFoundException("Could not fetch jukeboxes");
        }
        return jukeboxes.getBody();
    }

    private boolean matchJukeboxesAndSettings(Jukebox juke, Setting setting) {
        Set<String> required = new HashSet<>(setting.getRequires());
        Set<String> current = juke.getComponents().stream().map(Component::getName).collect(Collectors.toSet());
        return current.containsAll(required);
    }

    private boolean matchJukeboxModels(Jukebox jukebox, Optional<String> model) {
        // if model is not present as param, return all models
        return model.isPresent() ? jukebox.getModel().equals(model.get()) : true;
    }
}
