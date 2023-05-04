package com.example.userservice.entity.mongo;

import lombok.*;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/10/2023 1:29 PM
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Configuration {
    private String value;
    private ProductConfiguration name;
    private Double extraPrice;


    public enum ProductConfiguration {
        STORAGE,
        MEMORY,
        PROCESSOR,
        SIZES_AND_WEIGHT ,
        DISPLAY ,
        RESISTANCE ,
//        PROCESSOR,
        CAMERA,
        FRONT_CAMERA,
        VIDEO_RECORDING,
        FACE_ID,
        APPLE_PAY,
        CELLULAR_AND_WIRELESS,
        LOCATION,
        AUDIO_PLAYBACK,
        VIDEO_PLAYBACK,
        SIRI,
        POWER_AND_BATTERY,
        SENSORS,
        OPERATING_SYSTEM
    }
}
