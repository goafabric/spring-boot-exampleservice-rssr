package org.goafabric.spring.boot.exampleservice.rssr.service.intf.result;

import org.goafabric.common.service.result.ServiceResult;
import lombok.*;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.dto.Order;

/**
 * Created by amautsch on 29.06.2015.
 */

@Data
@Builder
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("PMD.UnusedPrivateField")
public class OrderResult extends ServiceResult {

    private Order value;
}
