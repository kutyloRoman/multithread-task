package com.kutylo.subtask3;

import org.junit.Test;

public class MessageBrokerTest {

    private MessageBroker messageBroker = new MessageBroker();

    @Test
    public void testMessageBroker() throws InterruptedException {
        messageBroker.execute();
    }

}
