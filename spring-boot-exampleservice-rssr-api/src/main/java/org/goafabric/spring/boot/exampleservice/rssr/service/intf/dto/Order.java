package org.goafabric.spring.boot.exampleservice.rssr.service.intf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by amautsch on 29.06.2015.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@SuppressWarnings("PMD.UnusedPrivateField")
public class Order {
    private Long    orderId;
    private String  orderDescription;

    private Date    orderDate;
}
