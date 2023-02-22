package com.example.dog;

import android.util.Log;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Ssh_upload {
    private final String hostname = "sshop.tplinkdns.com";
    private final String username = "ivetm";
    private final String password = "ilovetmit@123";
    private String file = null;

    public Ssh_upload(String file_name) {
        this.file = file_name;

    }

    public String upload(){
        try {
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session = jsch.getSession(username, hostname, 3322);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            Log.v("ssh_con", "ssh connected");

            Channel channel = session.openChannel("sftp");
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.connect();

            sftpChannel.put(file, "video.mp4");

            sftpChannel.disconnect();
            session.disconnect();
            Log.v("ssh_con", "ssh upload completed");
            return "ssh upload completed";
            //System.out.println("DONE");
        } catch (Exception e) {
            Log.v("ssh_con", "ssh upload not completed");
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            Log.v("ssh_con", errors.toString());
            return errors.toString();
        }
    }
}
