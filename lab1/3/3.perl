$tmp = "";
while (<>) {
	$tmp = $tmp . $_;
}
$tmp =~ s/\n//g;

my @sites;

$regexp = "<a(.*?)href(.*?)=(.*?)(\"|')(.*?)(\"|')(.*?)>";

while ($tmp =~ /$regexp/) {
	$str = $5;
	$str =~ s/^.*?:\/\///;
	$str =~ s/[:\/].*$//;
	if (!($str =~ /(^$)|(^\.)|(\.$)|((\.){2,})/)) {
		push @sites, $str;	
	}
	$tmp =~ s/$regexp//;
}

my %rv = map {$_ => 1} @sites;
foreach (sort(keys %rv)) {
	print $_."\n";
}
