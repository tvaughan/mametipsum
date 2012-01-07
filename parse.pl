#!/usr/bin/perl -w

# This matches the scripts at dailyscript.com. For example:
# http://www.dailyscript.com/scripts/glengarry.html

use strict;

my $script = '';

while (<>) {
    chomp;
    if (/^ {25}(\S.*)$/) {
        $script .= $1 . ' ';
    }
}

$script =~ s/  +/ /g;
$script =~ s/(?<!Dr)(?<!Mr)(?<!Mrs)(?<!Ms)(?<!Miss)(\.|\?|\!) /$1\n/g;

print $script;
