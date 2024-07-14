package com.dunkware.time.data.dto.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignalGenerator {

    public static List<EntitySignalDTO> generateSignals(int entityId, int signalId, int streamId, int varCount, LocalDate date, LocalTime startTime, int listSize) {
        List<EntitySignalDTO> signals = new ArrayList<>();
        LocalDateTime timestamp = LocalDateTime.of(date, startTime);

        for (int i = 0; i < listSize; i++) {
            Map<Integer, Double> vars = new HashMap<>();
            for (int j = 0; j < varCount; j++) {
                vars.put(j, Math.random());
                        vars.put(j, Math.random() * 100); // Example variable values
                    }

                    EntitySignalDTO EntitySignalDTO = new EntitySignalDTO(entityId, signalId, streamId, timestamp, vars);
                    signals.add(EntitySignalDTO);

                    // Increment timestamp by 1 second
                    timestamp = timestamp.plusSeconds(1);
                }

                return signals;
            }

            public static void main(String[] args) {
                int entityId = 1;
                int signalId = 101;
                int streamId = 202;
                int varCount = 5;
                LocalDate date = LocalDate.of(2023, 7, 10);
                LocalTime startTime = LocalTime.of(14, 55, 0);
                int listSize = 10;

                List<EntitySignalDTO> signals = generateSignals(entityId, signalId, streamId, varCount, date, startTime, listSize);
                
                signals.forEach(EntitySignalDTO -> {
                    System.out.println("Entity ID: " + EntitySignalDTO.getEntityId() +
                                       ", EntitySignalDTO ID: " + EntitySignalDTO.getSignalId() +
                                       ", Stream ID: " + EntitySignalDTO.getStreamId() +
                                       ", Timestamp: " + EntitySignalDTO.getTimestamp() +
                                       ", Vars: " + EntitySignalDTO.getVars());
                });
            }
        }
