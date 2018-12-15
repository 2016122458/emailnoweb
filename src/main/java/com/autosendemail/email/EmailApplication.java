package com.autosendemail.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class EmailApplication implements CommandLineRunner {

    @Autowired
    private EmailDbOperate emailDbOperateForSend;

    @Autowired
    private EmailAppRunableForVeriEmail emailAppRunableForVeriEmail;

    @Autowired
    private EmailSendGridHandle emailSendGridHandle;

    @Autowired
    private EmailMailGunHandle emailMailGunHandle;

    @Autowired
    private EmailSparkPostHandle emailSparkPostHandle;

    @Autowired
    private EmailBatchInfoService emailBatchInfoService;

    public static void main(String[] args) {
        SpringApplication.run(EmailApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

//        EmailAppRunableForCloudEmail emailAppRunableForSendGrid =
//                new EmailAppRunableForCloudEmail(emailSendGridHandle,emailDbOperateForSend,emailBatchInfoService);
//        Thread thread_sendGrid = new Thread(emailAppRunableForSendGrid);
//        thread_sendGrid.start();
//        System.out.println("thread_sendGrid=" +  thread_sendGrid.getPriority());

        EmailAppRunableForCloudEmailBatch emailAppRunableForCloudEmailBatch =
                new EmailAppRunableForCloudEmailBatch(emailDbOperateForSend,emailBatchInfoService);
        Thread thread_sendMessageBatch = new Thread(emailAppRunableForCloudEmailBatch);
        thread_sendMessageBatch.start();
        System.out.println("thread_sendMessageBatch=" +  thread_sendMessageBatch.getPriority());

    }
}

