package com.design.utils.queueUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by cvter on 2017/5/27.
 */
public class TaskQueueManager {
    private static Map<String, TaskQueueRedisImpl> queneMap = new ConcurrentHashMap<String, TaskQueueRedisImpl>();
    /**
     * 购买队列名。
     */
    public static final String BUY_QUEUE = "BUY_QUEUE";

    private static void initQueneMap() {
        queneMap.put(BUY_QUEUE, new TaskQueueRedisImpl(BUY_QUEUE));
    }

    static {
        initQueneMap();
    }

    public static TaskQueue get(String name) {
        return getRedisTaskQueue(name);
    }

    public static TaskQueue getRedisTaskQueue(String name) {
        return queneMap.get(name);
    }

}
