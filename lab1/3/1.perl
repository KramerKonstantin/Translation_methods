$tmp = "not \n";
$line1 = 1;

while (<>) {
	if (!(/^\s*$/)) {
		if ($line1 != 1) {
			if ($tmp =~ /^$/) {
				print $tmp;
				$tmp = "not \n";
			}
		} else {
			$line1 = 0;
		}
		s/(^\s*)(.*?)(\s*$)/$2/g;
		s/(\s)\g1*/$1/g;
		print $_ . "\n";
	} else {
		$tmp = "\n";
	}
}
