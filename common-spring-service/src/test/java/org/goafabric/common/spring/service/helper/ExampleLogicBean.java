package org.goafabric.common.spring.service.helper;

import org.goafabric.common.spring.service.security.annotation.AuditLog;
import org.goafabric.common.spring.service.sustainability.annotation.ResultHandler;

/**
 * Created by amautsch on 14.08.2015.
 */
@ResultHandler
@AuditLog
public class ExampleLogicBean {
    public BasicResult create() {
        return new BasicResult();
    }

    public BasicResult get() {
        return new BasicResult();
    }

    public BasicResult update() {
        throw new UnsupportedOperationException();
    }
}
