package com.epam.jpoint.sparkdata2021;

import com.epam.unsafe_sparkdata.Source;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Evgeny Borisov
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Source("data/criminals.csv")
public class Criminal {
    private long id;
    private String name;
    private int number;

    @ForeignKey("criminalId")
    private List<Order> orders;


    public void printAllOrders() {
        orders.forEach(System.out::println);
    }


}
