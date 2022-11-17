package com.dunkware.street.stats.dunkwarestreetstats;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StatService {

    private final StatRepository statRepository;
    //    Get All Stats
    public List<Stats> getAllStats() {
        return statRepository.findAll();
    }
    //    Delete All Stats
    public void deleteAllStats() {
        statRepository.deleteAll();
    }
}
