package com.example.hogwarts.service;
import org.springframework.stereotype.Service;
import java.util.stream.LongStream;

@Service
public class InfoService {

    public long calculateSumOptimized() {
        return LongStream.rangeClosed(1, 1_000_000)
                .parallel()
                .sum();
    }
}