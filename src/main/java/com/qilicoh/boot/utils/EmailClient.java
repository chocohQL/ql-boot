package com.qilicoh.boot.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class EmailClient {
    @Value("${email.from}")
    private String from;
    @Value("${email.host}")
    private String host;
    @Value("${email.password}")
    private String password;
    @Value("${email.username}")
    private String username;

    private volatile MailAccount account;

    public void sendHttpEmail(String to, String subject, String content, String... filePaths) {
        sendEmail(CollUtil.newArrayList(to), subject, content, true, filePaths);
    }

    public void sendSimpleEmail(String to, String subject, String content, String... filePaths) {
        sendEmail(CollUtil.newArrayList(to), subject, content, false, filePaths);
    }

    public void sendHttpEmails(ArrayList<String> tos, String subject, String content, String... filePaths) {
        sendEmail(tos, subject, content, true, filePaths);
    }

    public void sendSimpleEmails(ArrayList<String> tos, String subject, String content, String... filePaths) {
        sendEmail(tos, subject, content, false, filePaths);
    }

    private void sendEmail(ArrayList<String> tos, String subject, String content, boolean isHtml, String... filePaths) {
        File[] files = Arrays.stream(filePaths).map(FileUtil::newFile).toArray(File[]::new);
        MailUtil.send(getAccount(), tos, subject, content, isHtml, files);
    }

    private MailAccount getAccount() {
        if (account == null) {
            synchronized (this) {
                if (account == null) {
                    account = new MailAccount()
                            .setAuth(true).setSslEnable(true).setPort(465)
                            .setFrom(from).setHost(host).setPass(password).setUser(username);
                }
            }
        }
        return account;
    }
}
