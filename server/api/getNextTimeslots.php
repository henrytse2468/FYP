<html>

<?php
$current_date = date('0g:i:s');
echo $current_date."<br/>";
$current_time = strtotime($current_date);

$frac = 900;
$r = $current_time % ($frac-$r);
$new_date = date('0g:i:s', $new_time);

echo $new_date."<br/>";
?>
</html>

