package com.epam.unsafe_sparkdata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Evgeny Borisov
 */
@ConfigurationProperties(prefix = "spark")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SparkPropsHolder {
    private String appName;
    private String packagesToScan;
}
