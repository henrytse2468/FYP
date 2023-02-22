import os
import paramiko

filename = "getBooking.php"
server = "sshop.tplinkdns.com:3322"
username = "ivetm"
password = "ilovetmit@123"
localpath = "C:/Users/henry/OneDrive/文件/fypPHP/" + filename
remotepath = "/var/www/html/" + filename

ssh = paramiko.SSHClient() 
ssh.load_host_keys(os.path.expanduser(os.path.join("~", ".ssh", "known_hosts")))
ssh.connect( "sshop.tplinkdns.com", 3322, username="ivetm", password="ilovetmit@123")
sftp = ssh.open_sftp()
sftp.put(localpath, remotepath)
sftp.close()
ssh.close()
