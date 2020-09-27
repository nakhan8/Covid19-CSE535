package com.example.Assign1;

import java.sql.Timestamp;

public class HeartRate {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    int heartrate = HeartRateView.heartRateBPM;
}
