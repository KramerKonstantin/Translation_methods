while (<>) {
	print if /\([^()]*\w+[^()]*\)/;
}
