package org.hubu.framework.core.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.Semaphore;

@Data
@AllArgsConstructor
public class ServerServiceSemaphoreWrapper {

    private Semaphore semaphore;

    private int maxNums;

    public ServerServiceSemaphoreWrapper(int maxNums) {
        this.maxNums = maxNums;
        this.semaphore = new Semaphore(maxNums);
    }

}
