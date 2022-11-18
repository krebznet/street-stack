package com.dunkware.street.stats.dunkwarestreetstats;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
@AllArgsConstructor
public class StatsController {
    private final StatService statService;

    @GetMapping("/stats")
    public List<Stats> fetchAllStats() {
        return statService.getAllStats();
    }

    @GetMapping("/delete")
    public void deleteAllStats() {
        statService.deleteAllStats();
    }
}
