package com.design.utils.queueUtil;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by cvter on 2017/5/27.
 */
public class TaskQueueRedisImpl implements TaskQueue {
    @Autowired
    private JedisPool jedisPool;

//    private final static Logger logger = Logger.getLogger(TaskQueueRedisImpl.class);

    private final String name;

    /**
     * 构造函数。
     *
     * @param name
     */
    public TaskQueueRedisImpl(String name) {
        this.name=name;
    }

    /* (non-Javadoc)
     * @see com.gwssi.common.mq.TaskQueue#getName()
     */
    public String getName() {
        return this.name;
    }

    /* (non-Javadoc)
     * @see com.gwssi.common.mq.TaskQueue#pushTask(String)
     */
    public void pushTask(String task) {
        Jedis jedis=jedisPool.getResource();
        jedis.lpush(this.name, task);
        jedis.close();
    }

    /* (non-Javadoc)
     * @see com.gwssi.common.mq.TaskQueue#popTask()
     */
    public String popTask() {
        Jedis jedis=jedisPool.getResource();
        String task=null;
        task=jedis.rpop(this.name);
        return task;
    }

}
