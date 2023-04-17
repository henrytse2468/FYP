<?php
	ini_set('memory_limit', '1024M');
	$id = $_POST["id"];
	shell_exec("sudo python3 capTrain.py ".$id." > /dev/null 2>&1 &");
        
        shell_exec("sudo python3 face-recognition-master/training/v2f.py ".$id." > /dev/null 2>&1 &" );
        
	shell_exec("sudo python3 /var/www/html/face-recognition-master/training/train.py -d /home/ivetm/faceDataset/folderMaster > /dev/null 2>&1 &");
	//shell_exec("sudo python3 capTrain.py ".$id);
        
        //shell_exec("sudo python3 face-recognition-master/training/v2f.py ".$id);
        
	//shell_exec("sudo python3 /var/www/html/face-recognition-master/training/train.py -d /home/ivetm/faceDataset/folderMaster");


	echo "done runTrain.php!";
        
?>
