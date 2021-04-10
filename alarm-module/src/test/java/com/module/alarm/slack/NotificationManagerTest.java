package com.module.alarm.slack;

import com.module.alarm.infrastructure.config.slack.NotificationManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NotificationManagerTest {

    @Autowired
    private NotificationManager notificationManager;

    @Test
    void sendNotification() {
        notificationManager.sendNotification("test");
    }
}