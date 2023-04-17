import os
import paramiko 
from stat import S_ISDIR, S_ISREG  
ssh = paramiko.SSHClient()
ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
ssh.connect(hostname='sshop.tplinkdns.com', port=3322, username="ivetm", password="ilovetmit@123")
sftp = ssh.open_sftp()
filename = 'folderMing_7_2'
localpath = '/home/henry/Downloads/' + filename
remotepath = '/var/www/html/' + filename

remotepath2 = '/home/ivetm/faceDataset/' + filename
print(remotepath2)
def sftp_get_recursive(path, dest, sftp=sftp):
	item_list = sftp.listdir(path)
	dest = str(dest)
	for item in item_list:
		item = str(item)

		if S_ISDIR(mode)::
		    sftp_get_recursive(path + "/" + item, dest + "/" + item, sftp)
		else:
		    sftp.get(path + "/" + item, dest + "/" + item)
sftp_get_recursive(remotepath2, localpath, sftp)
#sftp.get(remotepath2, localpath)
sftp.close()
ssh.close()
