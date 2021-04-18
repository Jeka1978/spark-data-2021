package com.epam.jpoint.sparkdata2021;

import com.epam.unsafe_sparkdata.Source;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Evgeny Borisov
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Source("data/orders.csv")
public class Order {
//name,desc,price,criminalId
    private String name;
    private String desc;
    private int price;

    private long criminalId;


}
